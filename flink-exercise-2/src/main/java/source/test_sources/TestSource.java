package source.test_sources;

import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.time.LocalDateTime;
import java.util.Random;

public class TestSource implements SourceFunction<String> {
    public void run(SourceContext<String> sourceContext) throws Exception {
        Random random = new Random();
        for (int i=0;i<100;i++){
            sourceContext.collect(String.format("Value%d %s", i, LocalDateTime.now().toString()));
            Thread.sleep(random.nextInt(1000)+500);
        }

    }

    public void cancel() {
        //DO NOTHING
    }
}
