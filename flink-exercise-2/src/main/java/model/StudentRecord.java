package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StudentRecord implements Comparable<StudentRecord> {
    String index;
    String course;
    int points;

    public StudentRecord(String index, String course, int points) {
        this.index = index;
        this.course = course;
        this.points = points;
    }

    public static StudentRecord parse(String data) {
        String[] parts = data.split("\\s+");
        return new StudentRecord(parts[0], parts[1], Integer.parseInt(parts[2]));
    }

    public static List<StudentRecord> parseWithMultipleCoursesInfo(String data) {
        List<StudentRecord> result = new ArrayList<>();
        String[] parts = data.split("\\s+");
        String index = parts[0];
        for (int i = 1; i < parts.length; i++) {
            if (i % 2 == 1) {
                String course = parts[i];
                int point = Integer.parseInt(parts[i + 1]);
                result.add(new StudentRecord(index, course, point));
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "StudentRecord{" +
                "index='" + index + '\'' +
                ", course='" + course + '\'' +
                ", points=" + points +
                '}';
    }

    public String getIndex() {
        return index;
    }

    public String getCourse() {
        return course;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public int compareTo(StudentRecord o) {
        Comparator<StudentRecord> comparator = Comparator.comparing(StudentRecord::getPoints)
                .thenComparing(StudentRecord::getIndex);

        return comparator.compare(this, o);
    }
}
