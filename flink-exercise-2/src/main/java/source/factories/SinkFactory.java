package source.factories;

import model.pojo.RuleStatisticsResult;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.functions.sink.PrintSinkFunction;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import settings.ProjectSettings;

public class SinkFactory {
    public static SinkFunction<RuleStatisticsResult> getSink (ParameterTool parameterTool) {
        boolean test = parameterTool.getBoolean(
                ProjectSettings.TEST_MODE,
                ProjectSettings.DEFAULT_TEST_MODE
        );

        if (test) {
            return new PrintSinkFunction<>();
        } else {
            return KafkaSinkStream.getTuplesKafkaSink(parameterTool);
        }
    }
}
