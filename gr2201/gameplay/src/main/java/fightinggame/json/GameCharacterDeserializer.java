package fightinggame.json;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import fightinggame.game.Action;
import fightinggame.game.Effectbox;
import fightinggame.game.GameCharacter;

public class GameCharacterDeserializer extends JsonDeserializer<GameCharacter>{
    @Override
    public GameCharacter deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
       TreeNode treeNode = p.getCodec().readTree(p);
       return deserialize((JsonNode) treeNode);
    }

    public GameCharacter deserialize(JsonNode jsonNode) {
        if (jsonNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            JsonNode characterNameNode = objectNode.get("characterName");
            JsonNode weightNode = objectNode.get("weight");
            // JsonNode healthNode = objectNode.get("health");
            JsonNode hurtboxNode = objectNode.get("hurtbox");

            JsonNode actionsNode = objectNode.get("actions");
            Boolean hasActions = actionsNode instanceof ArrayNode;

            Action actions;
            if (hasActions) {
                int currentActionIndex = 0;
                for (JsonNode actionNode : (ArrayNode) actionsNode) {
                    ActionProperties actionProperties = ActionPropertiesDeserializer.deserialize(actionNode);
                    actions.put(currentActionIndex, actionProperties);
                    currentActionIndex++;
                }
            }
            String characterName = characterNameNode.asText();
            int weight = weightNode.intValue();
            // int health = healthNode.intValue();
            Effectbox hurtbox = EffectboxDeserializer.deserialize(hurtboxNode);
            
            GameCharacter gameCharacter = new GameCharacter(characterName, weight, hurtbox, actions);
            return gameCharacter;
           }
        return null;
    }   
}
