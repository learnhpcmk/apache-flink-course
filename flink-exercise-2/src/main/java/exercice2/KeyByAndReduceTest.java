package exercice2;

import model.StudentRecord;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import source.test_sources.StudentDataStringSource;

public class KeyByAndReduceTest {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<String> inputStream = env.addSource(new StudentDataStringSource());

        DataStream<StudentRecord> studentRecordDataStream = inputStream.map(StudentRecord::parse);

        /*
        Task: Print the best student in each course whenever a new record for that course arrives.
        * */

        KeyedStream<StudentRecord, String> studentsByCourseStream = studentRecordDataStream.keyBy(StudentRecord::getCourse);

        SingleOutputStreamOperator<StudentRecord> result = studentsByCourseStream.reduce((value1, value2) -> (value1.compareTo(value2) >= 0) ? value1 : value2);

        result.print();

        env.execute(KeyByAndReduceTest.class.getName());
    }
}
