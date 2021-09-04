package exercise3;

import com.google.gson.Gson;
import model.pojo.Log;
import model.pojo.LogCountResult;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import source.test_sources.LogsJsonSource;
import timestamp_utils.LogTimestampAndWatermarkStrategy;
import window_utils.functions.LogCountingWindowFunction;

public class LogAnalysis3_2 {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<Log> logsStream = env.addSource(new LogsJsonSource())
                .map(value -> new Gson().fromJson(value, Log.class))
                .assignTimestampsAndWatermarks(new LogTimestampAndWatermarkStrategy());

        SingleOutputStreamOperator<LogCountResult> result = logsStream.keyBy(log -> String.format("%s___%s", log.getSystem(), log.getType()))
                .window(TumblingEventTimeWindows.of(Time.seconds(5)))
                .apply(new LogCountingWindowFunction());

        result.print();

        env.execute(LogAnalysis3_2.class.getName());
    }
}
