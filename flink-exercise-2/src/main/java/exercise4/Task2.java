package exercise4;

import com.google.gson.Gson;
import model.pojo.Log;
import model.pojo.SystemsListByLogTypeResult;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import process_functions.SystemListingProcess;
import process_functions.SystemListingProcessImproved;
import source.test_sources.LogsJsonSource;
import timestamp_utils.LogTimestampAndWatermarkStrategy;

public class Task2 {
    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<Log> logsStream = env.addSource(new LogsJsonSource())
                .map(value -> new Gson().fromJson(value, Log.class))
                .assignTimestampsAndWatermarks(new LogTimestampAndWatermarkStrategy());

        DataStream<SystemsListByLogTypeResult> result = logsStream.keyBy(Log::getType)
                .process(new SystemListingProcessImproved());

        result.print();

        env.execute(Task2.class.getName());
    }
}
