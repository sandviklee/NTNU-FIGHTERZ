package fightinggame.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

import java.util.ArrayList;
import java.util.HashMap;

import fightinggame.game.Effectbox;
import fightinggame.game.GameCharacter;
import fightinggame.game.WorldEntity;

public class SpriteRenderer {
    private HashMap<String, Image> playerSprites;
    private GraphicsContext content;
    private Image backgroundImg;
    private ArrayList<WorldEntity> entities = new ArrayList<>();
    private boolean showRect = true;

    public SpriteRenderer(Canvas canvas, HashMap<String, Image> sprites, ArrayList<WorldEntity> entities) {
        content = canvas.getGraphicsContext2D();
        backgroundImg = new Image((getClass().getResource("trainingstage.jpeg")).toString(), 1920, 1080, false, false);
        for (WorldEntity entity : entities) {
            this.entities.add(entity);
        }
        this.playerSprites = sprites;
    }

    public void update() {
        content.drawImage(backgroundImg, 0, 0);
        for (WorldEntity entity : entities) {
            if (entity instanceof GameCharacter) {
                int width = 250;
                int height = 190;
                int radius = 10;
    
                Image spriteImg = playerSprites.get(entity.getName() + entity.getCurrentAction().getName());
                double posX = entity.getPoint().getX() - width/2;
                double posY = entity.getPoint().getY() - height/2;
                content.drawImage(spriteImg, 500*entity.getCurrentAction().getCurrentFrame(), 0, 500, 402,
                (entity.getFacingDirection() > 0 ? posX : (posX + 275)), posY, width*entity.getFacingDirection(), height);
                content.setFill(Color.RED);
                content.fillOval(entity.getPoint().getX() + radius/2, entity.getPoint().getY(), radius, radius);
                Effectbox hitbox = entity.getHurtBox();
                content.strokeRect(hitbox.getPosX(), hitbox.getPosY(), hitbox.getWidth(), hitbox.getHeight());
            }
        }
    }
}
