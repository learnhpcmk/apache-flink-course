package window_utils.functions;

import window_utils.aggregates.LogTypePercentageAccumulator;
import model.pojo.Log;
import model.pojo.LogTypePercentagesResult;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.GlobalWindow;
import org.apache.flink.util.Collector;

public class LogTypePercentageGlobalWindowFunction implements WindowFunction<Log, LogTypePercentagesResult, String, GlobalWindow> {
    @Override
    public void apply(String s, GlobalWindow window, Iterable<Log> input, Collector<LogTypePercentagesResult> out) throws Exception {
        long startTimestamp = Long.MAX_VALUE;
        long endTimestamp = Long.MIN_VALUE;

        LogTypePercentageAccumulator accumulator = new LogTypePercentageAccumulator();
        for (Log log : input) {
            startTimestamp = Math.min(startTimestamp, log.getTimestamp());
            endTimestamp = Math.max(endTimestamp, log.getTimestamp());
            accumulator = accumulator.add(log);
        }

        LogTypePercentagesResult result = accumulator.getResult();
        result.setStartTimestamp(startTimestamp);
        result.setEndTimestamp(endTimestamp);
    }
}
