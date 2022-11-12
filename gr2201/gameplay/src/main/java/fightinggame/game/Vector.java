package fightinggame.game;

public class Vector {
    private double vx;
    private double vy;
    private double ax;
    private double ay;
    private int direction;

    public double getVx(){
        return this.vx;
    }

    public double getVy(){
        return this.vy;
    }

    /**
     * Constructors for a vector in a specific directions. Will throw an exeption if velocity if not 
     * @param vx
     * @param vy
     * @param ax
     * @param ay
     * @param direction
     * @throws IllegalArgumentException
     */
    public Vector(double vx, double vy, double ax, double ay, int direction) throws IllegalArgumentException{
        if (vx > 0 && ax > 0) {
            if (vx%ax != 0) {
                throw new IllegalArgumentException("Acceleration has to be a factor of the velocity");
            }
            
        } else if (vy > 0 && ay > 0) {
            if (vy%ay != 0) {
                throw new IllegalArgumentException("Acceleration has to be a factor of the velocity");
            }
            
        }

        this.direction = direction;
        this.vx = vx*direction;
        this.vy = vy*direction;
        this.ax = ax*direction;
        this.ay = ay*direction;
    }

    public Vector(double vx, double vy, double ax, double ay) throws IllegalArgumentException{
        if (vx > 0 && ax > 0) {
            if (vx%ax != 0) {
                throw new IllegalArgumentException("Acceleration has to be a factor of the velocity");
            }
            
        } else if (vy > 0 && ay > 0) {
            if (vy%ay != 0) {
                throw new IllegalArgumentException("Acceleration has to be a factor of the velocity");
            }
            
        }

        this.vx = vx;
        this.vy = vy;
        this.ax = ax;
        this.ay = ay;
    }

    public Vector(){
        this.ax = 0;
        this.ay = 0;
        this.vx = 0;
        this.vy = 0;
    }

    public void addVector(Vector vector){
        this.vx += vector.vx;
        this.vy += vector.vy;
        this.ax += vector.ax;
        this.ay += vector.ay;
    }
    
    public void removeVector(Vector vector){
        this.vx -= vector.vx;
        this.vy -= vector.vy;
        this.ax -= vector.ax;
        this.ay -= vector.ay;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public int getDirection() {
        return direction;
    }
    //This method also resets all the parameters to strictly follow the direction of the move.
    public void setDirection(int direction) {
        this.direction = direction;
        this.vx = Math.abs(this.vx)* direction;
        this.vy = Math.abs(this.vy)* direction;
        this.ax = Math.abs(this.ax)* direction;
        this.ay = Math.abs(this.ay)* direction;
    }

    public void applyAcceleration(){
        this.vx += this.ax;
        if (this.vx == 0){
            this.ax = 0;
        }

        this.vy += this.ay;
        if (this.vy == 0){
            this.ay = 0;
        }
    }
}
