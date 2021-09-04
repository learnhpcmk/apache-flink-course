package window_utils.triggers;

import model.pojo.Log;
import org.apache.flink.streaming.api.windowing.triggers.Trigger;
import org.apache.flink.streaming.api.windowing.triggers.TriggerResult;
import org.apache.flink.streaming.api.windowing.windows.GlobalWindow;

public class ErrorTrigger extends Trigger<Log, GlobalWindow> {
    @Override
    public TriggerResult onElement(Log element, long timestamp, GlobalWindow window, TriggerContext ctx) throws Exception {
        if (element.getType().equals("ERROR"))
            return TriggerResult.FIRE_AND_PURGE;
        else
            return TriggerResult.CONTINUE;
    }

    @Override
    public TriggerResult onProcessingTime(long time, GlobalWindow window, TriggerContext ctx) throws Exception {
        return TriggerResult.CONTINUE;
    }

    @Override
    public TriggerResult onEventTime(long time, GlobalWindow window, TriggerContext ctx) throws Exception {
        return TriggerResult.CONTINUE;
    }

    @Override
    public void clear(GlobalWindow window, TriggerContext ctx) throws Exception {
        //DO NOTHING
    }
}
