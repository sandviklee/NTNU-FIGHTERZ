package fightinggame.characterJSON;
// package fightinggame.json;

// import java.io.File;
// import java.util.HashMap;

// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;

// import com.fasterxml.jackson.core.Version;
// import com.fasterxml.jackson.core.type.TypeReference;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.databind.module.SimpleModule;

// public class CharacterInformationDeserializerTest {
//     private File file = new File("./gr2201/gameplay/src/main/java/fightinggame/json/CharacterInformation.json");
//     private  SimpleModule module = new SimpleModule("CharacterInformationDeserializer", new Version(1, 0, 0, null, null, null));
//     private ObjectMapper mapper = new ObjectMapper();
//     private TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {};

//     private final String characterJsonResult = "";

//     @BeforeAll
//     public void setup() {
//         module.addDeserializer(HashMap.class, new CharacterInformationDeserializer());
//         mapper.registerModule(module);
        
//     }

//     @Test
//     @DisplayName("Tests if ")
//     public void test() throws Exception {
//         HashMap<String, String> characters = mapper.readValue(file, typeRef);
//     }

//     /*
//      * ObjectMapper mapper = new ObjectMapper();
//         SimpleModule module = new SimpleModule("CharacterInformationDeserializer", new Version(1, 0, 0, null, null, null));
//         module.addDeserializer(HashMap.class, new CharacterInformationDeserializer());
//         mapper.registerModule(module);
//         try {
//             File file = new File("./gr2201/gameplay/src/main/java/fightinggame/json/CharacterInformation.json");
//             TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {};
//             HashMap<String, String> characters = mapper.readValue(file, typeRef);
//             System.out.println("hello");
//             System.out.println(characters);
//             System.out.println("bye");
//         } catch (Exception e) {
//             System.out.println(e);
//         }
//      */
// }
