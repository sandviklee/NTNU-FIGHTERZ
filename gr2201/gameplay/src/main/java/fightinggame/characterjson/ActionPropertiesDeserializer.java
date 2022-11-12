package fightinggame.characterjson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import fightinggame.game.ActionProperties;
import fightinggame.game.Effectbox;
import fightinggame.game.Vector;


public class ActionPropertiesDeserializer extends JsonDeserializer<ActionProperties> {
    private static EffectboxDeserializer effectboxDeserializer = new EffectboxDeserializer();
    private static VectorDeserializer vectorDeserializer = new VectorDeserializer();

    @Override
    public ActionProperties deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
       TreeNode treeNode = p.getCodec().readTree(p);
       return deserialize((JsonNode) treeNode);

    }

    public ActionProperties deserialize(JsonNode jsonNode) {
        if (jsonNode instanceof ObjectNode) {
            // Get all required nodes
            ObjectNode objectNode = (ObjectNode) jsonNode;
            JsonNode actionNameNode = objectNode.get("actionName");
            JsonNode isSelfInterruptibleNode = objectNode.get("isSelfInterruptible");
            JsonNode isEnemyInterruptibleNode = objectNode.get("isEnemyInterruptible");
            JsonNode actionPriorityNode = objectNode.get("actionPriority");
            JsonNode damageNode = objectNode.get("damage");
            JsonNode durationNode = objectNode.get("duration");
            JsonNode hitBoxStartTimeNode = objectNode.get("hitBoxStartTime");
            JsonNode totalFramesNode = objectNode.get("totalFrames");
            JsonNode animationLoopStartTimeNode = objectNode.get("animationLoopStartTime");
            JsonNode isAnimationLoopNode = objectNode.get("isAnimationLoop");
            JsonNode isProjectileNode = objectNode.get("isProjectile");
            JsonNode isMovementNode = objectNode.get("isMovement");

            // Get all values guaranteed by json
            String actionName = actionNameNode.asText();
            int actionPriority = actionPriorityNode.intValue();
            int duration = durationNode.intValue();
            boolean isSelfInterruptible = isSelfInterruptibleNode.asBoolean();
            boolean isEnemyInterruptible = isEnemyInterruptibleNode.asBoolean();
            int totalFrames = totalFramesNode.intValue();
            int animationLoopStartTime = animationLoopStartTimeNode.intValue();
            boolean isAnimationLoop = isAnimationLoopNode.asBoolean();
            int damage = damageNode.intValue();
            boolean isProjectile = isProjectileNode.asBoolean();
            boolean isMovement = isMovementNode.asBoolean();

            // Check if json contains non required fields
            boolean hasHitbox = objectNode.has("hitBox");
            boolean hasKnockback = objectNode.has("knockback");

            Effectbox hitbox = null;
            int hitBoxStartTime = hitBoxStartTimeNode.asInt();
            if (hasHitbox) {
                JsonNode effectboxNode = objectNode.get("hitBox");
                hitbox = effectboxDeserializer.deserialize(effectboxNode);   
                
            }

            Vector knockBack = null;
            if (hasKnockback) {
                JsonNode knockbackNode = objectNode.get("knockback");
                knockBack = vectorDeserializer.deserialize(knockbackNode);
            }
            //System.out.println(actionName + " action " + hitBoxStartTime);
            // Check what type of ActionProperties that shall be initiated
            if (isProjectile) {
                JsonNode gameCharNameNode = objectNode.get("gameCharName");
                String gameCharName = gameCharNameNode.asText();
                return new ActionProperties(actionName, gameCharName, actionPriority, duration, hitBoxStartTime, isSelfInterruptible, isEnemyInterruptible, totalFrames, isAnimationLoop, animationLoopStartTime, isMovement, knockBack, hitbox, damage, isProjectile);
            }
            
            if (hasHitbox && hasKnockback) {
                return new ActionProperties(actionName, actionPriority, duration, hitBoxStartTime, isSelfInterruptible, isEnemyInterruptible, totalFrames, isAnimationLoop, animationLoopStartTime, isMovement, knockBack, hitbox, damage);
            }

            if (hasKnockback) {
                return new ActionProperties(actionName, actionPriority, duration, isSelfInterruptible, isEnemyInterruptible, totalFrames, isAnimationLoop, animationLoopStartTime, isMovement, knockBack);
            }

            
            // no knockback no hitbox
            ActionProperties actionProperties = new ActionProperties(actionName, actionPriority, duration, isSelfInterruptible, isEnemyInterruptible, totalFrames, isAnimationLoop, animationLoopStartTime, isMovement);
            return actionProperties;
           }
           
        return null;
    }    
}