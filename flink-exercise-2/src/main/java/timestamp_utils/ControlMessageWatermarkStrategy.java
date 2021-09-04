package timestamp_utils;

import model.pojo.control.ControlMessage;
import org.apache.flink.api.common.eventtime.*;

public class ControlMessageWatermarkStrategy implements WatermarkStrategy<ControlMessage> {

    @Override
    public TimestampAssigner<ControlMessage> createTimestampAssigner(TimestampAssignerSupplier.Context context) {
        return (element, recordTimestamp) -> System.currentTimeMillis();
    }

    @Override
    public WatermarkGenerator<ControlMessage> createWatermarkGenerator(WatermarkGeneratorSupplier.Context context) {
        return new WatermarkGenerator<ControlMessage>() {
            @Override
            public void onEvent(ControlMessage event, long eventTimestamp, WatermarkOutput output) {
                output.emitWatermark(new Watermark(System.currentTimeMillis()));
            }

            @Override
            public void onPeriodicEmit(WatermarkOutput output) {
                //DO NOTHING
            }
        };
    }
}
