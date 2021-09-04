package exercice2;

import model.StudentRecord;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import source.test_sources.StudentDataStringSource;

import java.util.DoubleSummaryStatistics;

public class WindowApplyOnKeyedStreamTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<String> inputStream = env.addSource(new StudentDataStringSource());

        DataStream<StudentRecord> studentRecordDataStream = inputStream.map(StudentRecord::parse);

        KeyedStream<StudentRecord, String> studentsByCourseStream = studentRecordDataStream.keyBy(StudentRecord::getCourse);

        DataStream<String> result = studentsByCourseStream
                .window(TumblingProcessingTimeWindows.of(Time.milliseconds(3000)))
                .apply(new WindowFunction<StudentRecord, String, String, TimeWindow>() {
                    @Override
                    public void apply(String key, TimeWindow window, Iterable<StudentRecord> windowData, Collector<String> out) {
                        DoubleSummaryStatistics statistics = new DoubleSummaryStatistics();
                        windowData.forEach(studentRecord -> statistics.accept(studentRecord.getPoints()));
                        out.collect(String.format("Statistics for the course %s in the interval [%d, %d] is %s",
                                key,
                                window.getStart(),
                                window.getEnd(),
                                statistics.toString()));
                    }
                });

        result.print();

        env.execute(KeyByTest.class.getName());
    }
}
