package exercice2;

import model.StudentRecord;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.AllWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import source.test_sources.StudentDataStringSource;

import java.util.*;

public class WindowAllApplyOnRegularDataStreamTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<String> inputStream = env.addSource(new StudentDataStringSource());

        DataStream<StudentRecord> studentRecordDataStream = inputStream.map(StudentRecord::parse);



        DataStream<String> result = studentRecordDataStream
                .windowAll(TumblingProcessingTimeWindows.of(Time.seconds(1)))
                .apply(new AllWindowFunction<StudentRecord, String, TimeWindow>() {
                    @Override
                    public void apply(TimeWindow window, Iterable<StudentRecord> windowValues, Collector<String> out) throws Exception {
                        DoubleSummaryStatistics statistics = new DoubleSummaryStatistics();
                        Set<String> courses = new LinkedHashSet<>();
                        for (StudentRecord record : windowValues){
                            statistics.accept(record.getPoints());
                            courses.add(record.getCourse());
                        }
                        String allCoursesString = String.join(", ", courses);
                        out.collect(String.format("In the interval [%d, %d] there were %d points from the courses %s. Average points during the period is %.2f",
                                window.getStart(),
                                window.getEnd(),
                                statistics.getCount(),
                                allCoursesString,
                                statistics.getAverage())
                        );

                    }
                });

        result.print();

        env.execute(WindowAllApplyOnRegularDataStreamTest.class.getName());
    }
}
