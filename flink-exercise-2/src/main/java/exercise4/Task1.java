package exercise4;

import com.google.gson.Gson;
import model.pojo.ErrorDeltaTimeResult;
import model.pojo.Log;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import process_functions.DifferenceBetweenErrorsCalculator;
import source.test_sources.LogsJsonSource;
import timestamp_utils.LogTimestampAndWatermarkStrategy;

public class Task1 {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<Log> logsStream = env.addSource(new LogsJsonSource())
                .map(value -> new Gson().fromJson(value, Log.class))
                .assignTimestampsAndWatermarks(new LogTimestampAndWatermarkStrategy());

        DataStream<ErrorDeltaTimeResult> result = logsStream.keyBy(Log::getSystem)
                .process(new DifferenceBetweenErrorsCalculator());

        result.print();

        env.execute(Task1.class.getName());

    }
}
