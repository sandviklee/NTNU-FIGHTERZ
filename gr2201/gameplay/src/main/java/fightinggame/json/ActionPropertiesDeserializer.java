
package fightinggame.json;

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


public class ActionPropertiesDeserializer extends JsonDeserializer<ActionProperties> {

    @Override
    public ActionProperties deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
       TreeNode treeNode = p.getCodec().readTree(p);
       return deserialize((JsonNode) treeNode);

    }

    public ActionProperties deserialize(JsonNode jsonNode) {
        if (jsonNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) jsonNode;

            JsonNode effectboxNode = objectNode.get("hitbox");
            Effectbox box = EffectboxDeserializer.deserialize(effectboxNode);
            
            JsonNode knockbackNode = objectNode.get("knockback");
            Vector knockback = VectorDeserializer.deserialize(knockbackNode);

            JsonNode actionPriorityNode = objectNode.get("actionPriority");
            JsonNode damageNode = objectNode.get("damage");
            JsonNode durationNode = objectNode.get("duration");
            JsonNode hitBoxStartTimeNode = objectNode.get("hitBoxStartTime");
            JsonNode animationLoopStartTimeNode = objectNode.get("animationLoopStartTime");

            JsonNode isAnimationLoopNode = objectNode.get("isAnimationLoop");

            // Must get these fields "hitBox", "isSelfInterruptible", "actionpPriority", "damage", "knockback", "duration", "isProjectile"

            // Effectbox hitbox, String spriteName, Vector knockback, boolean isSelfInterruptible, boolean isEnemyInterruptible, int duration, int actionPriority, int hitBoxStartTime, int damage, int totalFrames, boolean animationLoop, int animationLoopStartFrame

            ActionProperties actionProperties = new ActionProperties();
            return actionProperties;
           }
           
        return null;
    }    
}
