package source.schemas;

import com.google.gson.Gson;
import model.pojo.control.ControlMessage;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import java.io.IOException;

public class ControlMessageDeserializationSchema implements DeserializationSchema<ControlMessage> {
    @Override
    public ControlMessage deserialize(byte[] message) throws IOException {
        return new Gson().fromJson(new String(message), ControlMessage.class);
    }

    @Override
    public boolean isEndOfStream(ControlMessage nextElement) {
        return false;
    }

    @Override
    public TypeInformation<ControlMessage> getProducedType() {
        return TypeInformation.of(ControlMessage.class);
    }
}
