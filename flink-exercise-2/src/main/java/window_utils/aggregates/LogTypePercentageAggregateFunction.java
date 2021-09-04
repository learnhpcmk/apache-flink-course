package window_utils.aggregates;

import model.pojo.Log;
import model.pojo.LogTypePercentagesResult;
import org.apache.flink.api.common.functions.AggregateFunction;

public class LogTypePercentageAggregateFunction implements AggregateFunction<Log, LogTypePercentageAccumulator, LogTypePercentagesResult> {

    @Override
    public LogTypePercentageAccumulator createAccumulator() {
        return new LogTypePercentageAccumulator();
    }

    @Override
    public LogTypePercentageAccumulator add(Log value, LogTypePercentageAccumulator accumulator) {
        return accumulator.add(value);
    }

    @Override
    public LogTypePercentagesResult getResult(LogTypePercentageAccumulator accumulator) {
        return accumulator.getResult();
    }

    @Override
    public LogTypePercentageAccumulator merge(LogTypePercentageAccumulator a, LogTypePercentageAccumulator b) {
        return a.merge(b);
    }
}
