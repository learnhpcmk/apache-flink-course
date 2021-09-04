package window_utils.aggregates;

import model.pojo.Log;
import model.pojo.LogCountResult;

public class LogCountingAccumulator {
    String system;
    String logType;
    long count = 0;

    public LogCountingAccumulator() {

    }

    public LogCountingAccumulator(String system, String logType, long count) {
        this.system = system;
        this.logType = logType;
        this.count = count;
    }

    public LogCountingAccumulator add(Log value) {
        return new LogCountingAccumulator(
                value.getSystem(),
                value.getType(),
                this.count+1
        );
    }

    public LogCountResult getResult() {
        LogCountResult result = new LogCountResult();
        result.setSystem(this.system);
        result.setLogType(this.logType);
        result.setCount(this.count);
        return result;
    };

    public LogCountingAccumulator merge(LogCountingAccumulator other) {
        return new LogCountingAccumulator(
                this.system,
                this.logType,
                this.count+other.count
        );
    }
}
