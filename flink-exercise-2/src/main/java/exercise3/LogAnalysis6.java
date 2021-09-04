package exercise3;

import window_utils.aggregates.LogTypePercentageAggregateFunction;
import com.google.gson.Gson;
import model.pojo.Log;
import model.pojo.LogTypePercentagesResult;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.GlobalWindows;
import source.test_sources.LogsJsonSource;
import timestamp_utils.LogTimestampAndWatermarkStrategy;
import window_utils.triggers.ErrorTrigger;

public class LogAnalysis6 {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<Log> logsStream = env.addSource(new LogsJsonSource())
                .map(value -> new Gson().fromJson(value, Log.class))
                .assignTimestampsAndWatermarks(new LogTimestampAndWatermarkStrategy());

        SingleOutputStreamOperator<LogTypePercentagesResult> result = logsStream.keyBy(Log::getSystem)
                .window(GlobalWindows.create())
                .trigger(new ErrorTrigger())
                .aggregate(new LogTypePercentageAggregateFunction());

        result.print();

        env.execute(LogAnalysis5.class.getName());
    }
}
