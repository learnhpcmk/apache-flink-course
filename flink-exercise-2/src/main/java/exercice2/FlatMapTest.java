package exercice2;

import mappers.StringToStudentRecordForMultipleCoursesMapper;
import model.StudentRecord;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import source.test_sources.StudentDataWithMultipleCoursesStringSource;

public class FlatMapTest {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<String> inputStream = env.addSource(new StudentDataWithMultipleCoursesStringSource());

        /*TODO
        Task: The input now has a different format i.e the student record contains info for the students points in several
        courses. Per ex. 173500 KK 84 AOK 20 OOP 38 DM2 33  (this record contains info for the students points in 4 courses).
        The goal is to map this stream of strings into a stream of object of class StudentRecord with the operator flatMap.
        * */

        DataStream<StudentRecord> studentRecordDataStream = inputStream
                .flatMap(new StringToStudentRecordForMultipleCoursesMapper());

        studentRecordDataStream.print();

        env.execute(FlatMapTest.class.getName());
    }
}
