package exercise3;

import window_utils.aggregates.ErrorPercentageAggregateFunction;
import com.google.gson.Gson;
import model.pojo.ErrorPercentageResult;
import model.pojo.Log;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import source.test_sources.LogsJsonSource;
import timestamp_utils.LogTimestampAndWatermarkStrategy;

/**
 * Task2: Get the percentage of ERROR logs on each system on every 3 seconds.
 * The output should be in the format {"system":"F","percentage":0.0}
 */
public class LogAnalysis2 {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<Log> logsStream = env.addSource(new LogsJsonSource())
                .map(value -> new Gson().fromJson(value, Log.class))
                .assignTimestampsAndWatermarks(new LogTimestampAndWatermarkStrategy());

        SingleOutputStreamOperator<ErrorPercentageResult> result = logsStream.keyBy(Log::getSystem)
                .window(TumblingEventTimeWindows.of(Time.seconds(3)))
                .aggregate(new ErrorPercentageAggregateFunction());

        result.print();

        env.execute(LogAnalysis2.class.getName());

    }
}
