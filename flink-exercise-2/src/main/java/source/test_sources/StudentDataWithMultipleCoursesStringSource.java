package source.test_sources;

import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.*;

public class StudentDataWithMultipleCoursesStringSource implements SourceFunction<String> {
    public List<String> courses;
    public static Random random = new Random();
    public int nOfRecords;
    public Map<String, String> alreadyUsed;

    public StudentDataWithMultipleCoursesStringSource() {

        courses = Arrays.asList("SP", "VVI", "K1", "DM1", "PV", "AOK", "OOP", "DM2", "K2", "OVD", "KK");
        this.nOfRecords = 100;
        alreadyUsed = new HashMap<>();

    }

    private String createIndex() {
        return String.format("%d%d%03d",
                random.nextInt(6) + 15,
                random.nextInt(7) + 1,
                random.nextInt(900));
    }

    @Override
    public void run(SourceContext<String> ctx) throws Exception {
        for (int i = 0; i < nOfRecords; i++) {
            StringBuilder sb = new StringBuilder();
            String index = createIndex();
            sb.append(index).append(" ");
            int coursesCount = random.nextInt(5) + 2;
            for (int j = 0; j < coursesCount; j++) {
                String course = courses.get(random.nextInt(courses.size()));
                if (alreadyUsed.containsKey(index)) {
                    if (alreadyUsed.get(index).equalsIgnoreCase(course)) {
                        continue;
                    }
                }
                sb.append(String.format("%s %d ", course, random.nextInt(80) + 20));
                alreadyUsed.put(index, course);
            }


            ctx.collect(sb.toString());
            Thread.sleep(random.nextInt(300));
        }
    }

    @Override
    public void cancel() {

    }
}
