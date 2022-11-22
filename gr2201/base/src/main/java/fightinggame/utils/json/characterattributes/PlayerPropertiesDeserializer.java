package fightinggame.utils.json.characterattributes;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import fightinggame.game.ActionProperties;
import fightinggame.game.Effectbox;
import fightinggame.game.PlayerProperties;

public class PlayerPropertiesDeserializer extends JsonDeserializer<PlayerProperties> {
    private static ActionPropertiesDeserializer actionPropertiesDeserializer = new ActionPropertiesDeserializer();
    private static EffectboxDeserializer effectboxDeserializer = new EffectboxDeserializer();

    @Override
    public PlayerProperties deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        TreeNode treeNode = p.getCodec().readTree(p);
        return deserialize((JsonNode) treeNode);
    }

    public PlayerProperties deserialize(JsonNode jsonNode) {
        if (jsonNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            JsonNode characterNameNode = objectNode.get("characterName");
            JsonNode weightNode = objectNode.get("weight");
            JsonNode hurtboxNode = objectNode.get("hurtbox");
            JsonNode speedNode = objectNode.get("speed");

            JsonNode actionsNode = objectNode.get("actions");
            Boolean hasActions = actionsNode instanceof ArrayNode;

            ArrayList<ActionProperties> actions = new ArrayList<>();
            if (hasActions) {
                for (JsonNode actionNode : (ArrayNode) actionsNode) {
                    ActionProperties actionProperties = actionPropertiesDeserializer.deserialize(actionNode);
                    actions.add(actionProperties);
                }
            }
            String characterName = characterNameNode.asText();
            double weight = weightNode.doubleValue();
            Effectbox hurtbox = effectboxDeserializer.deserialize(hurtboxNode);
            int length = hurtbox.getHeight();
            int width = hurtbox.getWidth();
            double speed = speedNode.doubleValue();

            PlayerProperties playerProperties = new PlayerProperties(characterName, weight, length, width, speed,
                    actions);
            return playerProperties;
        }
        return null;
    }
}
