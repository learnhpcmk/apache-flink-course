package source.factories;

import com.google.gson.Gson;
import model.InputMessage;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import settings.ProjectSettings;
import source.test_sources.ExampleInputMessageSource;
import source.test_sources.ExampleJsonSource;

public class SourceDataStreamFactory {

    public static DataStream<InputMessage> getStream(ParameterTool parameterTool, StreamExecutionEnvironment env) {

        boolean testMode = parameterTool.getBoolean(
                ProjectSettings.TEST_MODE,
                ProjectSettings.DEFAULT_TEST_MODE
        );

        if (testMode) {
            return env.addSource(new ExampleInputMessageSource());
        } else {
            return KafkaDataStream.getDataStream(parameterTool, env);
        }
    }


}
