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
            ObjectNode objectNode = (ObjectNode) jsonNode;
            JsonNode actionNameNode = objectNode.get("actionName");
            JsonNode effectboxNode = objectNode.get("hitbox");
            JsonNode isSelfInterruptibleNode = objectNode.get("isSelfInterruptible");
            JsonNode isEnemyInterruptibleNode = objectNode.get("isEnemyInterruptible");
            JsonNode knockbackNode = objectNode.get("knockback");
            JsonNode actionPriorityNode = objectNode.get("actionPriority");
            JsonNode damageNode = objectNode.get("damage");
            JsonNode durationNode = objectNode.get("duration");
            JsonNode hitBoxStartTimeNode = objectNode.get("hitBoxStartTime");
            JsonNode totalFramesNode = objectNode.get("totalFrames");
            JsonNode animationLoopStartTimeNode = objectNode.get("animationLoopStartTime");
            JsonNode isAnimationLoopNode = objectNode.get("isAnimationLoop");
            JsonNode isProjectileNode = objectNode.get("isProjectile");

            String actionName = actionNameNode.asText();
            int actionPriority = actionPriorityNode.intValue();
            int duration = durationNode.intValue();
            boolean isSelfInterruptible = isSelfInterruptibleNode.asBoolean();
            boolean isEnemyInterruptible = isEnemyInterruptibleNode.asBoolean();
            int totalFrames = totalFramesNode.intValue();
            int animationLoopStartTime = animationLoopStartTimeNode.intValue()
            boolean isAnimationLoop = isAnimationLoopNode.asBoolean();
            int damage = damageNode.intValue();
            boolean isProjectile = isProjectileNode.asBoolean();

            Effectbox hitbox = effectboxDeserializer.deserialize(effectboxNode);
            Vector knockBack = vectorDeserializer.deserialize(knockbackNode);
            
            ActionProperties actionProperties = new ActionProperties(actionName, actionPriority, duration, isSelfInterruptible, isEnemyInterruptible, totalFrames, isAnimationLoop, animationLoopStartTime, knockBack);
            // no knockback
            // ActionProperties actionProperties = new ActionProperties(null, 0, 0, false, false, 0, false, 0);

            return actionProperties;
           }
           
        return null;
    }    
}