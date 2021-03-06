package exercise5;

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
import source.factories.*;
import state.StateDescriptors;
import timestamp_utils.ControlMessageWatermarkStrategy;
import timestamp_utils.InputMessageWatermarkStrategy;
import timestamp_utils.MatchedMessageWatermarkStrategy;
import window_utils.assigners.DynamicWindowAssigner;
import window_utils.functions.DynamicStatisticalAnalysisWindowFunction;

public class DynamicConfigWithRabbitMq {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        ParameterTool parameterTool = ParameterTool.fromArgs(args);

        Boolean eventTime = parameterTool.getBoolean("event_time", Boolean.TRUE);

        DataStream<InputMessage> inputMessageDataStream = RabbitMqDataStream.getInputMessageDataStream(parameterTool,env) //smeneto tuka
                .assignTimestampsAndWatermarks(new InputMessageWatermarkStrategy(parameterTool));

        DataStream<ControlMessage> controlMessageDataStream = RabbitMqDataStream.getControlMessageDataStream(parameterTool,env) //smeneto tuka
                .assignTimestampsAndWatermarks(new ControlMessageWatermarkStrategy());


        BroadcastStream<ControlMessage> broadcastControlStream = controlMessageDataStream
                .broadcast(StateDescriptors.RULES_BROADCAST_STATE_DESCRIPTOR);

        SingleOutputStreamOperator<MatchedMessage> matchedMessages = inputMessageDataStream.connect(broadcastControlStream)
                .process(new MatchingProcessFunction())
                .assignTimestampsAndWatermarks(new MatchedMessageWatermarkStrategy(parameterTool));

//        matchedMessages.print();

        SingleOutputStreamOperator<RuleStatisticsResult> result = matchedMessages.keyBy(MatchedMessage::getPartitioningKey)
                .window(new DynamicWindowAssigner(eventTime))
                .apply(new DynamicStatisticalAnalysisWindowFunction());

        result.addSink(RabbitMqSinkStream.getRabbitMqSink(parameterTool));

        env.execute(DynamicConfigWithRabbitMq.class.getName());
    }
}
