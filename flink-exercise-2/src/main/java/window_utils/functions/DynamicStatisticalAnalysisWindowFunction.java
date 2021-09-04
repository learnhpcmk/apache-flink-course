package window_utils.functions;

import com.tdunning.math.stats.TDigest;
import com.tdunning.math.stats.TreeDigest;
import data.MatchedMessage;
import model.InputMessage;
import model.pojo.RuleStatisticsResult;
import model.pojo.Statistics;
import model.pojo.control.ControlMessage;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.util.DoubleSummaryStatistics;

public class DynamicStatisticalAnalysisWindowFunction implements WindowFunction<MatchedMessage, RuleStatisticsResult, String, TimeWindow> {

    @Override
    public void apply(String key, TimeWindow window, Iterable<MatchedMessage> input, Collector<RuleStatisticsResult> out) {
        DoubleSummaryStatistics statistics = new DoubleSummaryStatistics();
        TDigest tDigest = new TreeDigest(100);

        String [] parts = key.split("___");
        String userId = parts[0];
        String ruleId = parts[1];


        for (MatchedMessage matchedMessage : input){
            InputMessage inputMessage = matchedMessage.inputMessage;
            ControlMessage controlMessage = matchedMessage.controlMessage;

            double value = Double.parseDouble(inputMessage.getFieldValue(controlMessage.getStatisticsProperty()));
            statistics.accept(value);
            tDigest.add(value);
        }

        out.collect(new RuleStatisticsResult(
                userId,
                ruleId,
                "",
                window.getStart(),
                window.getEnd(),
                new Statistics(
                        statistics.getMin(),
                        tDigest.quantile(0.25),
                        tDigest.quantile(0.5),
                        statistics.getAverage(),
                        tDigest.quantile(0.75),
                        statistics.getMax(),
                        statistics.getCount()
                )
        ));

    }
}
