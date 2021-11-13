package exercise6;

import com.google.gson.Gson;
import com.tdunning.math.stats.TDigest;
import com.tdunning.math.stats.TreeDigest;
import model.InputMessage;
import model.pojo.Log;
import model.pojo.SeverityAssignerMessage;
import model.pojo.SeverityStatisticsResult;
import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.functions.PatternProcessFunction;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.cep.pattern.conditions.IterativeCondition;
import org.apache.flink.cep.pattern.conditions.SimpleCondition;
import org.apache.flink.streaming.api.datastream.BroadcastStream;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;
import process_functions.AssignSeverityToLogsProcess;
import source.test_sources.ExampleInputMessageSource;
import source.test_sources.LogsJsonSource;
import source.test_sources.SeverityAssignerSource;
import state.StateDescriptors;
import timestamp_utils.InputMessageWatermarkStrategy;
import timestamp_utils.LogTimestampAndWatermarkStrategy;
import timestamp_utils.SeverityAssignerTimestampAndWatermarkStrategy;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;

public class LogProcessingWithCEP2 {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<InputMessage> inputMessageDataStream = env.addSource(new ExampleInputMessageSource())
                .assignTimestampsAndWatermarks(new InputMessageWatermarkStrategy())
                .keyBy(message -> message.getFieldValue("category"));


        Pattern<InputMessage, ?> anomalyPattern = Pattern.<InputMessage>begin("fewLowValues")
                .where(new SimpleCondition<InputMessage>() {
                    @Override
                    public boolean filter(InputMessage message) throws Exception {
                        return message.getFieldAsInteger("value") < 50;
                    }
                }).timesOrMore(4).consecutive()
                .next("anomalyCheck").where(new IterativeCondition<InputMessage>() {
                    @Override
                    public boolean filter(InputMessage inputMessage, Context<InputMessage> context) throws Exception {
                        Iterable<InputMessage> lowValues = context.getEventsForPattern("fewLowValues");
                        TDigest tDigest = new TreeDigest(100);
                        for (InputMessage message : lowValues) {
                            tDigest.add(message.getFieldAsInteger("value"));
                        }
                        double q3 = tDigest.quantile(0.75);
                        double q1 = tDigest.quantile(0.25);
                        double IQR = q3 - q1;
                        double lowerBound = q1 - 1.5 * IQR;
                        double upperBound = q3 + 1.5 * IQR;

                        int currentValue = inputMessage.getFieldAsInteger("value");
                        return currentValue > upperBound || currentValue < lowerBound;
                    }
                }).oneOrMore().consecutive();

        PatternStream<InputMessage> patternStream = CEP.pattern(inputMessageDataStream, anomalyPattern);

        patternStream.process(new PatternProcessFunction<InputMessage, String>() {
            @Override
            public void processMatch(Map<String, List<InputMessage>> map, Context context, Collector<String> collector) throws Exception {
                List<InputMessage> lowValueMessages = map.get("fewLowValues");
                List<InputMessage> anomalies = map.get("anomalyCheck");

                IntSummaryStatistics lowValuesStatistics = lowValueMessages.stream().mapToInt(m -> m.getFieldAsInteger("value")).summaryStatistics();
                IntSummaryStatistics anomaliesStatistics = anomalies.stream().mapToInt(m -> m.getFieldAsInteger("value")).summaryStatistics();


                collector.collect(String.format("%d anomalies were detected on category %s. The values of the anomalies are " +
                                "in the range [%d,%d]. The anomalies were preceded by %d low values in the range [%d, %d].",
                        anomaliesStatistics.getCount(),
                        anomalies.get(0).getFieldValue("category"),
                        anomaliesStatistics.getMin(),
                        anomaliesStatistics.getMax(),
                        lowValuesStatistics.getCount(),
                        lowValuesStatistics.getMin(),
                        lowValuesStatistics.getMax()
                ));
            }
        }).print();

        env.execute(LogProcessingWithCEP2.class.getName());


    }
}
