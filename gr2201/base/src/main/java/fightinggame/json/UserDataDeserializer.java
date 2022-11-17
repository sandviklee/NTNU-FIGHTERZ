package fightinggame.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;


import fightinggame.users.UserData;

public class UserDataDeserializer extends JsonDeserializer<UserData> {

    @Override
    public UserData deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
       TreeNode treeNode = p.getCodec().readTree(p);
       return deserialize((JsonNode) treeNode);

    }

    public UserData deserialize(JsonNode jsonNode) {
        if (jsonNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) jsonNode;

            String passwordField = "password";
            if (objectNode.has(passwordField)) {
                JsonNode passwordNode = objectNode.get(passwordField);

                if (passwordNode instanceof TextNode) {
                    String password = passwordNode.asText();
                    UserData data = new UserData(password);
                    return data;
                }
            }
        }
        return null;
    }    
}
