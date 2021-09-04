package exercice2;

import model.StudentRecord;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import source.test_sources.StudentDataStringSource;

public class FilterTask {
    public static void main(String[] args) throws Exception {

        /*
        Task: Filter only the student records for the course Object-oriented programming (code = OOP).
        * */

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<String> inputStream = env.addSource(new StudentDataStringSource());

        DataStream<StudentRecord> studentRecordDataStream = inputStream.map(StudentRecord::parse);

        DataStream<StudentRecord> studentsFromOOPStream = studentRecordDataStream
                .filter(record -> record.getCourse().equalsIgnoreCase("OOP"));

        studentsFromOOPStream.print();

        env.execute(FilterTask.class.getName());

    }
}
