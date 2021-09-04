package window_utils.triggers;

import data.MatchedMessage;
import org.apache.flink.streaming.api.windowing.triggers.EventTimeTrigger;
import org.apache.flink.streaming.api.windowing.triggers.ProcessingTimeTrigger;
import org.apache.flink.streaming.api.windowing.triggers.Trigger;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;

public class DynamicTrigger {

    public static Trigger of(boolean eventTime) {
        if (eventTime) {
            return EventTimeTrigger.create();
        }
        else {
            return ProcessingTimeTrigger.create();
        }
    }


}