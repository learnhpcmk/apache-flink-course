package exercice2;

import model.StudentRecord;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import source.test_sources.StudentDataStringSource;

public class MapTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<String> inputStream = env.addSource(new StudentDataStringSource());

        /*
        Task: Map the input stream of Strings into a Data Stream of objects of class Student.
        Each string contains info for the students ID, course and the achieved points in the course.
        The info is delimited by a single space.
        * */

        DataStream<StudentRecord> studentRecordDataStream = inputStream.map(StudentRecord::parse);

        studentRecordDataStream.print();

        env.execute(MapTest.class.getName());

    }
}
