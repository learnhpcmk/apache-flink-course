package source.schemas;

import model.InputMessage;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import java.io.IOException;

public class InputMessageDeserializationSchema implements DeserializationSchema<InputMessage> {
    @Override
    public InputMessage deserialize(byte[] message) throws IOException {
        return new InputMessage(new String(message));
    }

    @Override
    public boolean isEndOfStream(InputMessage nextElement) {
        return false;
    }

    @Override
    public TypeInformation<InputMessage> getProducedType() {
        return TypeInformation.of(InputMessage.class);
    }
}
