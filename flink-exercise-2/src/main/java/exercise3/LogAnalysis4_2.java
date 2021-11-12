package exercise3;

import com.google.gson.Gson;
import model.pojo.Log;
import model.pojo.LogTypePercentagesResult;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.SlidingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import source.test_sources.LogsJsonSource;
import timestamp_utils.LogTimestampAndWatermarkStrategy;
import window_utils.functions.LogTypePercentageWindowFunction;

public class LogAnalysis4_2 {
    /*
    Example of the output.
    {"system":"A","count":6,"start_timestamp":1632242770000,"end_timestamp":1632242780000,"log_type_percentages":[{"log_type":"INFO","percentage":16.666666666666668},{"log_type":"DEBUG","percentage":16.666666666666668},{"log_type":"WARN","percentage":66.66666666666667}]}
     */
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<Log> logsStream = env.addSource(new LogsJsonSource())
                .map(value -> new Gson().fromJson(value, Log.class))
                .assignTimestampsAndWatermarks(new LogTimestampAndWatermarkStrategy());

        SingleOutputStreamOperator<LogTypePercentagesResult> result = logsStream.keyBy(Log::getSystem)
                .window(SlidingEventTimeWindows.of(Time.seconds(10), Time.seconds(2)))
                .apply(new LogTypePercentageWindowFunction());

        result.print();

        env.execute(LogAnalysis4_2.class.getName());
    }
}
