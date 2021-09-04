package source.test_sources;

import model.pojo.control.ControlMessage;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.Arrays;

public class ControlMessageStringSource implements SourceFunction<String> {

    public String [] messages = {
            "{\"user_id\":\"user1\",\"rule_id\":\"rule1\",\"rule\":{\"filters\":[{\"property\":\"category\",\"operator\":\"EQ\",\"value\":\"A\",\"value_type\":\"string\"},{\"property\":\"value\",\"operator\":\"GTEQ\",\"value\":\"50\",\"value_type\":\"number\"}]},\"statistics_property\":\"value\",\"window_configuration\":{\"type\":\"tumbling\",\"size\":3000}}",
            "{\"user_id\":\"user2\",\"rule_id\":\"rule1\",\"rule\":{\"filters\":[{\"property\":\"category\",\"operator\":\"EQ\",\"value\":\"B\",\"value_type\":\"string\"},{\"property\":\"value\",\"operator\":\"LTEQ\",\"value\":\"55\",\"value_type\":\"number\"}]},\"statistics_property\":\"value\",\"window_configuration\":{\"type\":\"sliding\",\"size\":3000,\"slide\":1500}}",
            "{\"user_id\":\"user2\",\"rule_id\":\"rule2\",\"rule\":{\"filters\":[{\"property\":\"category\",\"operator\":\"EQ\",\"value\":\"D\",\"value_type\":\"string\"},{\"property\":\"value\",\"operator\":\"LTEQ\",\"value\":\"90\",\"value_type\":\"number\"}]},\"statistics_property\":\"value\",\"window_configuration\":{\"type\":\"sliding\",\"size\":10000,\"slide\":4000}}"

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

    public static void main(String[] args) {
        String [] messages = {
                "{\"user_id\":\"user1\",\"rule_id\":\"rule1\",\"rule\":{\"filters\":[{\"property\":\"category\",\"operator\":\"EQ\",\"value\":\"A\",\"value_type\":\"string\"},{\"property\":\"value\",\"operator\":\"GTEQ\",\"value\":\"50\",\"value_type\":\"number\"}]},\"statistics_property\":\"value\",\"window_configuration\":{\"type\":\"tumbling\",\"size\":3000}}",
                "{\"user_id\":\"user2\",\"rule_id\":\"rule1\",\"rule\":{\"filters\":[{\"property\":\"category\",\"operator\":\"EQ\",\"value\":\"B\",\"value_type\":\"string\"},{\"property\":\"value\",\"operator\":\"LTEQ\",\"value\":\"55\",\"value_type\":\"number\"}]},\"statistics_property\":\"value\",\"window_configuration\":{\"type\":\"sliding\",\"size\":3000,\"slide\":1500}}",
                "{\"user_id\":\"user2\",\"rule_id\":\"rule2\",\"rule\":{\"filters\":[{\"property\":\"category\",\"operator\":\"EQ\",\"value\":\"D\",\"value_type\":\"string\"},{\"property\":\"value\",\"operator\":\"LTEQ\",\"value\":\"90\",\"value_type\":\"number\"}]},\"statistics_property\":\"value\",\"window_configuration\":{\"type\":\"sliding\",\"size\":10000,\"slide\":4000}}"

        };

        Arrays.stream(messages).forEach(message -> System.out.println(message));
    }
}
