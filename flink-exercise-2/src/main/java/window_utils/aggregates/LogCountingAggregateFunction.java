package window_utils.aggregates;

import model.pojo.Log;
import model.pojo.LogCountResult;
import org.apache.flink.api.common.functions.AggregateFunction;

public class LogCountingAggregateFunction implements AggregateFunction<Log, LogCountingAccumulator, LogCountResult> {

    @Override
    public LogCountingAccumulator createAccumulator() {
        return new LogCountingAccumulator();
    }

    @Override
    public LogCountingAccumulator add(Log value, LogCountingAccumulator accumulator) {
        return accumulator.add(value);
    }

    @Override
    public LogCountResult getResult(LogCountingAccumulator accumulator) {
        return accumulator.getResult();
    }

    @Override
    public LogCountingAccumulator merge(LogCountingAccumulator a, LogCountingAccumulator b) {
        return a.merge(b);
    }
}
