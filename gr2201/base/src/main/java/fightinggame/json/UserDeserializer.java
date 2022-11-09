package fightinggame.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import fightinggame.users.*;

public class UserDeserializer extends JsonDeserializer<User> {

    private final UserIdDeserializer userIdDeserializer= new UserIdDeserializer();
    private final UserDataDeserializer userDataDeserializer = new UserDataDeserializer();

    @Override
    public User deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
       TreeNode treeNode = p.getCodec().readTree(p);
       if (treeNode instanceof ObjectNode) {
        ObjectNode objectNode = (ObjectNode) treeNode;
        JsonNode idNode = objectNode.get("UserId");
        JsonNode dataNode = objectNode.get("UserData");

        UserId id = userIdDeserializer.deserialize(idNode);
        UserData data = userDataDeserializer.deserialize(dataNode);

        return new User(id, data);
       }
       return null;
    }    
}
