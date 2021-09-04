package exercise4;

import com.google.gson.Gson;
import model.pojo.Log;
import model.pojo.SeverityAssignerMessage;
import model.pojo.SeverityStatisticsResult;
import org.apache.flink.streaming.api.datastream.BroadcastStream;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import process_functions.AssignSeverityToLogsProcess;
import source.test_sources.LogsJsonSource;
import source.test_sources.SeverityAssignerSource;
import state.StateDescriptors;
import timestamp_utils.LogTimestampAndWatermarkStrategy;
import timestamp_utils.SeverityAssignerTimestampAndWatermarkStrategy;
import window_utils.functions.SeverityStatisticsWindowFunction;

public class Task4 {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<Log> logsStream = env.addSource(new LogsJsonSource())
                .map(value -> new Gson().fromJson(value, Log.class))
                .assignTimestampsAndWatermarks(new LogTimestampAndWatermarkStrategy());

        SingleOutputStreamOperator<SeverityAssignerMessage> severitiesStream = env.addSource(new SeverityAssignerSource())
                .map(value -> new Gson().fromJson(value, SeverityAssignerMessage.class))
                .assignTimestampsAndWatermarks(new SeverityAssignerTimestampAndWatermarkStrategy());

        BroadcastStream<SeverityAssignerMessage> broadcastStream = severitiesStream.broadcast(StateDescriptors.BROADCAST_STATE_DESCRIPTOR);

        DataStream<Log> logsWithSeverityStream = logsStream.keyBy(Log::getSystem).connect(broadcastStream)
                .process(new AssignSeverityToLogsProcess());


        DataStream<SeverityStatisticsResult> result = logsWithSeverityStream
                .filter(log -> log.getSeverity()!=null)
                .keyBy(Log::getSystem)
                .window(TumblingEventTimeWindows.of(Time.seconds(5)))
                .apply(new SeverityStatisticsWindowFunction());

        result.print();


        env.execute(Task3.class.getName());


    }
}
