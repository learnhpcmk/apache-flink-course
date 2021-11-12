package process_functions;

import model.pojo.Log;
import model.pojo.SystemsListByLogTypeResult;
import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ListStateDescriptor;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SystemListingProcessImproved extends KeyedProcessFunction<String, Log, SystemsListByLogTypeResult> {

    ListState<Log> last10Logs;
    ValueState<Integer> listStateCount;



    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        ListStateDescriptor<Log> descriptor = new ListStateDescriptor<>("systemListState", Log.class);
        last10Logs = getRuntimeContext().getListState(descriptor);

        ValueStateDescriptor<Integer> countDescriptor = new ValueStateDescriptor<Integer>("countOfListState", Integer.class);
        listStateCount = getRuntimeContext().getState(countDescriptor);

    }

    private List<Log> getAsList() throws Exception {
        List<Log> result = new ArrayList<>();
        last10Logs.get().forEach(result::add);
        return result;
    }

    @Override
    public void processElement(Log value, Context ctx, Collector<SystemsListByLogTypeResult> out) throws Exception {
        if (listStateCount.value()==null) {
            listStateCount.update(0);
        }

        if (listStateCount.value() == 10) {
            List<Log> currentLogs = getAsList();
            long start = currentLogs.stream().mapToLong(Log::getTimestamp).min().getAsLong();
            long end = currentLogs.stream().mapToLong(Log::getTimestamp).max().getAsLong();
            List<String> systemsList = currentLogs.stream().map(Log::getSystem).collect(Collectors.toList());

            out.collect(new SystemsListByLogTypeResult(
                    ctx.getCurrentKey(),
                    systemsList,
                    end-start
            ));

            last10Logs.clear();
            listStateCount.update(0);
        } else {
            last10Logs.add(value);
            listStateCount.update(listStateCount.value()+1);
        }
    }
}
