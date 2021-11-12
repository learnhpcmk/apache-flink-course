package training_course;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

import java.util.Arrays;
import java.util.function.Function;

public class WordCountJob {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<String> wordsStream = env.readTextFile("src/main/java/training_course/words");

        wordsStream
                .map(word -> new WordCount(word.toLowerCase()))
                .keyBy(WordCount::getWord)
                .reduce((ReduceFunction<WordCount>) WordCount::reduce)
                .print();



        env.execute("Word Count");
    }
}
