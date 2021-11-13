package timestamp_utils;

import model.InputMessage;
import org.apache.flink.api.common.eventtime.*;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.functions.AssignerWithPunctuatedWatermarks;

public class InputMessageWatermarkStrategy implements WatermarkStrategy<InputMessage> {
    Boolean eventTime;
    String jsonTimestampField;

    public InputMessageWatermarkStrategy(){
        eventTime=true;
        jsonTimestampField = "timestamp";
    }


    public InputMessageWatermarkStrategy(ParameterTool parameterTool) {
        this.eventTime = parameterTool.getBoolean("event_time", Boolean.TRUE);
        this.jsonTimestampField = parameterTool.get("timestamp-property", "timestamp");
    }

    @Override
    public WatermarkGenerator<InputMessage> createWatermarkGenerator(WatermarkGeneratorSupplier.Context context) {
        return new WatermarkGenerator<InputMessage>() {
            @Override
            public void onEvent(InputMessage event, long eventTimestamp, WatermarkOutput output) {
                if (eventTime) {
                    output.emitWatermark(new Watermark(Long.parseLong(event.getFieldValue(jsonTimestampField))));
                }
                else {
                    output.emitWatermark(new Watermark(System.currentTimeMillis()));
                }
            }

            @Override
            public void onPeriodicEmit(WatermarkOutput output) {
                //DO NOTHING
            }
        };
    }

    @Override
    public TimestampAssigner<InputMessage> createTimestampAssigner(TimestampAssignerSupplier.Context context) {
        return (element, recordTimestamp) -> eventTime ? Long.parseLong(element.getFieldValue(jsonTimestampField)) : System.currentTimeMillis();
    }
}
