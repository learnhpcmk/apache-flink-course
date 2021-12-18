package exam;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

public class Example1 {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<String> stream = env.addSource(new RandomStringSource());
//                .assignTimestampsAndWatermarks()

        //DO SOMETHING
        DataStream<Integer> newStream = stream.flatMap(new FlatMapFunction<String, Character>() {
            @Override
            public void flatMap(String value, Collector<Character> out) throws Exception {
                value.chars()
                        .mapToObj(i -> (char) i)
                        .forEach(out::collect);
            }
        })
                .filter(Character::isUpperCase)
                .keyBy(c -> c)
                .process(new KeyedProcessFunction<Character, Character, Integer>() {
                    @Override
                    public void processElement(Character value, Context ctx, Collector<Integer> out) throws Exception {
                        //DO SOMETHING
                    }
                });


        env.execute(Example1.class.getName());
    }
}


