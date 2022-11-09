package fightinggame.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

import fightinggame.users.User;

public class UserSerializer extends JsonSerializer<User> {
    /*
    user: {
        UserId: {
            id: "..."
        }
        userData: {
            password: "..." 
        }
    }
    */
    @Override
    public void serialize(User user, JsonGenerator jsonGen, SerializerProvider serializerProvider) throws IOException {
        jsonGen.writeStartObject();
        
        // Use serializers for objects UserId and UserData
        jsonGen.writeObjectField("UserId", user.getUserId());
        jsonGen.writeObjectField("UserData", user.getUserData());

        jsonGen.writeEndObject();
    }
}

