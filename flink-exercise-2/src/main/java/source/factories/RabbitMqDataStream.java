package source.factories;

import model.InputMessage;
import model.pojo.control.ControlMessage;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.rabbitmq.RMQSource;
import org.apache.flink.streaming.connectors.rabbitmq.common.RMQConnectionConfig;
import settings.ProjectSettings;
import source.schemas.ControlMessageDeserializationSchema;
import source.schemas.InputMessageDeserializationSchema;

public class RabbitMqDataStream {

    static final RMQConnectionConfig connectionConfig = new RMQConnectionConfig.Builder()
            .setHost("localhost")
            .setPort(5672)
            .setUserName("guest")
            .setPassword("guest")
            .setVirtualHost("/")
            .build();

    public static DataStream<InputMessage> getInputMessageDataStream (ParameterTool parameterTool,
                                                               StreamExecutionEnvironment env) {
        String queue = parameterTool.get(
                ProjectSettings.MESSAGE_QUEUE,
                ProjectSettings.DEFAULT_MESSAGE_QUEUE
        );

        return env.addSource(new RMQSource<>(
                connectionConfig,
                queue,
                true,
                new InputMessageDeserializationSchema()
        ));

    }

    public static DataStream<ControlMessage> getControlMessageDataStream (ParameterTool parameterTool,
                                                                 StreamExecutionEnvironment env) {
        String queue = parameterTool.get(
                ProjectSettings.CONTROL_QUEUE,
                ProjectSettings.DEFAULT_CONTROL_QUEUE
        );

        return env.addSource(new RMQSource<>(
                connectionConfig,
                queue,
                true,
                new ControlMessageDeserializationSchema()
        ));
    }
}
