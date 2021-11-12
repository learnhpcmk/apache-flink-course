package window_utils.functions;

import model.pojo.ErrorPercentageResult;
import window_utils.aggregates.ErrorPercentageAccumulator;
import model.pojo.ErrorPercentageResultWithTimestamps;
import model.pojo.Log;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import window_utils.aggregates.ErrorPercentageAggregateFunction;

public class ErrorPercentageWindowFunction implements WindowFunction<Log, ErrorPercentageResult, String, TimeWindow> {

    @Override
    public void apply(String key, TimeWindow window, Iterable<Log> input, Collector<ErrorPercentageResult> out) throws Exception {
        ErrorPercentageAccumulator accumulator = new ErrorPercentageAccumulator();

        for (Log log: input){
            accumulator = accumulator.add(log);
        }

        ErrorPercentageResult result = accumulator.getResult();
        result.setStartTimestamp(window.getStart());
        result.setEndTimestamp(window.getEnd());

        out.collect(result);
    }
}
