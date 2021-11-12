package process_functions;

import model.pojo.ErrorDeltaTimeResult;
import model.pojo.Log;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

public class DifferenceBetweenErrorsCalculator extends KeyedProcessFunction<String, Log, ErrorDeltaTimeResult> {

    private ValueState<Log> lastErrorState;
    private static final String ERROR = "ERROR";

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        ValueStateDescriptor<Log> descriptor = new ValueStateDescriptor<>(
                "lastErrorLog",
                Log.class
        );

        lastErrorState = getRuntimeContext().getState(descriptor);
    }

    @Override
    public void processElement(Log newLog, Context ctx, Collector<ErrorDeltaTimeResult> out) throws Exception {
        if (newLog.getType().equals(ERROR)) {
            if (lastErrorState.value() != null) {
                Log lastError = lastErrorState.value();
                out.collect(new ErrorDeltaTimeResult(
                        ctx.getCurrentKey(), //system name, the key of the partition
                        lastError.getTimestamp(),
                        newLog.getTimestamp(),
                        newLog.getTimestamp()-lastError.getTimestamp()
                ));
            }
            lastErrorState.update(newLog);
        }
    }
}
