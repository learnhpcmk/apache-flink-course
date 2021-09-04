package window_utils.aggregates;

import model.pojo.ErrorPercentageResult;
import model.pojo.Log;

import java.io.Serializable;

public class ErrorPercentageAccumulator implements Serializable {

    String system;
    int totalCount = 0;
    int errorsCount = 0;

    public ErrorPercentageAccumulator() {

    }

    public ErrorPercentageAccumulator(String system, int totalCount, int errorsCount) {
        this.system = system;
        this.totalCount = totalCount;
        this.errorsCount = errorsCount;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public ErrorPercentageAccumulator add(Log log) {
        return new ErrorPercentageAccumulator(
                log.getSystem(),
                this.totalCount + 1,
                this.errorsCount + (log.getType().equalsIgnoreCase("ERROR") ? 1 : 0)
        );
    }

    public ErrorPercentageResult getResult() {
        double percentage = (totalCount == 0) ? 0 : (((double) errorsCount * 100) / totalCount);
        return new ErrorPercentageResult(system, percentage);
    }

    public ErrorPercentageAccumulator merge(ErrorPercentageAccumulator other) {
        return new ErrorPercentageAccumulator(
                other.system,
                this.totalCount + other.totalCount,
                this.errorsCount + other.errorsCount
        );
    }
}
