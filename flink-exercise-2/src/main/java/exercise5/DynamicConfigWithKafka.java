package exercise5;

import data.MatchedMessage;
import model.InputMessage;
import model.pojo.RuleStatisticsResult;
import model.pojo.control.ControlMessage;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.connector.sink.Sink;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.BroadcastStream;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import process_functions.MatchingProcessFunction;
import source.factories.SinkFactory;
import source.factories.SourceControlStreamFactory;
import source.factories.SourceDataStreamFactory;
import state.StateDescriptors;
import timestamp_utils.ControlMessageWatermarkStrategy;
import timestamp_utils.InputMessageWatermarkStrategy;
import timestamp_utils.MatchedMessageWatermarkStrategy;
import window_utils.assigners.DynamicWindowAssigner;
import window_utils.functions.DynamicStatisticalAnalysisWindowFunction;

public class DynamicConfigWithKafka {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        ParameterTool parameterTool = ParameterTool.fromArgs(args);

        Boolean eventTime = parameterTool.getBoolean("event_time", Boolean.TRUE);

        DataStream<InputMessage> inputMessageDataStream = SourceDataStreamFactory.getStream(parameterTool, env)
                .assignTimestampsAndWatermarks(new InputMessageWatermarkStrategy(parameterTool));

//        inputMessageDataStream.print();

        DataStream<ControlMessage> controlMessageDataStream = SourceControlStreamFactory.getStream(parameterTool, env)
                .assignTimestampsAndWatermarks(new ControlMessageWatermarkStrategy());

//        controlMessageDataStream.print();

        BroadcastStream<ControlMessage> broadcastControlStream = controlMessageDataStream
                .broadcast(StateDescriptors.RULES_BROADCAST_STATE_DESCRIPTOR);

        SingleOutputStreamOperator<MatchedMessage> matchedMessages = inputMessageDataStream.connect(broadcastControlStream)
                .process(new MatchingProcessFunction())
                .assignTimestampsAndWatermarks(new MatchedMessageWatermarkStrategy(parameterTool));

//        matchedMessages.print();

        SingleOutputStreamOperator<RuleStatisticsResult> result = matchedMessages.keyBy(MatchedMessage::getPartitioningKey)
                .window(new DynamicWindowAssigner(eventTime))
                .apply(new DynamicStatisticalAnalysisWindowFunction());

        result.addSink(SinkFactory.getSink(parameterTool));

        env.execute(DynamicConfigWithKafka.class.getName());
    }
}
