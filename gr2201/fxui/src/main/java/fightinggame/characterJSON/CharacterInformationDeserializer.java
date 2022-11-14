package fightinggame.characterJSON;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * CharacterInformationDeserializer is a JsonDeserializer for character information.
 * The JSON it takes in follows the pattern described in the JSON schema in docs/current/json/characterinfoformat.json
 */
public class CharacterInformationDeserializer extends JsonDeserializer<CharacterInformationObject> {
    /**
     * @param p JsonParser
     * @param ctxt DeserializationContext
     * @return CharacterInformationObject
     */
    @Override
    public CharacterInformationObject deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException{
        TreeNode treeNode = p.getCodec().readTree(p);
        return deserialize((JsonNode) treeNode);
    }

    /**
     * Deserializes JsonNode object into a CharacterInformationObject
     * @param jsonNode all json data in given node
     * @return CharacterInformationObject containing containing one character's information
     * @throws IllegalArgumentException if JSON provides invalid data for CharacterInformationObject
     * @throws NullPointerException if JSON does not provide all necessary fields for character
     */
    public CharacterInformationObject deserialize(JsonNode jsonNode) throws IllegalArgumentException, NullPointerException {
        if (jsonNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) jsonNode;

            String characterName = objectNode.get("characterName").asText();
            String characterDescription = objectNode.get("characterDescription").asText();
            int difficulty = objectNode.get("difficulty").asInt();
            String playstyle = objectNode.get("playstyle").asText();

            if (difficulty != 1 & difficulty != 2 & difficulty != 3){
                throw new IllegalArgumentException("Invalid difficulty.");
            }
            
            CharacterInformationObject character = new CharacterInformationObject(characterName, characterDescription, difficulty, playstyle);
            
            ArrayNode arrayNode = (ArrayNode) objectNode.get("specialActions");
            for (JsonNode elementNode : arrayNode) {
                String specialActionName = elementNode.get("specialActionName").asText();
                String specialActionDescription = elementNode.get("specialActionDescription").asText();
                character.addSpecialAction(specialActionName, specialActionDescription);
            }

            if (character.getSpecialActions().size() != 4){
                throw new IllegalArgumentException("Invalid size for special actions.");
            }
            return character;
            }
        return null;
    }
}
