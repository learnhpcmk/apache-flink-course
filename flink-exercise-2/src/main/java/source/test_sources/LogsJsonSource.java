package source.test_sources;

import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.json.JSONObject;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LogsJsonSource implements SourceFunction<String> {

    int n = 5000;

    Random random = new Random();
    List<String> logTypes = Arrays.asList("ERROR", "INFO", "WARN", "DEBUG");
    List<String> systems = Arrays.asList("A", "B", "C", "D", "E", "F");
    boolean delay;
    boolean lateEvents;

    public LogsJsonSource() {
        this.delay = false;
    }

    public LogsJsonSource(boolean delay) {
        this.delay = delay;
    }


    public LogsJsonSource(boolean delay, boolean lateEvents) {
        this.delay = delay;
        this.lateEvents = lateEvents;
    }

    @Override
    public void run(SourceContext<String> ctx) throws Exception {
        for (int i = 0; i < n; i++) {
            String type = logTypes.get(random.nextInt(logTypes.size()));
            String system = systems.get(random.nextInt(systems.size()));
            long timestamp = Instant.now().toEpochMilli();
            if (lateEvents && i % 50 == 0) {
                timestamp = LocalDateTime.now().minusDays(10).minusHours(5).minusSeconds(100)
                        .atZone(ZoneId.systemDefault())
                        .toInstant()
                        .toEpochMilli();
            }
            String message = "";
            switch (type) {
                case "ERROR":
                    message = String.format("An error occurred in system %s at %s",
                            system,
                            Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault())
                                    .toLocalDateTime().toString()
                    );
                    break;
                case "INFO":
                    message = String.format("Package of %d bytes was sent", random.nextInt(5000) + 4000);
                    break;
                case "WARN":
                    message = String.format("Memory is low on cluster %s", system);
                    break;
                case "DEBUG":
                    message = String.format("Failed to start cluster %s. Retrying again.", system);
                    break;
            }

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type", type);
            jsonObject.put("timestamp", timestamp);
            jsonObject.put("message", message);
            jsonObject.put("system", system);
            ctx.collect(jsonObject.toString());

            Thread.sleep((delay && i % 100 == 0) ? random.nextInt(1000) + 2100 : random.nextInt(200) + 50);
        }

    }

    @Override
    public void cancel() {

    }
}
