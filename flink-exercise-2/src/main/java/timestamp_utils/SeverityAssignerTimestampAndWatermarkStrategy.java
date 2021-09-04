package timestamp_utils;

import model.pojo.SeverityAssignerMessage;
import org.apache.flink.api.common.eventtime.*;

public class SeverityAssignerTimestampAndWatermarkStrategy implements WatermarkStrategy<SeverityAssignerMessage> {
    @Override
    public WatermarkGenerator<SeverityAssignerMessage> createWatermarkGenerator(WatermarkGeneratorSupplier.Context context) {
        return new WatermarkGenerator<SeverityAssignerMessage>() {
            @Override
            public void onEvent(SeverityAssignerMessage event, long eventTimestamp, WatermarkOutput output) {
                output.emitWatermark(new Watermark(System.currentTimeMillis()));
            }

            @Override
            public void onPeriodicEmit(WatermarkOutput output) {

            }
        };
    }

    @Override
    public TimestampAssigner<SeverityAssignerMessage> createTimestampAssigner(TimestampAssignerSupplier.Context context) {
        return (element, recordTimestamp) -> System.currentTimeMillis();
    }
}
