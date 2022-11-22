package fightinggame.utils.json.characterattributes;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import fightinggame.game.Vector;

public class VectorDeserializer extends JsonDeserializer<Vector> {

    @Override
    public Vector deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        TreeNode treeNode = p.getCodec().readTree(p);
        return deserialize((JsonNode) treeNode);

    }

    public Vector deserialize(JsonNode jsonNode) {
        if (jsonNode instanceof ArrayNode) {
            JsonNode arrayNode = (ArrayNode) jsonNode;

            JsonNode dxNode = arrayNode.get(0);
            JsonNode dyNode = arrayNode.get(1);
            JsonNode axNode = arrayNode.get(2);
            JsonNode ayNode = arrayNode.get(3);
            int dx = dxNode.asInt();
            int dy = dyNode.asInt();
            int ax = axNode.asInt();
            int ay = ayNode.asInt();
            int direction = 1;
            // Has a direction
            if (arrayNode.has(4)) {
                JsonNode directionNode = arrayNode.get(4);
                direction = directionNode.asInt();
            }

            Vector vec = new Vector(dx, dy, ax, ay, direction);
            return vec;
        }

        return null;
    }
}
