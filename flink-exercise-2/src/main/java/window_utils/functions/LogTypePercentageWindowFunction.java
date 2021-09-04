package window_utils.functions;

import window_utils.aggregates.LogTypePercentageAccumulator;
import model.pojo.Log;
import model.pojo.LogTypePercentagesResult;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

public class LogTypePercentageWindowFunction implements WindowFunction<Log, LogTypePercentagesResult, String, TimeWindow> {
    @Override
    public void apply(String key, TimeWindow window, Iterable<Log> input, Collector<LogTypePercentagesResult> out) throws Exception {
        LogTypePercentageAccumulator accumulator = new LogTypePercentageAccumulator();

        for (Log log: input)
            accumulator = accumulator.add(log);

        LogTypePercentagesResult result = accumulator.getResult();
        result.setStartTimestamp(window.getStart());
        result.setEndTimestamp(window.getEnd());

        out.collect(result);
    }
}
