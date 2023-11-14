package fightinggame.utils.json.users;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import fightinggame.users.UserId;

import java.io.IOException;

public class UserIdSerializer extends JsonSerializer<UserId> {

    /*
     * {id: "..."}
     */
    @Override
    public void serialize(UserId id, JsonGenerator jsonGen, SerializerProvider serializerProvider) throws IOException {
        jsonGen.writeStartObject();
        jsonGen.writeStringField("userId", id.getUserId());
        jsonGen.writeEndObject();
    }
}

