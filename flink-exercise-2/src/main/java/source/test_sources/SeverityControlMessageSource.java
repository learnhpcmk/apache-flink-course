package source.test_sources;

import org.apache.flink.streaming.api.functions.source.SourceFunction;

public class SeverityControlMessageSource implements SourceFunction<String> {

    public String [] messages = {
            "{\"user_id\":\"user1\",\"rule_id\":\"rule1\",\"rule\":{\"filters\":[{\"property\":\"severity\",\"operator\":\"GTEQ\",\"value\":\"6\",\"value_type\":\"number\"}]},\"statistics_property\":\"severity\",\"window_configuration\":{\"type\":\"tumbling\",\"size\":5000}}",
            "{\"user_id\":\"user1\",\"rule_id\":\"rule1\",\"rule\":{\"filters\":[{\"property\":\"system\",\"operator\":\"EQ\",\"value\":\"B\",\"value_type\":\"string\"}]},\"statistics_property\":\"severity\",\"window_configuration\":{\"type\":\"sliding\",\"size\":10000,\"slide\":2500}}",
            "{\"user_id\":\"user1\",\"rule_id\":\"rule1\",\"rule\":{\"filters\":[{\"property\":\"system\",\"operator\":\"EQ\",\"value\":\"C\",\"value_type\":\"string\"}]},\"statistics_property\":\"severity\",\"window_configuration\":{\"type\":\"tumbling\",\"size\":10000}}"
    };


    @Override
    public void run(SourceContext<String> ctx) throws Exception {
        Thread.sleep(2000);
        for (String message: messages) {
            ctx.collect(message);
            Thread.sleep(5000);
        }
    }

    @Override
    public void cancel() {

    }
}
