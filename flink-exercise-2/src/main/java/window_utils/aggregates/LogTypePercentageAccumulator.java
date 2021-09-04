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
    String system;

    public LogTypePercentageAccumulator() {
        countingMap = new HashMap<>();
    }

    public LogTypePercentageAccumulator(String system, Map<String, Integer> countingMap) {
        this.countingMap = countingMap;
        this.system = system;
    }

    public LogTypePercentageAccumulator add(Log value) {
        countingMap.putIfAbsent(value.getType(), 0);
        countingMap.computeIfPresent(value.getType(), (k, v) -> ++v);
        return new LogTypePercentageAccumulator(
                value.getSystem(),
                countingMap
        );
    }

    public LogTypePercentagesResult getResult() {
        int count = countingMap.values().stream().mapToInt(i -> i).sum();

        List<LogTypePercentage> logTypePercentages = countingMap.entrySet()
                .stream()
                .map(entry -> new LogTypePercentage(entry.getKey(), (double) entry.getValue() * 100.0 / count))
                .collect(Collectors.toList());

        return new LogTypePercentagesResult(system, (long) count, null, null, logTypePercentages);
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
