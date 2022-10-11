package fightinggame.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import fightinggame.game.Vector;

public class VectorDeserializer extends JsonDeserializer<Vector> {

    @Override
    public Vector deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
       TreeNode treeNode = p.getCodec().readTree(p);
       return deserialize((JsonNode) treeNode);

    }

    public Vector deserialize(JsonNode jsonNode) {
        if (jsonNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            JsonNode dxNode = objectNode.get("dx");
            JsonNode dyNode = objectNode.get("dy");
            JsonNode axNode = objectNode.get("ax");
            JsonNode ayNode = objectNode.get("ay");

            int dx = dxNode.asInt();
            int dy = dyNode.asInt();
            int ax = axNode.asInt();
            int ay = ayNode.asInt();
            
            Vector vec = new Vector(dx, dy, ax, ay);
            return vec;
           }
           
        return null;
    }    
}
