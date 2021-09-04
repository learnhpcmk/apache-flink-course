package source.test_sources;

import model.InputMessage;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.json.JSONObject;

import java.util.Random;

public class ExampleInputMessageSource implements SourceFunction<InputMessage> {
    int n = 1000;
    Random random = new Random();

    @Override
    public void run(SourceContext<InputMessage> ctx) throws Exception {
        for (int i=0;i<n;i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("category", String.valueOf((char)('A' + random.nextInt(10))));
            jsonObject.put("value", random.nextInt(80) + 20);
            jsonObject.put("timestamp", System.currentTimeMillis());
            ctx.collect(new InputMessage(jsonObject.toString()));
            Thread.sleep(random.nextInt(200)+100);
        }
    }

    @Override
    public void cancel() {

    }
}
