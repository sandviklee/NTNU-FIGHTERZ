package fightinggame.game;

public class Effectbox {
    private WorldEntity owner;
    private Point center;
    private boolean isTraversable;
    private double posX; //Position is at the left upper corner.
    private double posY;
    private double offsetX;
    private double offsetY;
    private int width;
    private int height;

    public Effectbox(WorldEntity owner, Point center, boolean isTraversable, int width, int height) {
        this.owner = owner;
        this.center = center;
        this.isTraversable = isTraversable;
        this.width = width;
        this.height = height;
        posX = center.getX() - (width/2 - 10);
        posY = center.getY() - (height/2);
        offsetX = center.getX();
        offsetY = center.getY();

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

    public Point getPoint() {
        return center;
    }

    

    public double getOffsetX() {
        return offsetX;
    }

    public double getOffsetY() {
        return offsetY;
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
