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

        if (center == null){
            throw new IllegalArgumentException("Center point cannot be null");
        }
        this.center = center;
        this.isTraversable = isTraversable;

        if (width <= 0 || height <= 0){
            throw new IllegalArgumentException("Width or height cannot be negative");
        }
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

    public void updateCenter(Point center) {
        this.center = center;
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

    public WorldEntity getOwner() {
        return owner;
    }


}
