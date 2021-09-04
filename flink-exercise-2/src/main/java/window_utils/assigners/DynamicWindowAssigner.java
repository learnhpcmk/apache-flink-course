package window_utils.assigners;

import data.MatchedMessage;
import org.apache.flink.api.common.ExecutionConfig;
import org.apache.flink.api.common.typeutils.TypeSerializer;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.WindowAssigner;
import org.apache.flink.streaming.api.windowing.triggers.Trigger;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import window_utils.triggers.DynamicTrigger;

import java.util.Collection;

public class DynamicWindowAssigner extends WindowAssigner<MatchedMessage, TimeWindow> {

    Boolean eventTime;

    public DynamicWindowAssigner(Boolean eventTime) {
        this.eventTime = eventTime;
    }

    @Override
    public Collection<TimeWindow> assignWindows(MatchedMessage element, long timestamp, WindowAssignerContext context) {
        String windowType = element.controlMessage.getWindowConfiguration().getType();
        if (!eventTime) {
            timestamp = context.getCurrentProcessingTime();
        } else if (timestamp == Long.MIN_VALUE) {
            throw new RuntimeException("Record has Long.MIN_VALUE timestamp (= no timestamp marker). " +
                    "Is the time characteristic set to 'ProcessingTime', or did you forget to call " +
                    "'DataStream.assignTimestampsAndWatermarks(...)'?");
        }

        return TimeWindowCollectionFactory.create(element, timestamp, windowType);
    }

    @Override
    public Trigger<MatchedMessage, TimeWindow> getDefaultTrigger(StreamExecutionEnvironment env) {
        return DynamicTrigger.of(eventTime);
    }

    @Override
    public TypeSerializer<TimeWindow> getWindowSerializer(ExecutionConfig executionConfig) {
        return new TimeWindow.Serializer();
    }

    @Override
    public boolean isEventTime() {
        return eventTime;
    }
}
