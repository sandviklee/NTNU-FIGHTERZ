package fightinggame.json;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

public class CharacterInformationDeserializer extends JsonDeserializer<CharacterInformationObject> {

    @Override
    public CharacterInformationObject deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException{
        TreeNode treeNode = p.getCodec().readTree(p);
        return deserialize((JsonNode) treeNode);
    }

    public CharacterInformationObject deserialize(JsonNode jsonNode){
        if (jsonNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            String characterName = objectNode.get("characterName").asText();
            String characterDescription = objectNode.get("characterDescription").asText();
            int difficulty = objectNode.get("difficulty").asInt();
            String playstyle = objectNode.get("playstyle").asText();
            
            CharacterInformationObject character = new CharacterInformationObject(characterName, characterDescription, difficulty, playstyle);
            
            ArrayNode arrayNode = (ArrayNode) objectNode.get("specialActions");
            for (JsonNode elementNode : arrayNode) {
                String specialActionName = elementNode.get("specialActionName").asText();
                String specialActionDescription = elementNode.get("specialActionDescription").asText();
                character.addSpecialAction(specialActionName, specialActionDescription);
            }
            return character;
            }
           
        return null;
    }

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("CharacterInformationDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(CharacterInformationObject.class, new CharacterInformationDeserializer());
        mapper.registerModule(module);
        try {
            File file = new File("./gr2201/gameplay/src/main/resources/fightinggame/CharacterInformation.json");
            TypeReference<ArrayList<CharacterInformationObject>> typeref = new TypeReference<ArrayList<CharacterInformationObject>>() {};
            ArrayList<CharacterInformationObject> myObjects = mapper.readValue(file, typeref);
            System.out.println(myObjects);
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
