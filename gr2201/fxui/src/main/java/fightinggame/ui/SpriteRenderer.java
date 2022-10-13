package fightinggame.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import fightinggame.game.WorldEntity;

public class SpriteRenderer {
    int x = 200;
    int y = 200;
    private GraphicsContext content;
    private Image backgroundImg;

    public SpriteRenderer(Canvas canvas, WorldEntity ... worldEntities) {
        content = canvas.getGraphicsContext2D();
        backgroundImg = new Image((getClass().getResource("trainingstage.jpeg")).toString(), 1920, 1080, false, false);
        
    }

    public void update() {
        x += 5;
        content.drawImage(backgroundImg, 0, 0);
        content.strokeRect(x, y, 50, 100);
      
    }
}
