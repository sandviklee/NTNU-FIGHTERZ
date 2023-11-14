package fightinggame.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fightinggame.game.Effectbox;
import fightinggame.game.PlayerProperties;
import fightinggame.game.Vector;
import fightinggame.game.ActionProperties;

import fightinggame.utils.json.characterattributes.GameplayModule;

public class GameplayModuleTest {

    private ObjectMapper mapper = new ObjectMapper();
    private final String validActionProperties = "{\"actionName\":\"idle\",\"hitBox\":{\"width\":0,\"height\":0,\"offsetX\":0,\"offsetY\":0,\"isTraversible\":false},\"isSelfInterruptible\":false,\"isEnemyInterruptible\":true,\"actionPriority\":1,\"damage\":0,\"knockback\":[0,0,0,0],\"duration\":400,\"hitBoxStartTime\":0,\"totalFrames\":0,\"isAnimationLoop\":false,\"animationLoopStartTime\":0,\"isProjectile\":false,\"isMovement\":false}";
    private final String nonValidActionProperties = "{\"actidonName\":\"idle\",\"hisdtBox\":{\"width\":0,\"height\":0,\"offsetX\":0,\"offsetY\":0,\"isTraversible\":false},\"isSelfInterruptible\":false,\"isEnemyInterruptible\":true,\"actionPriority\":1,\"damage\":0,\"knockback\":[0,0,0,0],\"duration\":400,\"hitBoxStartTime\":0,\"totalFrames\":0,\"isAnimationLoop\":false,\"animationLoopsdaStartTime\":0,\"isProjdectile\":false,\"issMovement\":false}";
    private final String validEffectbox = "\"hitBox\":{\"width\":10,\"height\":10,\"offsetX\":20,\"offsetY\":20,\"isTraversible\":false}";
    private final String nonValidEffectbox = "\"hitBdox\":{\"widsth\":-10,\"height\":10,\"offsetX\":20,\"offsetY\":20,\"isTrsaversible\":false}";
    private final String validPlayerProperties = "{\"characterName\":\"AngryCyclist\",\"weight\":1.0,\"speed\":1.0,\"hurtbox\":{\"width\":80,\"height\":172},\"actions\":[{\"actionName\":\"Idle\",\"isSelfInterruptible\":true,\"isEnemyInterruptible\":true,\"actionPriority\":0,\"damage\":0,\"duration\":18,\"hitBoxStartTime\":0,\"totalFrames\":18,\"isAnimationLoop\":false,\"animationLoopStartTime\":0,\"isProjectile\":false,\"isMovement\":false},{\"actionName\":\"HitStun\",\"isSelfInterruptible\":false,\"isEnemyInterruptible\":false,\"actionPriority\":4,\"damage\":0,\"duration\":8,\"hitBoxStartTime\":0,\"totalFrames\":8,\"isAnimationLoop\":false,\"animationLoopStartTime\":0,\"isProjectile\":false,\"isMovement\":false},{\"actionName\":\"Jump\",\"isSelfInterruptible\":true,\"isEnemyInterruptible\":true,\"actionPriority\":3,\"damage\":0,\"knockback\":[0,48,0,-4,-1],\"duration\":2,\"hitBoxStartTime\":0,\"totalFrames\":5,\"isAnimationLoop\":false,\"animationLoopStartTime\":0,\"isProjectile\":false,\"isMovement\":true},{\"actionName\":\"Run\",\"isSelfInterruptible\":true,\"isEnemyInterruptible\":true,\"actionPriority\":1,\"damage\":0,\"knockback\":[8,0,0,0,1],\"duration\":10,\"hitBoxStartTime\":0,\"totalFrames\":10,\"isAnimationLoop\":false,\"animationLoopStartTime\":0,\"isProjectile\":false,\"isMovement\":true},{\"actionName\":\"Run\",\"isSelfInterruptible\":true,\"isEnemyInterruptible\":true,\"actionPriority\":1,\"damage\":0,\"knockback\":[8,0,0,0,-1],\"duration\":10,\"hitBoxStartTime\":0,\"totalFrames\":10,\"isAnimationLoop\":false,\"animationLoopStartTime\":0,\"isProjectile\":false,\"isMovement\":true},{\"actionName\":\"Jump\",\"isSelfInterruptible\":true,\"isEnemyInterruptible\":true,\"actionPriority\":3,\"damage\":0,\"knockback\":[8,-48,0,4,1],\"duration\":2,\"hitBoxStartTime\":0,\"totalFrames\":5,\"isAnimationLoop\":false,\"animationLoopStartTime\":0,\"isProjectile\":false,\"isMovement\":true},{\"actionName\":\"Jump\",\"isSelfInterruptible\":true,\"isEnemyInterruptible\":true,\"actionPriority\":3,\"damage\":0,\"knockback\":[8,48,0,-4,-1],\"duration\":2,\"hitBoxStartTime\":0,\"totalFrames\":5,\"isAnimationLoop\":false,\"animationLoopStartTime\":0,\"isProjectile\":false,\"isMovement\":true},{\"actionName\":\"NeutralSpecial\",\"hitBox\":{\"width\":80,\"height\":80,\"offsetX\":100,\"offsetY\":-40,\"isTraversible\":false},\"isSelfInterruptible\":false,\"isEnemyInterruptible\":true,\"actionPriority\":2,\"damage\":30,\"knockback\":[30,0,0,0],\"gameCharName\":\"AngryCyclist\",\"duration\":18,\"hitBoxStartTime\":10,\"totalFrames\":18,\"isAnimationLoop\":false,\"animationLoopStartTime\":0,\"isProjectile\":true,\"isMovement\":false},{\"actionName\":\"SideSpecial\",\"hitBox\":{\"width\":240,\"height\":180,\"offsetX\":0,\"offsetY\":-12,\"isTraversible\":false},\"isSelfInterruptible\":true,\"isEnemyInterruptible\":false,\"actionPriority\":2,\"damage\":20,\"knockback\":[20,0,0,0,1],\"duration\":10000,\"hitBoxStartTime\":7,\"totalFrames\":19,\"isAnimationLoop\":true,\"animationLoopStartTime\":13,\"isProjectile\":false,\"isMovement\":true},{\"actionName\":\"SideSpecial\",\"hitBox\":{\"width\":240,\"height\":180,\"offsetX\":0,\"offsetY\":-12,\"isTraversible\":false},\"isSelfInterruptible\":true,\"isEnemyInterruptible\":false,\"actionPriority\":2,\"damage\":15,\"knockback\":[20,0,0,0,-1],\"duration\":10000,\"hitBoxStartTime\":7,\"totalFrames\":19,\"isAnimationLoop\":true,\"animationLoopStartTime\":13,\"isProjectile\":false,\"isMovement\":true},{\"actionName\":\"UpSpecial\",\"hitBox\":{\"width\":200,\"height\":50,\"offsetX\":0,\"offsetY\":-50,\"isTraversible\":false},\"isSelfInterruptible\":false,\"isEnemyInterruptible\":true,\"actionPriority\":1,\"damage\":30,\"knockback\":[0,40,0,-1,-1],\"duration\":8,\"hitBoxStartTime\":0,\"totalFrames\":8,\"isAnimationLoop\":false,\"animationLoopStartTime\":0,\"isProjectile\":false,\"isMovement\":true},{\"actionName\":\"DownSpecial\",\"isSelfInterruptible\":true,\"isEnemyInterruptible\":true,\"hitBox\":{\"width\":200,\"height\":200,\"offsetX\":0,\"offsetY\":0,\"isTraversible\":false},\"actionPriority\":1,\"damage\":25,\"knockback\":[0,24,0,0,1],\"duration\":3,\"hitBoxStartTime\":0,\"totalFrames\":3,\"isAnimationLoop\":false,\"animationLoopStartTime\":0,\"isProjectile\":false,\"isMovement\":true},{\"actionName\":\"SideNormal\",\"hitBox\":{\"width\":60,\"height\":80,\"offsetX\":65,\"offsetY\":0,\"isTraversible\":false},\"isSelfInterruptible\":false,\"isEnemyInterruptible\":true,\"actionPriority\":4,\"damage\":10,\"knockback\":[20,0,0,0,1],\"duration\":8,\"hitBoxStartTime\":3,\"totalFrames\":10,\"isAnimationLoop\":false,\"animationLoopStartTime\":0,\"isProjectile\":false,\"isMovement\":false},{\"actionName\":\"SideNormal\",\"hitBox\":{\"width\":60,\"height\":80,\"offsetX\":-65,\"offsetY\":0,\"isTraversible\":false},\"isSelfInterruptible\":false,\"isEnemyInterruptible\":true,\"actionPriority\":4,\"damage\":10,\"knockback\":[20,0,0,0,-1],\"duration\":8,\"hitBoxStartTime\":3,\"totalFrames\":10,\"isAnimationLoop\":false,\"animationLoopStartTime\":0,\"isProjectile\":false,\"isMovement\":false},{\"actionName\":\"UpNormal\",\"hitBox\":{\"width\":150,\"height\":50,\"offsetX\":0,\"offsetY\":-50,\"isTraversible\":false},\"isSelfInterruptible\":false,\"isEnemyInterruptible\":true,\"actionPriority\":1,\"damage\":15,\"knockback\":[0,-20,0,0],\"duration\":7,\"hitBoxStartTime\":1,\"totalFrames\":7,\"isAnimationLoop\":false,\"animationLoopStartTime\":0,\"isProjectile\":false,\"isMovement\":false},{\"actionName\":\"DownNormal\",\"hitBox\":{\"width\":160,\"height\":90,\"offsetX\":65,\"offsetY\":0,\"isTraversible\":false},\"isSelfInterruptible\":true,\"isEnemyInterruptible\":true,\"actionPriority\":1,\"damage\":20,\"knockback\":[26,-8,0,0],\"duration\":9,\"hitBoxStartTime\":4,\"totalFrames\":9,\"isAnimationLoop\":false,\"animationLoopStartTime\":0,\"isProjectile\":false,\"isMovement\":false}]}";
    private final String nonValidPlayerProperties = "{}";
    private final String validVector = "\"knockback\":[0,0,0,0]";
    private final String validVectorWithDirection = "\"knockback\":[1,1,1,1,1]";
    private final String nonValidVector = "[1]";
    private final String empty = "";

    @BeforeEach
    public void setup() {
        this.mapper.registerModule(new GameplayModule());
    }

    @Test
    @DisplayName("Tests if ActionProperties JSON Deserializes to correct POJO")
    public void testActionProperties() {
        try {
            ActionProperties actionProperties = mapper.readValue(validActionProperties, ActionProperties.class);
            assertTrue(actionProperties != null);

        } catch (JsonMappingException e) {
            fail("Did not Map correctly");
            System.out.println(e.getLocalizedMessage());
        } catch (JsonProcessingException e) {
            fail("Did not Process correctly");
            System.out.println(e.getLocalizedMessage());
        }

        Assertions.assertThrows(JsonMappingException.class, () -> {
            mapper.readValue(empty, ActionProperties.class);
        }, "This ActionProperties is not possible");

        Assertions.assertThrows(NullPointerException.class, () -> {
            mapper.readValue(nonValidActionProperties, ActionProperties.class);
        }, "This should not deserialize to ActionProperties");

    }

    @Test
    @DisplayName("Tests if Effectbox JSON Deserializes to correct POJO")
    public void testEffectbox() {
        try {
            Effectbox effectbox = mapper.readValue(validEffectbox, Effectbox.class);
        } catch (JsonMappingException e) {
            fail("Did not Map correctly");
            System.out.println(e.getLocalizedMessage());
        } catch (JsonProcessingException e) {
            fail("Did not Process correctly");
            System.out.println(e.getLocalizedMessage());
        }

        Assertions.assertThrows(JsonMappingException.class, () -> {
            mapper.readValue(empty, Effectbox.class);
        }, "This Effectbox is not possible");
    }

    @Test
    public void testPlayerProperties() {
        try {
            PlayerProperties playerProperties = mapper.readValue(validPlayerProperties, PlayerProperties.class);
        } catch (JsonMappingException e) {
            fail("Did not Map correctly");
            System.out.println(e.getLocalizedMessage());
        } catch (JsonProcessingException e) {
            fail("Did not Process correctly");
            System.out.println(e.getLocalizedMessage());
        }

        Assertions.assertThrows(JsonMappingException.class, () -> {
            mapper.readValue(empty, PlayerProperties.class);
        }, "This PlayerProperties is not possible");
        Assertions.assertThrows(NullPointerException.class, () -> {
            mapper.readValue(nonValidPlayerProperties, PlayerProperties.class);
        }, "This should not deserialize to PlayerProperties");

    }

    @Test
    @DisplayName("Tests if Vector JSON Deserializes to correct POJO")
    public void testVector() {
        try {
            Vector vector = mapper.readValue(validVector, Vector.class);
            Vector vectorWithDirection = mapper.readValue(validVectorWithDirection, Vector.class);
        } catch (JsonMappingException e) {
            fail("Did not Map correctly");
            System.out.println(e.getLocalizedMessage());
        } catch (JsonProcessingException e) {
            fail("Did not Process correctly");
            System.out.println(e.getLocalizedMessage());
        }

        Assertions.assertThrows(JsonMappingException.class, () -> {
            mapper.readValue(empty, Vector.class);
        }, "This Vector is not possible");

        Assertions.assertThrows(NullPointerException.class, () -> {
            Vector nullVector = mapper.readValue(nonValidVector, Vector.class);
        }, "This should not deserialize to nullVector");
    }

}
