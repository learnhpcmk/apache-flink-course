package exercise5;

import model.InputMessage;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import source.factories.SourceDataStreamFactory;

public class KafkaExample {
    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        ParameterTool parameterTool = ParameterTool.fromArgs(args);

        DataStream<InputMessage> inputMessageDataStream = SourceDataStreamFactory.getStream(parameterTool, env);

        inputMessageDataStream.print();

        env.execute(KafkaExample.class.getName());

    }
}
