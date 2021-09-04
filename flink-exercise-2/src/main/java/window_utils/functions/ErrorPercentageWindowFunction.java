package window_utils.functions;

import window_utils.aggregates.ErrorPercentageAccumulator;
import model.pojo.ErrorPercentageResultWithTimestamps;
import model.pojo.Log;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

public class ErrorPercentageWindowFunction implements WindowFunction<Log, ErrorPercentageResultWithTimestamps, String, TimeWindow> {

    @Override
    public void apply(String s, TimeWindow window, Iterable<Log> input, Collector<ErrorPercentageResultWithTimestamps> out) throws Exception {
        ErrorPercentageAccumulator accumulator = new ErrorPercentageAccumulator();

        for (Log log: input){
            accumulator = accumulator.add(log);
        }

        ErrorPercentageResultWithTimestamps result = new ErrorPercentageResultWithTimestamps(
                accumulator.getResult().getSystem(),
                accumulator.getResult().getPercentage(),
                window.getStart(),
                window.getEnd()
        );

        out.collect(result);
    }
}
