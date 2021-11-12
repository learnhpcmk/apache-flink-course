package exercise6;

import com.google.gson.Gson;
import model.pojo.Log;
import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.functions.PatternProcessFunction;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.cep.pattern.conditions.SimpleCondition;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;
import source.test_sources.LogsJsonSource;
import timestamp_utils.LogTimestampAndWatermarkStrategy;

import java.util.List;
import java.util.Map;

public class LogProcessingWithCEP {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<Log> logsStream = env.addSource(new LogsJsonSource())
                .map(value -> new Gson().fromJson(value, Log.class))
                .assignTimestampsAndWatermarks(new LogTimestampAndWatermarkStrategy())
                .keyBy(Log::getSystem);

        Pattern<Log, ?> pattern = Pattern.<Log>begin("moreThenThreeWarnings")
                .where(new SimpleCondition<Log>() {
                    @Override
                    public boolean filter(Log log) {
                        return log.getType().equalsIgnoreCase("warn");
                    }
                }).timesOrMore(3)
                .next("errorDetected").where(new SimpleCondition<Log>() {
                    @Override
                    public boolean filter(Log log) {
                        return log.getType().equalsIgnoreCase("error");
                    }
                }).times(1).within(Time.seconds(5));

        PatternStream<Log> patternStream = CEP.pattern(logsStream, pattern);

        patternStream.process(new PatternProcessFunction<Log, String>() {
            @Override
            public void processMatch(Map<String, List<Log>> map, Context context, Collector<String> collector) throws Exception {
                List<Log> warnings = map.get("moreThenThreeWarnings");
                List<Log> errors = map.get("errorDetected");
                Log error = errors.get(0);

                long start = warnings.stream().mapToLong(Log::getTimestamp).min().getAsLong();
                long end = warnings.stream().mapToLong(Log::getTimestamp).max().getAsLong();

                long timespanForWarnings = end-start;

                long timeBetweenWarningsAndError = error.getTimestamp() - end;

                collector.collect(String.format("An error was detected on the system %s at %d. The error was preceded by " +
                        "%s warnings in the timespan of %d ms. The error occurred %s ms after the last warning",
                        error.getSystem(),
                        error.getTimestamp(),
                        warnings.size(),
                        timespanForWarnings,
                        timeBetweenWarningsAndError));
            }
        }).print();

        env.execute(LogProcessingWithCEP.class.getName());
    }
}
