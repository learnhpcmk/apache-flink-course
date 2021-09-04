package window_utils.functions;

import model.pojo.*;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.util.DoubleSummaryStatistics;

public class SeverityStatisticsWindowFunction implements WindowFunction<Log, SeverityStatisticsResult, String, TimeWindow> {
    @Override
    public void apply(String key, TimeWindow window, Iterable<Log> input, Collector<SeverityStatisticsResult> out) throws Exception {
        DoubleSummaryStatistics statistics = new DoubleSummaryStatistics();
        for (Log log : input) {
            statistics.accept(log.getSeverity());
        }

        out.collect(new SeverityStatisticsResult(
                key,
                window.getStart(),
                window.getEnd(),
                new SeverityStats(
                        statistics.getCount(),
                        statistics.getMin(),
                        statistics.getAverage(),
                        statistics.getMax()
                )
        ));

    }
}
