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
