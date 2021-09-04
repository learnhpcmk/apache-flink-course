package window_utils.functions;

import model.pojo.Log;
import model.pojo.LogCountResult;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

public class LogCountingWindowFunction implements WindowFunction<Log, LogCountResult, String, TimeWindow> {
    @Override
    public void apply(String key, TimeWindow window, Iterable<Log> input, Collector<LogCountResult> out) throws Exception {
        long count = 0;
        for (Log log : input) {
            ++count;
        }

        String [] parts = key.split("___");
        String system = parts[0];
        String logType = parts[1];

        out.collect(new LogCountResult(
                system,
                logType,
                count,
                window.getStart(),
                window.getEnd()
        ));

    }
}
