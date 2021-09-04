package window_utils.assigners;

import data.MatchedMessage;
import model.InputMessage;
import model.pojo.control.ControlMessage;
import model.pojo.control.WindowConfiguration;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TimeWindowCollectionFactory {

    private static final String TUMBLING = "tumbling";
    private static final String SLIDING = "sliding";

    public static Collection<TimeWindow> create(MatchedMessage element, long timestamp, String windowType) {
        WindowConfiguration windowConfiguration = element.controlMessage.getWindowConfiguration();
        long windowSize = windowConfiguration.getSize();
        long start;
        switch (windowType) {
            case TUMBLING:
                start = (timestamp / windowSize) * windowSize;
                long end = start + windowSize;
                TimeWindow timeWindow =  new TimeWindow(start, end);
                return Collections.singletonList(timeWindow);
            case SLIDING:
                long slide = windowConfiguration.getSlide();
                List<TimeWindow> windows = new ArrayList<>();
                start = ((timestamp + slide - windowSize) / slide) * slide;
                while (start <= timestamp) {
                    windows.add(new TimeWindow(start, start + windowSize));
                    start += slide;
                }
                return windows;
            default:
                return Collections.emptyList();
        }
    }
}