package process_functions;

import data.MatchedMessage;
import jdk.internal.util.xml.impl.Input;
import model.InputMessage;
import model.pojo.control.ControlMessage;
import org.apache.flink.api.common.state.BroadcastState;
import org.apache.flink.api.common.state.ReadOnlyBroadcastState;
import org.apache.flink.streaming.api.functions.co.BroadcastProcessFunction;
import org.apache.flink.util.Collector;
import state.StateDescriptors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchingProcessFunction extends BroadcastProcessFunction<InputMessage, ControlMessage, MatchedMessage> {

    @Override
    public void processElement(InputMessage inputMessage, ReadOnlyContext ctx, Collector<MatchedMessage> out) throws Exception {
        List<ControlMessage> controlMessages = getAllControlMessagesFromState(ctx);

            controlMessages.stream()
                .filter(controlMessage -> controlMessage.getRule().checkRule(inputMessage))
                .forEach(controlMessage -> out.collect(new MatchedMessage(controlMessage, inputMessage)));
    }

    private List<ControlMessage> getAllControlMessagesFromState (ReadOnlyContext ctx) throws Exception {
        List<ControlMessage> result = new ArrayList<>();
        ReadOnlyBroadcastState<String, Map<String, ControlMessage>> state = ctx.getBroadcastState(StateDescriptors.RULES_BROADCAST_STATE_DESCRIPTOR);
        for (Map.Entry<String, Map<String, ControlMessage>> immutableEntry : state.immutableEntries()) {
            result.addAll(immutableEntry.getValue().values());
        }
        return result;
    }

    @Override
    public void processBroadcastElement(ControlMessage controlMessage, Context ctx, Collector<MatchedMessage> out) throws Exception {
        BroadcastState<String, Map<String, ControlMessage>> state = ctx.getBroadcastState(StateDescriptors.RULES_BROADCAST_STATE_DESCRIPTOR);
        Map<String, ControlMessage> messagesByRuleId;

        if (state.contains(controlMessage.getUserId())) {
            messagesByRuleId = state.get(controlMessage.getUserId());
        } else {
            messagesByRuleId = new HashMap<>();
        }

        messagesByRuleId.put(controlMessage.getRuleId(), controlMessage);
        state.put(controlMessage.getUserId(), messagesByRuleId);
    }
}
