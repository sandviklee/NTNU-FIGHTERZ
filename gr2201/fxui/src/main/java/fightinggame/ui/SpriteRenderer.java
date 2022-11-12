package fightinggame.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.HashMap;


import fightinggame.game.Effectbox;
import fightinggame.game.GameCharacter;
import fightinggame.game.Projectile;
import fightinggame.game.Terrain;
import fightinggame.game.Vector;
import fightinggame.game.WorldEntity;

public class SpriteRenderer {
    private HashMap<String, Image> playerSprites;
    private GraphicsContext content;
    private Image backgroundImg;
    private ArrayList<WorldEntity> entities = new ArrayList<>();
    

    public SpriteRenderer(Canvas canvas, HashMap<String, Image> sprites, ArrayList<WorldEntity> entities) {
        content = canvas.getGraphicsContext2D();
        backgroundImg = new Image((getClass().getResource("trainingstage.jpeg")).toString(), 1920, 1080, false, false);
        this.entities = entities;
        this.playerSprites = sprites;
    }

    public void update() {
        content.drawImage(backgroundImg, 0, 0); //Draws the current bg
        int radius = 10;
        
        for (WorldEntity entity : entities) {
            if (entity instanceof GameCharacter) {
                int width = 250;
                int height = 190;
                
                Effectbox hurtbox = entity.getHurtBox();
                Image spriteImg = playerSprites.get(entity.getName() + entity.getCurrentAction().getName());
                double posX = entity.getPoint().getX() - width/2;
                double posY = entity.getPoint().getY() - height/2;
                content.drawImage(spriteImg, 500*entity.getCurrentAction().getCurrentFrame(), 0, 500, 402,
                (entity.getFacingDirection() > 0 ? posX : (posX + 275)), posY, width*entity.getFacingDirection(), height);
                drawCircle(entity, radius, content, Color.RED);
                drawBox(hurtbox, content, Color.LIGHTBLUE);
                //drawVector((GameCharacter) entity, content);

                if (entity.getCurrentAction().getHitBox() != null) {
                    Effectbox hitbox = entity.getCurrentAction().getHitBox();
                    drawBox(hitbox, content, Color.RED);
                }

            } else if (entity instanceof Projectile) {
                int width = 80;
                int height = 80;
                double posX = entity.getPoint().getX() - width/2;
                double posY = entity.getPoint().getY() - height/2;
                //System.out.println("posX: " + posX + " posY: " + posY);
                Effectbox hitbox = entity.getHitBox();
                Image spriteImg = playerSprites.get(entity.getName() + entity.getCurrentAction().getName());

                content.drawImage(spriteImg, 134*entity.getCurrentAction().getCurrentFrame(), 0, 134, 136,
                posX, posY, width, height);
                drawCircle(entity, radius, content, Color.RED);
                drawBox(hitbox, content, Color.RED);
            } else if (entity instanceof Terrain) {
                Effectbox hitbox = entity.getHitBox();
                drawCircle(entity, radius, content, Color.RED);
                drawBox(hitbox, content, Color.BLUE);
            }
        }
    }

    private void drawBox(Effectbox hitbox, GraphicsContext cntn, Color color) {
        cntn.setStroke(color);
        cntn.strokeRect(hitbox.getPosX(), hitbox.getPosY(), hitbox.getWidth(), hitbox.getHeight());
    }

    private void drawCircle(WorldEntity entity, int radius, GraphicsContext cntn, Color color) {
        content.setFill(color);
        content.fillOval(entity.getPoint().getX() + radius/2, entity.getPoint().getY(), radius, radius);
    }
    /* 
    private void drawVector(GameCharacter entity, GraphicsContext cntn) {
        cntn.setFill(Color.BLACK);

        double dx = entity.getVector().getVx();
        double dy = entity.getVector().getVy();
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

        Transform t = Transform.translate(entity.getPoint().getX(), entity.getPoint().getY());
        t = t.createConcatenation(Transform.rotate(Math.toDegrees(angle), 0, 0));
    
        cntn.strokeLine(entity.getPoint().getX(), entity.getPoint().getY(), entity.getPoint().getX() + len, 0);
    }
    */


}
