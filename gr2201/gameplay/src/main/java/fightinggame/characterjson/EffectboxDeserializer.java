package fightinggame.characterjson;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import fightinggame.game.Effectbox;
import fightinggame.game.Point;

public class EffectboxDeserializer extends JsonDeserializer<Effectbox> {

    @Override
    public Effectbox deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
       TreeNode treeNode = p.getCodec().readTree(p);
       return deserialize((JsonNode) treeNode);

    }

    public Effectbox deserialize(JsonNode jsonNode) {
        if (jsonNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            JsonNode widthNode = objectNode.get("lenght");
            JsonNode heightNode = objectNode.get("height");

            JsonNode offsetXNode;
            JsonNode offsetYNode;
            JsonNode isTraversibleNode;
            Boolean isHurtBox = true;
            Boolean isTraversible = false;
            int width = widthNode.intValue();
            int length = heightNode.intValue();
            int offsetX = 0;
            int offsetY = 0;

            if(objectNode.has("offsetX") && objectNode.has("offsetY") && objectNode.has("isTraversible")) {
                offsetXNode = objectNode.get("offsetX");
                offsetYNode = objectNode.get("offsetY");
                isTraversibleNode = objectNode.get("isTraversible");
                isHurtBox = false;
                offsetX = offsetXNode.intValue();
                offsetY = offsetYNode.intValue();
                isTraversible = isTraversibleNode.booleanValue();
            }
                        
            ArrayList hitboxProperties = new ArrayList<>();
            hitboxProperties.add(width);
            hitboxProperties.add(length);

            // Effectbox box = (isHurtBox) ? new Effectbox(width, length, isTraversible) : new Effectbox(width, length, offsetX, offsetY, isTraversible);
            Effectbox box = new Effectbox(null, new Point(offsetX, offsetY), isTraversible, hitboxProperties);
            return box;
           }
        return null;
    }    
}
