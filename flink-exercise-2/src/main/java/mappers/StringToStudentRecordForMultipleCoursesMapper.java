package mappers;

import model.StudentRecord;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;

public class StringToStudentRecordForMultipleCoursesMapper implements FlatMapFunction<String, StudentRecord> {
    @Override
    public void flatMap(String value, Collector<StudentRecord> out) throws Exception {
        StudentRecord.parseWithMultipleCoursesInfo(value)
                .forEach(out::collect);
    }
}
