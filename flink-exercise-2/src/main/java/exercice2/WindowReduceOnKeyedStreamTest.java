package exercice2;

import model.StudentRecord;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.WindowedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import source.test_sources.StudentDataStringSource;

public class WindowReduceOnKeyedStreamTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<String> inputStream = env.addSource(new StudentDataStringSource());

        DataStream<StudentRecord> studentRecordDataStream = inputStream.map(StudentRecord::parse);

        KeyedStream<StudentRecord, String> studentsByCourseStream = studentRecordDataStream.keyBy(StudentRecord::getCourse);

        /*
        Task: Find the best students on a course in a given time period
        * */


        //1,2,3,4,5,6,7,8,9,10 --> [1,2,3], [4,5,6], [7,8,9], [10] tumbling
        //1,2,3,4,5,6,7,8,9,10 --> [1,2,3,4], [2,3,4,5], [3,4,5,6], [4,5,6,7],..., [7,8,9,10]

        WindowedStream<StudentRecord, String, TimeWindow> windowedStudentStream = studentsByCourseStream.window(TumblingProcessingTimeWindows.of(Time.seconds(4)));

        windowedStudentStream.reduce(((value1, value2) -> (value1.compareTo(value2) >= 0) ? value1 : value2))
                .print();

        env.execute(WindowReduceOnKeyedStreamTest.class.getName());
    }
}
