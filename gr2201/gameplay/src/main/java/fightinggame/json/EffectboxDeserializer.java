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

            JsonNode offsetXNode;
            JsonNode offsetYNode;
            JsonNode isTraversibleNode;
            Boolean isHurtBox;
            Boolean isTraversible = false;
            int width = widthNode.intValue();
            int length = heightNode.intValue();
            
            if(objectNode.has("offsetX") && objectNode.has("offsetY") && objectNode.has("isTraversible")) {
                offsetXNode = objectNode.get("offsetX");
                offsetYNode = objectNode.get("offsetY");
                isTraversibleNode = objectNode.get("isTraversible");
                isHurtBox = true;
            }
                        
            int offsetX = offsetXNode.intValue();
            int offsetY = offsetYNode.intValue();
            isTraversible = isTraversibleNode.booleanValue();
            
            Effectbox box = (isHurtBox) ? new Effectbox(width, length, isTraversible) : new Effectbox(width, length, offsetX, offsetY, isTraversible);
            return box;
           }
        return null;
    }    
}
