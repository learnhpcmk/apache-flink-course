package source.schemas;

import model.pojo.RuleStatisticsResult;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.streaming.util.serialization.KeyedSerializationSchema;

public class RuleStatisticsResultSerializationSchema implements SerializationSchema<RuleStatisticsResult> {
    @Override
    public byte[] serialize(RuleStatisticsResult element) {
        return element.toString().getBytes();
    }

}
