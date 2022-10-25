package fightinggame.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import fightinggame.game.Effectbox;

public class EffectboxDeserializer extends JsonDeserializer<Effectbox> {

    @Override
    public Effectbox deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
       TreeNode treeNode = p.getCodec().readTree(p);
       return deserialize((JsonNode) treeNode);

    }

    public Effectbox deserialize(JsonNode jsonNode) {
        if (jsonNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            JsonNode widthNode = objectNode.get("width");
            JsonNode heightNode = objectNode.get("height");
            // JsonNode isTraversibleNode = objectNode.get("isTraversible");

            int width = widthNode.intValue();
            int length = heightNode.intValue();
            Boolean isTraversible = false;

            Effectbox box = new Effectbox(width, length, isTraversible);
            return box;
           }
        return null;
    }    
}
