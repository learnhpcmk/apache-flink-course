package training_course;

import model.InputMessage;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import org.json.JSONObject;

import java.util.DoubleSummaryStatistics;

public class StaticAggregationWindowFunction implements WindowFunction<InputMessage, String, String, TimeWindow> {
    @Override
    public void apply(String key, TimeWindow window, Iterable<InputMessage> windowData, Collector<String> out) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("category", key);
        jsonObject.put("start_timestamp", window.getStart());
        jsonObject.put("end_timestamp", window.getEnd());

        DoubleSummaryStatistics ds = new DoubleSummaryStatistics();
        for (InputMessage inputMessage : windowData){
            ds.accept(Double.parseDouble(inputMessage.getFieldValue("value"))); //What if we want to extract another property in the future??
        }

        jsonObject.put("min", ds.getMin());
        jsonObject.put("average", ds.getAverage());
        jsonObject.put("max", ds.getMax());
        jsonObject.put("count", ds.getCount());

        out.collect(jsonObject.toString());
    }
}
