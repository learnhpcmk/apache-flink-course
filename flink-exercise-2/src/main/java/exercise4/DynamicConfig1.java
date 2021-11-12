package exercise4;

import com.google.gson.Gson;
import data.MatchedMessage;
import model.InputMessage;
import model.pojo.RuleStatisticsResult;
import model.pojo.control.ControlMessage;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.BroadcastStream;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import process_functions.MatchingProcessFunction;
import source.test_sources.ControlMessageStringSource;
import source.test_sources.ExampleJsonSource;
import state.StateDescriptors;
import timestamp_utils.ControlMessageWatermarkStrategy;
import timestamp_utils.InputMessageWatermarkStrategy;
import window_utils.assigners.DynamicWindowAssigner;
import window_utils.functions.DynamicStatisticalAnalysisWindowFunction;

public class DynamicConfig1 {
    public static void main(String[] args) throws Exception {

        ParameterTool parameterTool = ParameterTool.fromArgs(args);
        Boolean eventTime = parameterTool.getBoolean("event_time", Boolean.TRUE);


        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        SingleOutputStreamOperator<InputMessage> inputStream = env.addSource(new ExampleJsonSource())
                .map(InputMessage::new)
                .assignTimestampsAndWatermarks(new InputMessageWatermarkStrategy(parameterTool));

        SingleOutputStreamOperator<ControlMessage> controlStream = env.addSource(new ControlMessageStringSource())
                .map(controlMessageStr -> new Gson().fromJson(controlMessageStr, ControlMessage.class))
                .assignTimestampsAndWatermarks(new ControlMessageWatermarkStrategy());

        BroadcastStream<ControlMessage> broadcastControlStream = controlStream.broadcast(StateDescriptors.RULES_BROADCAST_STATE_DESCRIPTOR);

        DataStream<MatchedMessage> matchedMessageDataStream = inputStream.connect(broadcastControlStream)
                .process(new MatchingProcessFunction());

        SingleOutputStreamOperator<RuleStatisticsResult> results = matchedMessageDataStream.keyBy(MatchedMessage::getPartitioningKey)
                .window(new DynamicWindowAssigner(eventTime))
                .apply(new DynamicStatisticalAnalysisWindowFunction());

        results.print();

        env.execute(DynamicConfig1.class.getName());
    }
}
