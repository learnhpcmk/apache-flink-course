package training_course;

import com.google.gson.Gson;
import model.InputMessage;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.datastream.WindowedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import source.test_sources.ExampleJsonSource;
import timestamp_utils.InputMessageWatermarkStrategy;

public class StaticStatisticalAnalysis {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        ParameterTool parameterTool = ParameterTool.fromArgs(args);

        SingleOutputStreamOperator<InputMessage> inputJsonMessages = env.addSource(new ExampleJsonSource())
                .map(InputMessage::new)
                .assignTimestampsAndWatermarks(new InputMessageWatermarkStrategy(parameterTool));

        KeyedStream<InputMessage, String> keyedStream = inputJsonMessages
                .keyBy(inputMessage -> inputMessage.getFieldValue("category")); //Is this ok? Can the user change this in runtime?
        //What if the user wants to partition the data based on more then one criteria/rule?

        WindowedStream<InputMessage, String, TimeWindow> windowedStream = keyedStream
                .window(TumblingEventTimeWindows.of(Time.seconds(5)));
        //What if the user wants to analyze the data from category A on every 20 seconds, the data from category B on every 8 second.
        //What if the user doesn't want to use tumbling window
        //What if the user wants to change the time of the windowing?

        SingleOutputStreamOperator<String> result = windowedStream.apply(new StaticAggregationWindowFunction());

        result.print();


        env.execute(StaticStatisticalAnalysis.class.getName());
    }
}
