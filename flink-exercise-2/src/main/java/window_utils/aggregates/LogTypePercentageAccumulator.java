package window_utils.aggregates;

import model.pojo.Log;
import model.pojo.LogTypePercentage;
import model.pojo.LogTypePercentagesResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LogTypePercentageAccumulator {

    Map<String, Integer> countingMap;
    //ERROR - 1, DEBUG - 1, INFO - 2 --> count = 4
    String system;
    long startTimestamp = Long.MAX_VALUE;
    long endTimestamp = Long.MIN_VALUE;

    public LogTypePercentageAccumulator() {
        countingMap = new HashMap<>();
    }

    public LogTypePercentageAccumulator(String system, Map<String, Integer> countingMap) {
        this.countingMap = countingMap;
        this.system = system;
    }

    public LogTypePercentageAccumulator(String system, Map<String, Integer> countingMap, long startTimestamp, long endTimestamp) {
        this.countingMap = countingMap;
        this.system = system;
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
    }

    public LogTypePercentageAccumulator add(Log log) {
        countingMap.putIfAbsent(log.getType(), 0);
        countingMap.computeIfPresent(log.getType(), (k, v) -> ++v);
        return new LogTypePercentageAccumulator(
                log.getSystem(),
                countingMap,
                Long.min(startTimestamp, log.getTimestamp()),
                Long.max(endTimestamp, log.getTimestamp())
        );
    }

    public LogTypePercentagesResult getResult() {
        int count = countingMap.values().stream().mapToInt(i -> i).sum();

        List<LogTypePercentage> logTypePercentages = countingMap.entrySet()
                .stream()
                .map(entry -> new LogTypePercentage(entry.getKey(), (double) entry.getValue() * 100.0 / count))
                .collect(Collectors.toList());

        return new LogTypePercentagesResult(system, (long) count, startTimestamp, endTimestamp, logTypePercentages);
    }

    public LogTypePercentageAccumulator merge(LogTypePercentageAccumulator other) {
        Map<String, Integer> newCountingMap = new HashMap<>(this.countingMap);
        other.countingMap.forEach((k, v) -> newCountingMap.merge(k, v, Integer::sum));
        return new LogTypePercentageAccumulator(
                this.system,
                newCountingMap
        );
    }
}
