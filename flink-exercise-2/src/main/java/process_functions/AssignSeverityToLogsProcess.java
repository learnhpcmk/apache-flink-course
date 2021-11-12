package process_functions;

import exercise4.Task3;
import model.pojo.Log;
import model.pojo.Severity;
import model.pojo.SeverityAssignerMessage;
import org.apache.flink.api.common.state.BroadcastState;
import org.apache.flink.api.common.state.ReadOnlyBroadcastState;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.co.KeyedBroadcastProcessFunction;
import org.apache.flink.util.Collector;
import state.StateDescriptors;

public class AssignSeverityToLogsProcess extends KeyedBroadcastProcessFunction<String, Log, SeverityAssignerMessage, Log> {


    @Override
    public void processElement(Log log, ReadOnlyContext ctx, Collector<Log> out) throws Exception {
        String system = ctx.getCurrentKey();
//        String system = log.getSystem();
        ReadOnlyBroadcastState<String, SeverityAssignerMessage> state = ctx.getBroadcastState(StateDescriptors.BROADCAST_STATE_DESCRIPTOR);

        if (state.contains(system)){
            SeverityAssignerMessage severityAssignerMessage = state.get(system);

            Long severityToBeAssigned = severityAssignerMessage.getSeverities()
                    .stream()
                    .filter(severity -> severity.getLogType().equals(log.getType()))
                    .findFirst()
                    .map(Severity::getSeverity)
                    .orElse(null);

            log.setSeverity(severityToBeAssigned);
        }

        out.collect(log);
    }

    @Override
    public void processBroadcastElement(SeverityAssignerMessage severityAssignerMessage, Context ctx, Collector<Log> out) throws Exception {
        BroadcastState<String, SeverityAssignerMessage> state = ctx.getBroadcastState(StateDescriptors.BROADCAST_STATE_DESCRIPTOR);
        state.put(severityAssignerMessage.getSystem(), severityAssignerMessage);
    }
}
