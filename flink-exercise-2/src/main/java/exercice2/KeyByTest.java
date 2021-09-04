package exercice2;

import model.StudentRecord;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import source.test_sources.StudentDataStringSource;

public class KeyByTest {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<String> inputStream = env.addSource(new StudentDataStringSource());

        DataStream<StudentRecord> studentRecordDataStream = inputStream.map(StudentRecord::parse);

        KeyedStream<StudentRecord, String> studentsByCourseStream = studentRecordDataStream.keyBy(StudentRecord::getCourse);

        studentsByCourseStream.print();

        env.execute(KeyByTest.class.getName());
    }
}
