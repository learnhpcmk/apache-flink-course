package data;

import model.InputMessage;
import model.pojo.control.ControlMessage;

public class MatchedMessage {
    public ControlMessage controlMessage;
    public InputMessage inputMessage;

    public MatchedMessage(ControlMessage controlMessage, InputMessage inputMessage) {
        this.controlMessage = controlMessage;
        this.inputMessage = inputMessage;
    }

    public String getPartitioningKey () {
        return controlMessage.getUserId() + "___" + controlMessage.getRuleId();
    }
}
