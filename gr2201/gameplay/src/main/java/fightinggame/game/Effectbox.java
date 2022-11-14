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


    /**
     * Checks if an EffectBox intersects with an other Effectbox. 
     * @param otherBox
     * @return Returns a string describing if the Effectboxes intercect and if so how. Will return the side which is not contained, prioritizing top and bottom 
     * over left and right. If the Effectbox is fully contained it will return "Contained"
     */
    public String EffectBoxInEffectBox(Effectbox otherBox) {
        //Variables for the x and y distances of the two Effectboxes and the required distances for them to intersect
        double dx = Math.abs(this.center.getX() - otherBox.center.getX());
        double dy = Math.abs(this.center.getY() - otherBox.center.getY());
        double halfHeightSum = this.getHeight() / 2 + otherBox.getHeight()/2;
        double halfWidthSum = this.getWidth() / 2 + otherBox.getWidth()/2;

        //Variables for the y coordinates of the tops and bottoms of the EffectBoxes
        double thisTop = this.center.getY() - this.getHeight()/2;
        double otherBoxTop = otherBox.center.getY() - otherBox.getHeight()/2;
        double thisBottom = this.center.getY() + this.getHeight()/2;
        double otherBoxBottom = otherBox.center.getY() + otherBox.getHeight()/2;

        //Variables for the x coordinates of the left and right sides of the Effectboxes
        double thisRight = this.center.getX() + this.getWidth()/2;
        double otherRight = otherBox.center.getX() + otherBox.getWidth()/2;
        double thisLeft = this.center.getX() - this.getWidth()/2;
        double otherLeft = otherBox.center.getX() - otherBox.getWidth()/2;
        
        //Top means that this effectbox is on top of the other.
        if (dx <= halfWidthSum && dy <= halfHeightSum){
            if (thisTop <= otherBoxTop){
                return "Top";
            }
            if (thisBottom >= otherBoxBottom){
                return "Bottom";
            }
            if (thisLeft <= otherLeft){
                return "Left";
            }
            if (thisRight >= otherRight){
                return "Right";
            }

            return "Contained";
        }
        return "Outside";
    }

    private void expandEffectBox(int length, int height) {
    }

    public void updateCenter(Point center) {
        this.center = center;
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


}
