package window_utils.aggregates;

import model.pojo.ErrorPercentageResult;
import model.pojo.Log;
import org.apache.flink.api.common.functions.AggregateFunction;

public class ErrorPercentageAggregateFunction implements AggregateFunction<Log, ErrorPercentageAccumulator, ErrorPercentageResult> {

    @Override
    public ErrorPercentageAccumulator createAccumulator() {
        return new ErrorPercentageAccumulator();
    }

    @Override
    public ErrorPercentageAccumulator add(Log value, ErrorPercentageAccumulator accumulator) {
        return accumulator.add(value);
    }

    @Override
    public ErrorPercentageResult getResult(ErrorPercentageAccumulator accumulator) {
        return accumulator.getResult();
    }

    @Override
    public ErrorPercentageAccumulator merge(ErrorPercentageAccumulator a, ErrorPercentageAccumulator b) {
        return a.merge(b);
    }


}
