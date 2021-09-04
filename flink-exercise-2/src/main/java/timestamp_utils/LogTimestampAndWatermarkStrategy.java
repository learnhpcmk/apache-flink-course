package timestamp_utils;

import model.pojo.Log;
import org.apache.flink.api.common.eventtime.*;

/*
This function is creating a WatermarkStrategy that extracts the timestamp from the Log itself and that emits the watermark
on every received event.
This WatermarkStrategy should be used when we know for sure that the events' timestamps are in ascending order.
 */
public class LogTimestampAndWatermarkStrategy implements WatermarkStrategy<Log> {


    @Override
    public TimestampAssigner<Log> createTimestampAssigner(TimestampAssignerSupplier.Context context) {
        return (element, recordTimestamp) -> element.getTimestamp();
    }

    @Override
    public WatermarkGenerator<Log> createWatermarkGenerator(WatermarkGeneratorSupplier.Context context) {
        return new WatermarkGenerator<Log>() {
            @Override
            public void onEvent(Log event, long eventTimestamp, WatermarkOutput output) {
                output.emitWatermark(new Watermark(event.getTimestamp()));
            }

            @Override
            public void onPeriodicEmit(WatermarkOutput output) {
//                output.emitWatermark(new Watermark(System.currentTimeMillis()));
            }
        };
    }
}
