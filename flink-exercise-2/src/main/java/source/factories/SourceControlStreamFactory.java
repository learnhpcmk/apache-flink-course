package source.factories;

import model.pojo.control.ControlMessage;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import settings.ProjectSettings;
import source.test_sources.ControlMessageSource;

public class SourceControlStreamFactory {
    public static DataStream<ControlMessage> getStream(ParameterTool parameterTool, StreamExecutionEnvironment env) {
        boolean testMode = parameterTool.getBoolean(ProjectSettings.TEST_MODE, true);
        if (testMode) {
            return env.addSource(new ControlMessageSource());
        } else {
            return KafkaDataStream.getControlStream(parameterTool, env);
        }
    }
}
