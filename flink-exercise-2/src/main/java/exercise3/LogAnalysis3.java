package exercise3;

import window_utils.aggregates.LogCountingAggregateFunction;
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

public class LogAnalysis3 {
    /*
    Example output: {"system":"D","log_type":"ERROR","count":1}
    * */
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<Log> logsStream = env.addSource(new LogsJsonSource())
                .map(value -> new Gson().fromJson(value, Log.class))
                .assignTimestampsAndWatermarks(new LogTimestampAndWatermarkStrategy());

        SingleOutputStreamOperator<LogCountResult> result = logsStream.keyBy(log -> String.format("%s___%s", log.getSystem(), log.getType()))
                .window(TumblingEventTimeWindows.of(Time.seconds(5)))
                .aggregate(new LogCountingAggregateFunction());

        result.print();

        env.execute(LogAnalysis3.class.getName());

    }
}
