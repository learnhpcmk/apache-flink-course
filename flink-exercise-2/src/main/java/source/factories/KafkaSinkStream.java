package source.factories;

import model.pojo.RuleStatisticsResult;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import settings.ProjectSettings;
import source.schemas.RuleStatisticsResultSerializationSchema;

public class KafkaSinkStream {

    public static SinkFunction<RuleStatisticsResult> getKafkaSink (ParameterTool parameterTool) {
        String resultTopic = parameterTool.get(
                ProjectSettings.DEFAULT_RESULT_SINK_TOPIC_NAME,
                ProjectSettings.DEFAULT_RESULT_SINK_TOPIC
        );

        return new FlinkKafkaProducer<>(
                resultTopic,
                new RuleStatisticsResultSerializationSchema(),
                parameterTool.getProperties()
        );
    }
}
