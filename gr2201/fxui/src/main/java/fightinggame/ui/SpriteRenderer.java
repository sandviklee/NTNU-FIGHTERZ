package fightinggame.ui;

import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import java.util.ArrayList;
import java.util.HashMap;
import fightinggame.game.AnimationSpritePlayer;
import fightinggame.game.Effectbox;
import fightinggame.game.GameCharacter;
import fightinggame.game.Projectile;
import fightinggame.game.Terrain;
import fightinggame.game.WorldEntity;
/**
 * The {@code SpriteRenderer} class does all the rendering of spriteimages.
 */
public class SpriteRenderer {
    private HashMap<String, Image> playerSprites;
    private HashMap<String, Image> assetSprites;
    private HashMap<WorldEntity, Boolean> isPlaying = new HashMap<>();
    private HashMap<WorldEntity, Double> lastPercentage = new HashMap<>();
    private String path;
    private GraphicsContext content;
    private Image backgroundImg;
    private ArrayList<WorldEntity> entities = new ArrayList<>();
    private AnimationSpritePlayer backgroundSpritePlayer;
    private Color circleColor;
    private Color hurtBoxColor;
    private Color hitBoxColor;
    private boolean rectOn;
    private int totalFrames;
    private int[] screenSize = {1920, 1080};
    private int[] characterSize = {260, 200};
    private int[] projectileSize = {80, 80};
    private int[] hudSize = {440, 230};
    private int[] hud1Pos = {500, 790};
    private int[] hud2Pos = {1045, 790};
    /**
     * Creates a SpriteRenderer
     * @param canvas        asserts the world canvas to be drawn on
     * @param entities      asserts the worldentities that needs to be drawn
     * @param playerSprites asserts the hashmap of playersprites
     * @param assetSprites  asserts the assetsprites, for example the hud of characters
     */
    public SpriteRenderer(Canvas canvas, ArrayList<WorldEntity> entities, HashMap<String, Image> playerSprites, HashMap<String, Image> assetSprites) {
        this.path = "../fxui/src/main/resources/fightinggame/ui/";		
        content = canvas.getGraphicsContext2D();
        this.entities = entities;
        this.playerSprites = playerSprites;
        this.assetSprites = assetSprites;
        this.backgroundImg = assetSprites.get("Backgroundspritesheet");
        this.totalFrames = 16;
        this.circleColor = Color.RED;
        this.hurtBoxColor = Color.LIGHTBLUE;
        this.hitBoxColor = Color.RED;
        this.rectOn = false;
        this.backgroundSpritePlayer = new AnimationSpritePlayer(totalFrames, true, 0, 4);
        for (WorldEntity worldEntity : entities) {
            lastPercentage.put(worldEntity, 0.0);
            isPlaying.put(worldEntity, false);
        }
    }
    /**
     * Draws all the images needed in their current state
     */
    public void update() {
        /*
         * Draws the background image and updates the animationspriteplayer for next tick.
         * (backgroundImg.getWidth()/totalFrames) is the width of the backgroundImg, since the whole image is a sprite.
         */
        double[] backgroundImgSize = {backgroundImg.getWidth()/totalFrames, backgroundImg.getHeight()};
        content.drawImage(backgroundImg, backgroundImgSize[0]*backgroundSpritePlayer.getCurrentFrame(), 0, backgroundImgSize[0], backgroundImgSize[1], 0, 0, screenSize[0], screenSize[1]);
        backgroundSpritePlayer.next();
        /*
         * Radius is the size of the "center dot" of the effectbox when drawn.
         */
        int radius = 10;
        for (WorldEntity entity : entities) {
            if (entity instanceof GameCharacter) {
                /*
                 * The width and height is the universal height and width of the character sprite.
                 * This is not the Image width and height, rather the actual sprite on the canvas.
                 * PosX and PosY is the position where the Effectbox gets drawn.
                 */
                double[] characterPos = {entity.getX() - characterSize[0]/2, entity.getY() - characterSize[1]/2};
                Effectbox hurtbox = entity.getHurtBox();
                Image characterSpriteImg = playerSprites.get(entity.getName() + entity.getCurrentAction().getName());
                /*
                 * This draws the characters.
                 * The characterSpriteImgSize is set from the universal game character sprite image.
                 * The reason we cant use the same method as the background image is because the character sprites
                 vary in size.
                 * The reason behind: entity.getFacingDirection() > 0 ? posX : (posX + 275), is to render the correct
                 facing direction of the character. The magic number 275 is used to position the character correctly
                 after the image has been flipped. The image flips at the upper left point of the image.
                 */
                int[] characterSpriteImgSize = {500, 402};
                content.drawImage(characterSpriteImg, characterSpriteImgSize[0]*entity.getCurrentAction().getCurrentFrame(), 0, characterSpriteImgSize[0], characterSpriteImgSize[1],
                (entity.getFacingDirection() > 0 ? characterPos[0]: (characterPos[0] + 275)), characterPos[1], characterSize[0]*entity.getFacingDirection(), characterSize[1]); 
                /*
                 * If rectOn then draw the hitbox/hurtboxes.
                 */
                if (rectOn) {
                    drawCircle(entity, radius, content, circleColor);
                    drawBox(hurtbox, content, hurtBoxColor); //draw hurtbox
                    if (entity.getCurrentAction().getHitBox() != null) { //draw hitbox
                        Effectbox hitbox = entity.getCurrentAction().getHitBox();
                        drawBox(hitbox, content, hitBoxColor);
                    }
                }
            } else if (entity instanceof Terrain) {
                Image terrainImg = assetSprites.get("BackgroundTerrain");
                Effectbox terrainHitbox = entity.getHitBox();
                double[] terrainSize = {terrainImg.getWidth(), terrainImg.getHeight()};
                /*
                 * This draws the terrain.
                 */
                content.drawImage(terrainImg, 0, 0, terrainSize[0], terrainSize[1], entity.getHitBox().getPosX(), entity.getHitBox().getPosY(), entity.getHitBox().getWidth(), entity.getHitBox().getHeight());
                /*
                 * If rectOn then draw the terrain hitbox.
                 */
                if (rectOn) {
                    drawCircle(entity, radius, content, circleColor);
                    drawBox(terrainHitbox, content, hitBoxColor);
                }    
            } else if (entity instanceof Projectile) {
                double[] projectilePos = {entity.getX() - (projectileSize[0]/2 - 10), entity.getY() - (projectileSize[1]/2)};
                Effectbox projectileHitbox = entity.getHitBox();
                Image projectileSpriteImg = playerSprites.get(entity.getName() + entity.getCurrentAction().getName());
                int[] projectileSpriteImgSize = {134, 136};
                /*
                 * Draws the projectile.
                 */
                content.drawImage(projectileSpriteImg, projectileSpriteImgSize[0]*entity.getCurrentAction().getCurrentFrame(), 0, projectileSpriteImgSize[0], projectileSpriteImgSize[1],
                projectilePos[0], projectilePos[1], projectileSize[0], projectileSize[1]);
                if (rectOn) {
                    drawCircle(entity, radius, content, circleColor);
                    drawBox(projectileHitbox, content, hitBoxColor);
                }
            }
        }
        for (WorldEntity entity : entities) {
            if (entity instanceof GameCharacter) {
                /*
                 * Here we draw the character HUD and player number over the character.
                 */
                double[] playerPos = {entity.getX() - characterSize[0]/2, entity.getY() - characterSize[1]/2};
                int playerInt = entity.getPlayerNumb();
                /*
                 * All these positions are defined as immutable arrays.
                 * playerNumbOffset is defined as the offsets for the player number image. (x, y)
                 * offScreenPos is the positions (x, y, x, y) where the image should appear at a set x or y position.
                 * playerNumbSize is the size of the player number image on the canvas. (x, y)
                 * playerNumbStaticOffset is the offset to where the playerNumb should appear (so it hovers over the head of the character).
                 */
                int[] playerNumbOffset = {110, -45};
                int[] offScreenPos = {10, 80, 1910, 0};
                int[] playerNumbSize = {60, 65};
                int playerNumbStaticOffset = 20;
                /*
                 * Loads in bot the playerInt image and the flipped version.
                 */
                Image playerIntImg = assetSprites.get("Assetsplayer" + playerInt);
                Image playerIntImgFlip = assetSprites.get("Assetsplayer" + playerInt + "Flip");
                if ((playerPos[1] + characterSize[1]) <= offScreenPos[3]) {
                    content.drawImage(playerIntImg, playerPos[0] + playerNumbOffset[0], offScreenPos[1], -playerNumbSize[0], -playerNumbSize[1]); //draws the player number at the top of the screen when the character is outside.
                } else if (playerPos[0]  + characterSize[0] + playerNumbStaticOffset <= offScreenPos[0]) {
                    content.drawImage(playerIntImgFlip, offScreenPos[0], playerPos[1] + playerNumbOffset[1], playerNumbSize[0], playerNumbSize[1]); //draws the player number at the left side of the screen when outside of the screen.
                } else if (playerPos[0] - playerNumbStaticOffset >= offScreenPos[2]) {
                    content.drawImage(playerIntImgFlip, offScreenPos[2], playerPos[1] + playerNumbOffset[1], -playerNumbSize[0], playerNumbSize[1]); //draws the player number at the right side of the screen when outside of the screen.
                } else {
                    content.drawImage(playerIntImg, playerPos[0] + playerNumbOffset[0], playerPos[1] + playerNumbOffset[1], playerNumbSize[0], playerNumbSize[1]); //draws the player number over the head of the character.
                }
                Image hudImage = assetSprites.get("Assets" + entity.getName() + "P" + entity.getPlayerNumb());
                /*
                 * The hudSize are positions for where the hud should be.
                 */
                int[] hudImgSize = {730, 379};
                int[] hudSizeOffset = {3};
                double currentPercentage = lastPercentage.get(entity);
                /*
                 * This draws the hud. 
                 */
                content.drawImage(hudImage, 0, 0, hudImgSize[0], hudImgSize[1], entity.getPlayerNumb() == 1 ? hud1Pos[0] : hud2Pos[0], hud2Pos[1], hudSize[0], hudSize[1]); //draws the players HUD.
                drawText(entity, entity.getPlayerNumb() == 1 ? hud1Pos[0] + 315 : hud2Pos[0] + 125, hud2Pos[1] + 100, 200, currentPercentage%5 == 0 ? 45 : 50, content, currentPercentage < 255 ? Color.rgb(255, (int) (255 - currentPercentage), (int) (255 - currentPercentage)) : Color.RED, Color.BLACK, "" + currentPercentage + "%"); //draws the percentage to the hud.
                
                if (currentPercentage < entity.getPercentage()) {
                    lastPercentage.put(entity, lastPercentage.get(entity) + 1);
                    
                } else if (currentPercentage > entity.getPercentage()) {
                    lastPercentage.put(entity, 0.0);
                }
                /*
                 * This draws the deathcounter for the player.
                 */
                drawText(entity, entity.getPlayerNumb() == 1 ? hud1Pos[0] + 310 : hud2Pos[0] + 123, hud2Pos[1] + 35, 200, 28, content, entity.getDeathCounter() < 2 ? Color.WHITE : Color.RED, Color.BLACK, "Deaths: " + entity.getDeathCounter()); //draws the deathcounter to the hud.
            }
        }
    }

    /**
     * Updates the World Entity ArrayList
     * @param worldEntities declares the WorldEntity that should be added
     */
    public void updateRendererWorldEntities(ArrayList<WorldEntity> worldEntities) {
        this.entities = worldEntities;
    }

    /**
     * Draws an Effectbox
     * @param hitbox declares the effectbox
     * @param cntn   declares the GraphicsContext
     * @param color  declares the color of the box
     */
    private void drawBox(Effectbox hitbox, GraphicsContext cntn, Color color) {
        cntn.setStroke(color);
        cntn.strokeRect(hitbox.getPosX(), hitbox.getPosY(), hitbox.getWidth(), hitbox.getHeight());
    }

    /**
     * Draws the circular point in the middle of an effetbox
     * @param entity declares the WorldEntity
     * @param radius declares the radius of the circle
     * @param cntn   declares GraphicsContext 
     * @param color  declares the color of the circle
     */
    private void drawCircle(WorldEntity entity, int radius, GraphicsContext cntn, Color color) {
        content.setFill(color);
        content.fillOval(entity.getX() + radius/2, entity.getY(), radius, radius);
    }

    /**
     * Draws text. eg. percentage and deathcounter.
     * @param entity      declares the WorldEntity
     * @param hudPosX     declares the hud X position
     * @param hudPosY     declares the hud Y position
     * @param maxWidth    declares the maximum Width of the text
     * @param fontSize    declares the font size
     * @param cntn        declares the GraphicsContext
     * @param color       declares the color
     * @param strokeColor declares the stroke color
     * @param text        declares the text
     */
    private void drawText(WorldEntity entity, int hudPosX, int hudPosY, int maxWidth, int fontSize, GraphicsContext cntn, Color color, Color strokeColor, String text) {
        Font font = Font.loadFont("file:" + path + "Fonts/KabelBold.ttf", fontSize);
        cntn.setFill(color);
        cntn.setStroke(strokeColor);
        
        cntn.setTextAlign(TextAlignment.CENTER);
        cntn.setTextBaseline(VPos.CENTER);
        cntn.setFont(font);

        cntn.fillText(text, hudPosX, hudPosY, maxWidth); //draws the players percentage.
        cntn.strokeText(text, hudPosX, hudPosY, maxWidth);
    }
}
