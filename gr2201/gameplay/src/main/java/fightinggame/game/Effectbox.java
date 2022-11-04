package fightinggame.game;

import java.util.ArrayList;

public class Effectbox {
    private WorldEntity owner;
    private Point center;
    private boolean isTraversable;
    private double posX; //Position is at the left upper corner.
    private double posY;
    private int width;
    private int height;

    public Effectbox(WorldEntity owner, Point center, boolean isTraversable, ArrayList<Integer> hitboxProperties) {
        this.owner = owner;
        this.center = center;
        this.isTraversable = isTraversable;
        this.width= hitboxProperties.get(0);
        this.height = hitboxProperties.get(1);
        posX = center.getX() - (width/2 - 10);
        posY = center.getY() - (height/2);

    }

    public void updatePos() {
        posX = center.getX() - (width/2 - 10);
        posY = center.getY() - (height/2);
    }

    public boolean worldEntityInEffectArea(WorldEntity entity) {
        // TODO:
        return false;
    }

    public void setIsTraversable(boolean state){
        this.isTraversable = state;
    }
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    private boolean pointInArea(Point p){
        // TODO:
        return false;
    }
    
    private boolean EffectBoxInEffectBox(Effectbox otherBox){
        // TODO:
        return false;
    }

    private void expandEffectBox(int length, int height) {
    }

}
