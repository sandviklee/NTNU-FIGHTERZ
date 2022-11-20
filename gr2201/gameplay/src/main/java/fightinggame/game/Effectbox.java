package fightinggame.game;
/**
 * The {@code Effectbox} class represents a Hitbox or Hurtbox for a WorldEntity.
 */
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

    /**
     * Creates an Effectbox with the given attributes for a World Entity.
     * @param owner         WorldEntity
     * @param center        Point
     * @param isTraversable boolean
     * @param width         integer
     * @param height        integer
     */
    public Effectbox(WorldEntity owner, Point center, boolean isTraversable, int width, int height) {
        this.owner = owner;

        if (center == null){
            throw new IllegalArgumentException("Center point cannot be null");
        }
        this.center = center;

        if (width < 0 || height < 0){
            throw new IllegalArgumentException("Width or height cannot be negative");
        }

        this.width = width;
        this.height = height;
        this.isTraversable = isTraversable;
        posX = center.getX() - (width/2 - 10);
        posY = center.getY() - (height/2);
        offsetX = center.getX();
        offsetY = center.getY();
    }

    /**
     * Updates the position of the Effectbox. 
     * This works because the Point position is updated
     * and therefore the Effectbox also needs to be updated internally.
     */
    public void updatePos() {
        posX = center.getX() - (width/2 - 10);
        posY = center.getY() - (height/2);
    }

    /**
     * Checks if an EffectBox intersects with another Effectbox. 
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
            /*Had to configure the priority because if Top and Bottom was tested before left and right, you could just go through the terrain.
            This code is made so that if you are on top of a hitbox, you are still at the top, if your characters bottom coordinate
            is over (in programming case under) the top of the terrain hitbox you are still on top, even if its checked that 
            you are technically on the left or right of the terrain hitbox.
            The random +10 is there so that when the gravity takes the character a bit into the terrain hitbox, it still counts the bottom
            of the character hurtbox as "over" the terrain hitbox.
            */
            if (thisLeft <= otherLeft){
                if (thisBottom <= otherBoxTop + 20){
                    return "Top";
                } else {
                    return "Left";
                }
                
            }
            if (thisRight >= otherRight){
                if (thisBottom <= otherBoxTop + 20){
                    return "Top";
                } else {
                    return "Right";
                }
            }

            if (thisTop <= otherBoxTop){
                return "Top";
            }
            if (thisBottom >= otherBoxBottom){
                return "Bottom";
            }

            return "Contained";
        }
        return "Outside";
    }
    
    /**
     * Updates the center of the point.
     * @param center Point
     */
    public void updateCenter(Point center) {
        this.center = center;
    }

    public boolean getIsTraversable(){
        return this.isTraversable;
    }
    
    /**
     * Sets the Traversability of the Effectbox
     * @param isTraversable boolean
     */
    public void setIsTraversable(boolean state){
        this.isTraversable = state;
    }
    
    /**
     * Getter for width
     * @return width integer
     */
    public int getWidth() {
        return width;
    }

    /**
     * Getter for height
     * @return height integer
     */
    public int getHeight() {
        return height;
    }

    /**
     * Getter for X Position
     * @return posX double
     */
    public double getPosX() {
        return posX;
    }

    /**
     * Getter for Y Position
     * @return posY double
     */
    public double getPosY() {
        return posY;
    }

    /**
     * Getter for Point
     * @return center Point
     */
    public Point getPoint() {
        return center;
    }

    /**
     * Getter for X Offset
     * This is the offset the Effectbox stays at in relation with the WorldEntity position.
     * @return offsetX integer
     */
    public double getOffsetX() {
        return offsetX;
    }

    /**
     * Getter for Y Offset
     * This is the offset the Effectbox stays at in relation with the WorldEntity position.
     * @return offsetY integer
     */
    public double getOffsetY() {
        return offsetY;
    }

    /**
     * Getter for WorldEntity Owner
     * The Owner of this Effectbox
     * @return owner Effectbox
     */
    public WorldEntity getOwner() {
        return owner;
    }
}
