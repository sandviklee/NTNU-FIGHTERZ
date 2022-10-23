package fightinggame.json;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class CharacterInformationDeserializer extends JsonDeserializer<HashMap<String, HashMap<String, String>>> {

    @Override
    public HashMap<String, HashMap<String, String>> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException{
        TreeNode treeNode = p.getCodec().readTree(p);
        return deserialize((JsonNode) treeNode);
    }

    public HashMap<String, HashMap<String, String>> deserialize(JsonNode jsonNode){
        if (jsonNode instanceof ArrayNode) {
            HashMap<String, HashMap<String, String>> allCharacters = new HashMap<String, HashMap<String, String>>();

            for (JsonNode characterNode : jsonNode) {
                ObjectNode objectNode = (ObjectNode) characterNode;
                HashMap<String, String> characterInformation = new HashMap<String, String>();

                String characterDescription = objectNode.get("characterDescription").asText();
                characterInformation.put("Character description", characterDescription);
                String difficulty = objectNode.get("difficulty").asText();
                characterInformation.put("Difficulty", difficulty);
                String playstyle = objectNode.get("playstyle").asText();
                characterInformation.put("Playstyle", playstyle);
                
                int i = 1;
                ArrayNode arrayNode = (ArrayNode) objectNode.get("specialActions");
                for (JsonNode elementNode : arrayNode){
                    String specialActionName = elementNode.get("specialActionName").asText();
                    characterInformation.put("Special Action Name #"+i, specialActionName);
                    String specialActionDescription = elementNode.get("specialActionDescription").asText();
                    characterInformation.put("Special Action Description #"+i, specialActionDescription);
                    i++;
                }
                String characterName = objectNode.get("characterName").asText();
                allCharacters.put(characterName, characterInformation);
            }
            return allCharacters;
           }
           
        return null;
    }

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("CharacterInformationDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(HashMap.class, new CharacterInformationDeserializer());
        mapper.registerModule(module);
        try {
            File file = new File("./gr2201/gameplay/src/main/resources/CharacterInformation.json");
            TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {};
            HashMap<String, String> characters = mapper.readValue(file, typeRef);
            System.out.println(characters.get("Sly"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
