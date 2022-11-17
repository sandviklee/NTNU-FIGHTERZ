package fightinggame.ui;

import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import fightinggame.game.AnimationSpritePlayer;
import fightinggame.game.Effectbox;
import fightinggame.game.GameCharacter;
import fightinggame.game.Projectile;
import fightinggame.game.Terrain;
import fightinggame.game.World;
import fightinggame.game.WorldEntity;

public class SpriteRenderer {
    private HashMap<String, Image> playerSprites;
    private HashMap<String, Image> assetSprites;
    private HashMap<WorldEntity, Boolean> isPlaying = new HashMap<>();
    private HashMap<WorldEntity, Double> lastPrecentage = new HashMap<>();

    private GraphicsContext content;
    private Image backgroundImg;
    private ArrayList<WorldEntity> entities = new ArrayList<>();
    private AnimationSpritePlayer backgroundSpritePlayer = new AnimationSpritePlayer(16, true, 0, 4);

    private boolean rectOn = false;

    public SpriteRenderer(Canvas canvas, ArrayList<WorldEntity> entities, HashMap<String, Image> playerSprites, HashMap<String, Image> assetSprites) {
        content = canvas.getGraphicsContext2D();
        backgroundImg = new Image((getClass().getResource("trainingstage.jpeg")).toString(), 1920, 1080, false, false);
        this.entities = entities;
        this.playerSprites = playerSprites;
        this.assetSprites = assetSprites;
        this.backgroundImg = assetSprites.get("Backgroundspritesheet");

        for (WorldEntity worldEntity : entities) {
            lastPrecentage.put(worldEntity, 0.0);
            isPlaying.put(worldEntity, false);
        }
    }

    public void update() {
        content.drawImage(backgroundImg, 500*backgroundSpritePlayer.getCurrentFrame(), 0, 420, 252, 0, 0, 1920, 1080);
        backgroundSpritePlayer.next();
        
        int radius = 10;
        
        for (WorldEntity entity : entities) {
            if (entity instanceof GameCharacter) {
                int width = 260;
                int height = 200;
                double posX = entity.getX() - width/2;
                double posY = entity.getY() - height/2;
                
                Effectbox hurtbox = entity.getHurtBox();
                Image spriteImg = playerSprites.get(entity.getName() + entity.getCurrentAction().getName());

                content.drawImage(spriteImg, 500*entity.getCurrentAction().getCurrentFrame(), 0, 500, 402,
                (entity.getFacingDirection() > 0 ? posX : (posX + 275)), posY, width*entity.getFacingDirection(), height); //draws the player itself.

                if (rectOn) {
                    drawCircle(entity, radius, content, Color.RED);
                    drawBox(hurtbox, content, Color.LIGHTBLUE);

                    if (entity.getCurrentAction().getHitBox() != null) {
                        Effectbox hitbox = entity.getCurrentAction().getHitBox();
                        drawBox(hitbox, content, Color.RED);
                    }
    
                }



            } else if (entity instanceof Terrain) {
                Effectbox hitbox = entity.getHitBox();
                content.drawImage(assetSprites.get("BackgroundTerrain"), 0, 0, 963, 278, entity.getHitBox().getPosX(), entity.getHitBox().getPosY(), entity.getHitBox().getWidth(), entity.getHitBox().getHeight());
                if (rectOn) {
                    drawCircle(entity, radius, content, Color.RED);
                    drawBox(hitbox, content, Color.BLUE);
                }    

            } else if (entity instanceof Projectile) {
                int width = 80;
                int height = 80;
                double posX = entity.getX() - (width/2 - 10);
                double posY = entity.getY() - (height/2);
        
                Effectbox hitbox = entity.getHitBox();
                Image spriteImg = playerSprites.get(entity.getName() + entity.getCurrentAction().getName());

                content.drawImage(spriteImg, 134*entity.getCurrentAction().getCurrentFrame(), 0, 134, 136,
                posX, posY, width, height);
                if (rectOn) {
                    drawCircle(entity, radius, content, Color.RED);
                    drawBox(hitbox, content, Color.RED);
                }

            }

        }
        for (WorldEntity entity : entities) {
            if (entity instanceof GameCharacter) {
                int[] screenSize = {1920, 1080};
                int[] playerProperties = {260, 200};
                double playerPosX = entity.getX() - playerProperties[0]/2;
                double playerPosY = entity.getY() - playerProperties[1]/2;

                int playerInt = 0;
                int[] playerIntOffset = {110, -45};

                int[] hudProperties = {440, 230};
                int hud1PosX = 500;
                int hud2PosX = 1045;
                int hudPosY = 790;

                double i = lastPrecentage.get(entity);
         
                ArrayList<String> availKeys = entity.getAvailKeys();
                playerInt = availKeys.get(2).equals("W") ? 1 : (availKeys.get(2).equals("I") ? 2 : 0);
                Image playerIntImg = assetSprites.get("Assetsplayer" + playerInt);
                Image playerIntImgFlip = assetSprites.get("Assetsplayer" + playerInt + "Flip");

                if ((playerPosY + playerProperties[1]) <= 0) {
                    content.drawImage(playerIntImg, playerPosX + playerIntOffset[0], 80, -60, -65); //draws the player number over the head of the character.
                } else if (playerPosX  + playerProperties[0] + 20 <= 0) {
                    content.drawImage(playerIntImgFlip, 10, playerPosY + playerIntOffset[1], 60, 65); //draws the player number over the head of the character.
                } else if (playerPosX - 20 >= 1920) {
                    content.drawImage(playerIntImgFlip, 1910, playerPosY + playerIntOffset[1], -60, 65); //draws the player number over the head of the character.
                } else {
                    content.drawImage(playerIntImg, playerPosX + playerIntOffset[0], playerPosY + playerIntOffset[1], 60, 65); //draws the player number over the head of the character.
                }
         
                content.drawImage(assetSprites.get("Assets" + entity.getName() + "P" + entity.getPlayerNumb()), 0, 0, 730, 379, entity.getPlayerNumb() == 1 ? hud1PosX : hud2PosX, hudPosY, hudProperties[0], hudProperties[1]); //draws the players HUD.
            
                drawText(entity, entity.getPlayerNumb() == 1 ? hud1PosX + 315 : hud2PosX + 125, hudPosY + 100, 200, i%5 == 0 ? 45 : 50, content, i < 255 ? Color.rgb(255, (int) (255 - i), (int) (255 - i)) : Color.RED, Color.BLACK, "" + i + "%"); //draws the precentage to the hud.
                
                if (i < entity.getPrecentage()) {
                    lastPrecentage.put(entity, lastPrecentage.get(entity) + 1);
                    
                } else if (i > entity.getPrecentage()) {
                    lastPrecentage.put(entity, 0.0);
                }

                drawText(entity, entity.getPlayerNumb() == 1 ? hud1PosX + 310 : hud2PosX + 123, hudPosY + 35, 200, 28, content, entity.getDeathCounter() < 2 ? Color.WHITE : Color.RED, Color.BLACK, "Deaths: " + entity.getDeathCounter()); //draws the deathcounter to the hud.

            }
        }
    }

    public void updateRendererWorldEntities(ArrayList<WorldEntity> worldEntities) {
        this.entities = worldEntities;
    }

    private void drawBox(Effectbox hitbox, GraphicsContext cntn, Color color) {
        cntn.setStroke(color);
        cntn.strokeRect(hitbox.getPosX(), hitbox.getPosY(), hitbox.getWidth(), hitbox.getHeight());
    }

    private void drawCircle(WorldEntity entity, int radius, GraphicsContext cntn, Color color) {
        content.setFill(color);
        content.fillOval(entity.getX() + radius/2, entity.getY(), radius, radius);
    }

    private void drawText(WorldEntity entity, int hudPosX, int hudPosY, int maxWidth, int fontSize, GraphicsContext cntn, Color color, Color strokeColor, String text) {
        Font font = Font.loadFont("file:../fxui/src/main/resources/fightinggame/ui/Fonts/KabelBold.ttf", fontSize);
        cntn.setFill(color);
        cntn.setStroke(strokeColor);
        
        cntn.setTextAlign(TextAlignment.CENTER);
        cntn.setTextBaseline(VPos.CENTER);
        cntn.setFont(font);

        cntn.fillText(text, hudPosX, hudPosY, maxWidth); //draws the players precentage.
        cntn.strokeText(text, hudPosX, hudPosY, maxWidth);

    }

}
