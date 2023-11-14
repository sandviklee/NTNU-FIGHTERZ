package fightinggame.utils.json.characterattributes;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import fightinggame.game.Effectbox;
import fightinggame.game.Point;

public class EffectboxDeserializer extends JsonDeserializer<Effectbox> {

    @Override
    public Effectbox deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
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

            Boolean isTraversible = false;
            int width = 0;
            int length = 0;
            if ((widthNode instanceof IntNode) && (heightNode instanceof IntNode)) {
                width = widthNode.intValue();
                length = heightNode.intValue();
            }

            int offsetX = 0;
            int offsetY = 0;

            if (objectNode.has("offsetX") && objectNode.has("offsetY") && objectNode.has("isTraversible")) {
                offsetXNode = objectNode.get("offsetX");
                offsetYNode = objectNode.get("offsetY");
                isTraversibleNode = objectNode.get("isTraversible");
                if ((offsetXNode instanceof IntNode) && (offsetYNode instanceof IntNode)
                        && (isTraversibleNode instanceof BooleanNode)) {
                    offsetX = offsetXNode.intValue();
                    offsetY = offsetYNode.intValue();
                    isTraversible = isTraversibleNode.booleanValue();
                }
            }

            Effectbox box = new Effectbox(null, new Point(offsetX, offsetY), isTraversible, width, length);
            return box;
        }
        return null;
    }
}
