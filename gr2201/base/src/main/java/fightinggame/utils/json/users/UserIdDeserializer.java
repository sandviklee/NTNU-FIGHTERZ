package fightinggame.utils.json.users;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import fightinggame.users.UserId;

public class UserIdDeserializer extends JsonDeserializer<UserId> {

    @Override
    public UserId deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        TreeNode treeNode = p.getCodec().readTree(p);
        return deserialize((JsonNode) treeNode);

    }

    public UserId deserialize(JsonNode jsonNode) {
        if (jsonNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            String idField = "userId";

            if (objectNode.has(idField)) {
                JsonNode idNode = objectNode.get(idField);
                if (idNode instanceof TextNode) {
                    String id = idNode.asText();
                    UserId userid = new UserId(id);
                    return userid;
                }
            }
        }
        return null;
    }
}

