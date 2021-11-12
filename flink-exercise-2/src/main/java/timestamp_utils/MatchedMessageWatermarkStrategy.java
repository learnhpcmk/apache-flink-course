package timestamp_utils;

import data.MatchedMessage;
import org.apache.flink.api.common.eventtime.*;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.functions.AssignerWithPunctuatedWatermarks;

public class MatchedMessageWatermarkStrategy implements WatermarkStrategy<MatchedMessage> {

    Boolean eventTime;
    String jsonTimestampField;


    public MatchedMessageWatermarkStrategy(ParameterTool parameterTool) {
        this.eventTime = parameterTool.getBoolean("event_time", Boolean.TRUE);
        this.jsonTimestampField = parameterTool.get("timestamp-property", "timestamp");
    }

    @Override
    public WatermarkGenerator<MatchedMessage> createWatermarkGenerator(WatermarkGeneratorSupplier.Context context) {
        return new WatermarkGenerator<MatchedMessage>() {
            @Override
            public void onEvent(MatchedMessage matchedMessage, long eventTimestamp, WatermarkOutput output) {
                if (eventTime) {
                    output.emitWatermark(new Watermark(Long.parseLong(matchedMessage.inputMessage.getFieldValue(jsonTimestampField))));
                }
                else {
                    output.emitWatermark(new Watermark(System.currentTimeMillis()));
                }
            }

            @Override
            public void onPeriodicEmit(WatermarkOutput output) {
                output.emitWatermark(new Watermark(System.currentTimeMillis()));
            }
        };
    }

    @Override
    public TimestampAssigner<MatchedMessage> createTimestampAssigner(TimestampAssignerSupplier.Context context) {
        return (element, recordTimestamp) -> eventTime ? Long.parseLong(element.inputMessage.getFieldValue(jsonTimestampField)) : System.currentTimeMillis();
    }
}
