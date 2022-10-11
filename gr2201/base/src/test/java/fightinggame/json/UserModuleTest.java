package fightinggame.json;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fightinggame.users.User;
import fightinggame.users.UserData;
import fightinggame.users.UserId;


public class UserModuleTest {

    private ObjectMapper mapper = new ObjectMapper();
    private final String validUserIdParam = "UserId123";
    private final String validUserDataParam = "UserData123";

    private final UserId userId = new UserId(validUserIdParam);
    private final UserData userData = new UserData(validUserDataParam);
	private final User user = new User(userId, userData);

    private final String userJsonResult = "{\"UserId\":{\"id\":\"UserId123\"},\"UserData\":{\"password\":\"UserData123\"}}";
    private final String userIdJsonResult = "{\"id\":\"UserId123\"}";
    private final String userDataJsonResult = "{\"password\":\"UserData123\"}";


    @BeforeEach
    public void setup() {
        mapper.registerModule(new UserModule());
    }

    @Test
    @DisplayName("Tests if Userid Serializes to correct JSON String")
    public void userIdSerializerTest() {
        try {
            String res = mapper.writeValueAsString(userId);
            assertTrue(res.equals(userIdJsonResult));

        } catch (JsonProcessingException e) {
            fail("The UserId could not be serialized");
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Test
    @DisplayName("Tests if Userdata Serializes to correct JSON String")
    public void userDataSerializerTest() {
        try {
            String res = mapper.writeValueAsString(userData);
            assertTrue(res.equals(userDataJsonResult));

        } catch (JsonProcessingException e) {
            fail("The UserData could not be serialized");
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Test
    @DisplayName("Tests if User Serializes to correct JSON String")
    public void userSerializerTest() {
        try {
            String res = mapper.writeValueAsString(user);
            System.out.println(res);
            assertTrue(res.equals(userJsonResult));

        } catch (JsonProcessingException e) {
            fail("The User could not be serialized");
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Test
    @DisplayName("Tests if User JSON Deserializes to correct POJO")
    public void userDeserializerTest() {
        try {
            User pojoUser = mapper.readValue(userJsonResult, User.class);
            assertTrue(user.equals(pojoUser), "The Deserialization did not work");
            assertTrue(pojoUser.getUserId().equals(userId), "The Deserialization did not work");
            assertTrue(pojoUser.getUserData().getPassword().equals(userData.getPassword()), "The Deserialization did not work");

        } catch (JsonMappingException e) {
            fail("Did not Map correctly");
            System.out.println(e.getLocalizedMessage());
        } catch (JsonProcessingException e) {
            fail("Did not Process correctly");
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Test
    @DisplayName("Tests if UserId JSON Deserializes to correct POJO")
    public void userIdDeserializerTest() {
        try {
            UserId pojoUserId = mapper.readValue(userIdJsonResult, UserId.class);
            assertTrue(userId.equals(pojoUserId), "The Deserialization did not work");

        } catch (JsonMappingException e) {
            fail("Did not Map correctly");
            System.out.println(e.getLocalizedMessage());
        } catch (JsonProcessingException e) {
            fail("Did not Process correctly");
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Test
    @DisplayName("Tests if UserData JSON Deserializes to correct POJO")
    public void userDataDeserializerTest() {
        try {
            UserData pojoUserData = mapper.readValue(userDataJsonResult, UserData.class);
            assertTrue(userData.getPassword().equals(pojoUserData.getPassword()), "The Deserialization did not work");

        } catch (JsonMappingException e) {
            fail("Did not Map correctly");
            System.out.println(e.getLocalizedMessage());
        } catch (JsonProcessingException e) {
            fail("Did not Process correctly");
            System.out.println(e.getLocalizedMessage());
        }
    }
}
 