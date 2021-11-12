package source.factories;

import model.InputMessage;
import model.pojo.RuleStatisticsResult;
import org.apache.flink.api.connector.sink.Sink;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.connectors.rabbitmq.RMQSink;
import org.apache.flink.streaming.connectors.rabbitmq.RMQSource;
import org.apache.flink.streaming.connectors.rabbitmq.common.RMQConnectionConfig;
import settings.ProjectSettings;
import source.schemas.InputMessageDeserializationSchema;
import source.schemas.RuleStatisticsResultSerializationSchema;

public class RabbitMqSinkStream {

    static final RMQConnectionConfig connectionConfig = new RMQConnectionConfig.Builder()
            .setHost("localhost")
            .setPort(5672)
            .setUserName("guest")
            .setPassword("guest")
            .setVirtualHost("/")
            .build();

    public static SinkFunction<RuleStatisticsResult> getRabbitMqSink(ParameterTool parameterTool){
        String queue = parameterTool.get(
                ProjectSettings.RESULT_QUEUE,
                ProjectSettings.DEFAULT_RESULT_QUEUE
        );

        return new RMQSink<>(
                connectionConfig,
                queue,
                new RuleStatisticsResultSerializationSchema()
        );
    }
}
