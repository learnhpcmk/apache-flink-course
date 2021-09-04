package exercice2;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import source.test_sources.StudentDataJsonStringSource;


public class IntroToDataSources {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<String> source;
        /*Reading from txt file
        source = env.readTextFile("/Users/stefanandonov/Documents/" +
                "flink-kurs/flink-exercise-2/src/main/java/data/students.txt");
        */

        /*Reading from hardcoded elements
        source = env.fromElements("151020", "151021", "151022", "151023");
         */

        /*Reading from collection
        int start = 151020;
        List<String> indexes = IntStream.range(0,100)
                .mapToObj(i -> String.valueOf(i+start))
                .collect(Collectors.toList());
        source = env.fromCollection(indexes);
         */

        /*Reading from a sequence of long numbers
        source = env.fromSequence(151020, 152000).map(Object::toString);
         */

//        Reading a simulated real-time stream from Source function
        source = env.addSource(new StudentDataJsonStringSource());


        source.print();


        env.execute("Exercise 1");
    }
}
