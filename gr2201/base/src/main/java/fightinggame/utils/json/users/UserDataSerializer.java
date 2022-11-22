package fightinggame.utils.json.users;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

import fightinggame.users.UserData;

public class UserDataSerializer extends JsonSerializer<UserData> {

    /*
     * Format:
     * {
     * password: "..."
     * }
     */

    @Override
    public void serialize(UserData data, JsonGenerator jsonGen, SerializerProvider serializerProvider)
            throws IOException {
        jsonGen.writeStartObject();
        jsonGen.writeStringField("password", data.getPassword());
        jsonGen.writeEndObject();
    }
}

