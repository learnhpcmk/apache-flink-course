package source.factories;

import model.InputMessage;
import model.pojo.control.ControlMessage;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import settings.ProjectSettings;
import source.schemas.ControlMessageDeserializationSchema;
import source.schemas.InputMessageDeserializationSchema;
import timestamp_utils.ControlMessageWatermarkStrategy;
import timestamp_utils.InputMessageWatermarkStrategy;

import java.util.regex.Pattern;

public class KafkaDataStream {
    static DataStream<InputMessage> getDataStream(ParameterTool parameterTool,
                                                  StreamExecutionEnvironment env) {
        String topic = parameterTool.get(
                ProjectSettings.DEFAULT_MESSAGE_TOPIC_NAME,
                ProjectSettings.DEFAULT_MESSAGE_TOPIC
        );

        String brokers = parameterTool.get(
                "bootstrap.servers",
                "localhost:9092"
        );

        boolean eventTime = parameterTool.getBoolean(
                ProjectSettings.EVENT_TIME_PARAMETER,
                ProjectSettings.DEFAULT_EVENT_TIME
        );

        String timestampProperty = parameterTool.get(
                ProjectSettings.TIMESTAMP_PROPERTY_NAME,
                ProjectSettings.DEFAULT_TIMESTAMP_PROPERTY
        );

//        KafkaSource<InputMessage> source = KafkaSource.<InputMessage>builder()
//                .setBootstrapServers(brokers)
//                .setTopics(topic)
//                .setGroupId("my-group")
//                .setStartingOffsets(OffsetsInitializer.earliest())
//                .setValueOnlyDeserializer(new InputMessageDeserializationSchema())
//                .build();
//
//        return env.fromSource(source,
//                new InputMessageWatermarkStrategy(eventTime, timestampProperty),
//                "Input Message Source");

        FlinkKafkaConsumer<InputMessage> kafkaConsumer = new FlinkKafkaConsumer<>(
                topic,
                new InputMessageDeserializationSchema(),
                parameterTool.getProperties());

        return env.addSource(kafkaConsumer);
    }

    static DataStream<ControlMessage> getControlStream(ParameterTool parameterTool,
                                                       StreamExecutionEnvironment env) {
        String topic = parameterTool.get(
                ProjectSettings.DEFAULT_CONTROL_TOPIC_NAME,
                ProjectSettings.DEFAULT_CONTROL_TOPIC
        );

        String brokers = parameterTool.get(
                "bootstrap.servers",
                "localhost:9092"
        );

//        KafkaSource<ControlMessage> source = KafkaSource.<ControlMessage>builder()
//                .setBootstrapServers(brokers)
//                .setTopics(topic)
//                .setGroupId("my-group")
//                .setStartingOffsets(OffsetsInitializer.earliest())
//                .setValueOnlyDeserializer(new ControlMessageDeserializationSchema())
//                .build();
//
//        return env.fromSource(source,
//                new ControlMessageWatermarkStrategy(),
//                "Control Message Source");

        FlinkKafkaConsumer<ControlMessage> kafkaConsumer = new FlinkKafkaConsumer<>(
                topic,
                new ControlMessageDeserializationSchema(),
                parameterTool.getProperties());

        return env.addSource(kafkaConsumer);
    }
}
