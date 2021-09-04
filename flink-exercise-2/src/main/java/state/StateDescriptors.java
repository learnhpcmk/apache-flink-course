package state;

import model.pojo.SeverityAssignerMessage;
import model.pojo.control.ControlMessage;
import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import java.util.Map;

public class StateDescriptors {

    public static MapStateDescriptor<String, SeverityAssignerMessage> BROADCAST_STATE_DESCRIPTOR = new MapStateDescriptor<>(
            "broadcast_state_descriptor",
            String.class,
            SeverityAssignerMessage.class
    );

    public static MapStateDescriptor<String, Map<String, ControlMessage>> RULES_BROADCAST_STATE_DESCRIPTOR = new MapStateDescriptor<String, Map<String, ControlMessage>>(
            "rules_state_descriptor",
            TypeInformation.of(new TypeHint<String>(){}),
            TypeInformation.of(new TypeHint<Map<String, ControlMessage>>() {})
    );
}
