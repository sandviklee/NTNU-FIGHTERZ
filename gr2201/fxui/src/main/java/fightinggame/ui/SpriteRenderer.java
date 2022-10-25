package fightinggame.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;

import fightinggame.game.WorldEntity;

public class SpriteRenderer {
    private HashMap<String, Image> playerSprites;
    private GraphicsContext content;
    private Image backgroundImg;
    private ArrayList<WorldEntity> entities = new ArrayList<>();

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
            content.drawImage(playerSprites.get(entity.getName() + entity.getCurrentAction().getName()), 500*entity.getCurrentAction().getCurrentFrame(), 0, 500, 402, entity.getPoint().getX(), entity.getPoint().getY(), 250, 191);
        }
    }
}
