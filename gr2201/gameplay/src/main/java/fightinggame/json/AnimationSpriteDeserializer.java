package fightinggame.json;

import fightinggame.game.AnimationSprite;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class AnimationSpriteDeserializer extends JsonDeserializer<AnimationSprite>{
    
    @Override
    public AnimationSprite deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
       TreeNode treeNode = p.getCodec().readTree(p);
       return deserialize((JsonNode) treeNode);

    }

    public AnimationSprite deserialize(JsonNode jsonNode) {
        if (jsonNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            JsonNode totalFramesNode = objectNode.get("totalFrames");
            JsonNode animationLoopNode = objectNode.get("animationLoop");
            JsonNode animationLoopStartFrameNode = objectNode.get("animationLoopStartFrame");
            JsonNode holdFrameLengthNode = objectNode.get("holdFrameLength");

            if (totalFramesNode == null || animationLoopNode == null || animationLoopStartFrameNode == null || holdFrameLengthNode == null) return null;
            
            int totalFrames = totalFramesNode.intValue();
            Boolean animationLoop = animationLoopNode.booleanValue();
            int animationLoopStartFrame = animationLoopStartFrameNode.intValue();
            int holdFrameLength = holdFrameLengthNode.intValue();

            AnimationSprite animationSprite = new AnimationSprite(totalFrames, animationLoop, animationLoopStartFrame, holdFrameLength);
            return animationSprite;
           }
        return null;
    } 


    // int totalFrames, boolean animationLoop, int animationLoopStartFrame, int holdFrameLength
}

