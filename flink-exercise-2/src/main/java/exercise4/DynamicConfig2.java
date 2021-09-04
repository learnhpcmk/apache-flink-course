package exercise4;

import com.google.gson.Gson;
import data.MatchedMessage;
import model.InputMessage;
import model.pojo.Log;
import model.pojo.RuleStatisticsResult;
import model.pojo.SeverityAssignerMessage;
import model.pojo.control.ControlMessage;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.BroadcastStream;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import process_functions.AssignSeverityToLogsProcess;
import process_functions.MatchingProcessFunction;
import source.test_sources.LogsJsonSource;
import source.test_sources.SeverityAssignerSource;
import source.test_sources.SeverityControlMessageSource;
import state.StateDescriptors;
import timestamp_utils.ControlMessageWatermarkStrategy;
import timestamp_utils.InputMessageWatermarkStrategy;
import timestamp_utils.LogTimestampAndWatermarkStrategy;
import timestamp_utils.SeverityAssignerTimestampAndWatermarkStrategy;
import window_utils.assigners.DynamicWindowAssigner;
import window_utils.functions.DynamicStatisticalAnalysisWindowFunction;

public class DynamicConfig2 {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        ParameterTool parameterTool = ParameterTool.fromArgs(args);
        Boolean eventTime = parameterTool.getBoolean("event-time", Boolean.TRUE);


        DataStream<Log> logsStream = env.addSource(new LogsJsonSource())
                .map(value -> new Gson().fromJson(value, Log.class))
                .assignTimestampsAndWatermarks(new LogTimestampAndWatermarkStrategy());

        SingleOutputStreamOperator<SeverityAssignerMessage> severitiesStream = env.addSource(new SeverityAssignerSource())
                .map(value -> new Gson().fromJson(value, SeverityAssignerMessage.class))
                .assignTimestampsAndWatermarks(new SeverityAssignerTimestampAndWatermarkStrategy());

        BroadcastStream<SeverityAssignerMessage> broadcastStream = severitiesStream.broadcast(StateDescriptors.BROADCAST_STATE_DESCRIPTOR);

        DataStream<Log> logsWithSeverityStream = logsStream.keyBy(Log::getSystem).connect(broadcastStream)
                .process(new AssignSeverityToLogsProcess());

//        logsWithSeverityStream.print();

        DataStream<ControlMessage> controlStream = env.addSource(new SeverityControlMessageSource())
                .map(json -> new Gson().fromJson(json, ControlMessage.class))
                .assignTimestampsAndWatermarks(new ControlMessageWatermarkStrategy());

        BroadcastStream<ControlMessage> controlBroadcastStream = controlStream.broadcast(StateDescriptors.RULES_BROADCAST_STATE_DESCRIPTOR);

//        controlStream.print();

        SingleOutputStreamOperator<RuleStatisticsResult> result = logsWithSeverityStream
                .filter(log -> log.getSeverity()!=null)
                .map(log -> new InputMessage(log.toString()))
                .assignTimestampsAndWatermarks(new InputMessageWatermarkStrategy(parameterTool))
                .connect(controlBroadcastStream)
                .process(new MatchingProcessFunction())
                .keyBy(MatchedMessage::getPartitioningKey)
                .window(new DynamicWindowAssigner(eventTime))
                .apply(new DynamicStatisticalAnalysisWindowFunction());
//
//        result.print();

        env.execute(DynamicConfig2.class.getName());
    }
}
