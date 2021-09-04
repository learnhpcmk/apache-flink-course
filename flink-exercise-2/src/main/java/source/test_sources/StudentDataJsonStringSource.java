package source.test_sources;

import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.*;

public class StudentDataJsonStringSource implements SourceFunction<String> {

    public List<String> courses;
    public static Random random = new Random();
    public int nOfRecords;
    public Map<String, String> alreadyUsed;

    public StudentDataJsonStringSource() {

        courses = Arrays.asList("SP", "VVI", "K1", "DM1", "PV", "AOK", "OOP", "DM2", "K2", "OVD", "KK");
        this.nOfRecords = 100;
        alreadyUsed = new HashMap<>();

    }

    private String createIndex() {
        return String.format("%d%d%03d",
                random.nextInt(6)+15,
                random.nextInt(7)+1,
                random.nextInt(900));
    }

    @Override
    public void run(SourceContext<String> ctx) throws Exception {
        for (int i = 0; i < nOfRecords; i++) {
            String index = createIndex();
            String course = courses.get(random.nextInt(courses.size()));
            if (alreadyUsed.containsKey(index)) {
                if (alreadyUsed.get(index).equalsIgnoreCase(course)) {
                    continue;
                }
            }
            ctx.collect(String.format("{\"index\": \"%s\", \"course\": \"%s\", \"points\": %d}", index, course, random.nextInt(80)+20));
            alreadyUsed.put(index, course);
            Thread.sleep(random.nextInt(300));
        }
    }

    @Override
    public void cancel() {

    }
}
