package source.test_sources;

import model.pojo.Severity;
import model.pojo.SeverityAssignerMessage;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SeverityAssignerSource implements SourceFunction<String> {

    int n = 50;
    List<String> logTypes = Arrays.asList("ERROR", "INFO", "WARN", "DEBUG");
    List<String> systems = Arrays.asList("A", "B", "C", "D", "E", "F");
    Random random = new Random();

    @Override
    public void run(SourceContext<String> ctx) throws Exception {
        for (int i = 0; i < n; i++) {
            String system = systems.get(random.nextInt(systems.size()));
            List<Severity> severities = new ArrayList<>();
            severities.add(new Severity(
                    "INFO",
                    (long) random.nextInt(3)
            ));

            severities.add(new Severity(
                    "WARN",
                    (long) random.nextInt(3)+2
            ));

            severities.add(new Severity(
                    "DEBUG",
                    (long) random.nextInt(3)+5
            ));

            severities.add(new Severity(
                    "ERROR",
                    (long) random.nextInt(3)+8
            ));


            SeverityAssignerMessage message = new SeverityAssignerMessage(system, severities);

            ctx.collect(message.toString());
            Thread.sleep(random.nextInt(4000)+2000);
        }
    }

    @Override
    public void cancel() {

    }
}
