package fightinggame.game;
/**
 * The {@code Vector} class represents a mathematical vector.
 * Vectors in this game are used to represent both velocity and 
 * acceleration in both X and Y directions.
 */
public class Vector {
    private double vx;
    private double vy;
    private double ax;
    private double ay;
    private int direction;
    /**
     * Creates a Vector with a direction
     * @param vx         asserts the x velocity
     * @param vy         asserts the y velocity
     * @param ax         asserts the x acceleration
     * @param ay         asserts the y acceleration
     * @param direction  asserts the direction of the vector, 1: right; -1:left
     * @throws IllegalArgumentException if the x acceleration is not a factor in the velocity, and likewise for y
     */
    public Vector(double vx, double vy, double ax, double ay, int direction) throws IllegalArgumentException {
        if (vx > 0 && ax > 0) {
            if (vx % ax != 0) {
                throw new IllegalArgumentException("Acceleration has to be a factor of the velocity");
            }
        } else if (vy > 0 && ay > 0) {
            if (vy % ay != 0) {
                throw new IllegalArgumentException("Acceleration has to be a factor of the velocity");
            }
        }
        this.direction = direction;
        this.vx = vx * direction;
        this.vy = vy * direction;
        this.ax = ax * direction;
        this.ay = ay * direction;
    }
    /**
     * Creates a Vector without a starting direction
     * @param vx         asserts the x velocity
     * @param vy         asserts the y velocity
     * @param ax         asserts the x acceleration
     * @param ay         asserts the y acceleration
     * @throws IllegalArgumentException if the x acceleration is not a factor in the velocity, and likewise for y
     */
    public Vector(double vx, double vy, double ax, double ay) throws IllegalArgumentException {
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
    /**
     * Creates a copy of a {@code Vector}
     * @param vector the {@code Vector} to copy
     * @throws IllegalArgumentException if the x acceleration is not a factor in the velocity, and likewise for y
     */
    public Vector(Vector vector) throws IllegalArgumentException {
        if (vector.getVx() > 0 && vector.getAx() > 0) {
            if (vector.getVx() % vector.getAx() != 0) {
                throw new IllegalArgumentException("Acceleration has to be a factor of the velocity");
            }
        } else if (vector.getVy() > 0 && vector.getAy() > 0) {
            if (vector.getVy() % vector.getAy() != 0) {
                throw new IllegalArgumentException("Acceleration has to be a factor of the velocity");
            }
        }
        this.vx = vector.getVx();
        this.vy = vector.getVy();
        this.ax = vector.getAx();
        this.ay = vector.getAy();
    }    
    /**
     * Creates a nullvector
     */
    public Vector() {
        this.ax = 0;
        this.ay = 0;
        this.vx = 0;
        this.vy = 0;
    }
    /**
     * Adds the {@code Vector} to this Vector
     * @param vector Vector
     */
    public void addVector(Vector vector) {
        this.vx += vector.vx;
        this.vy += vector.vy;
        this.ax += vector.ax;
        this.ay += vector.ay;
    }
    /**
     * Subtracs the {@code Vector} to this Vector
     * @param vector Vector
     */
    public void removeVector(Vector vector) {
        this.vx -= vector.vx;
        this.vy -= vector.vy;
        this.ax -= vector.ax;
        this.ay -= vector.ay;
    }
    /**
     * Setter for X velocity
     * @param vx double
     */
    public void setVx(double vx) {
        this.vx = vx;
    }
    /**
     * Setter for Y velocity
     * @param vy double
     */
    public void setVy(double vy) {
        this.vy = vy;
    }
    /**
     * Setter for X acceleration
     * @param ax double
     */
    public void setAx(double ax) {
        this.ax = ax;
    }
    /**
     * Setter for Y acceleration
     * @param ay double
     */
    public void setAy(double ay) {
        this.ay = ay;
    }
    /**
     * Getter for X velocity
     * @return vx double
     */
    public double getVx() {
        return this.vx;
    }
    /**
     * Getter for Y velocity
     * @return vy double
     */
    public double getVy() {
        return this.vy;
    }
    /**
     * Getter for X acceleration
     * @return ax double
     */
    public double getAx() {
        return this.ax;
    }
    /**
     * Getter for Y acceleration
     * @return ax double
     */
    public double getAy() {
        return this.ay;
    }
    /**
     * Getter for direction
     * @return direction integer
     */
    public int getDirection() {
        return direction;
    }
    /**
     * Setter for the direction of the vector.
     * This method also resets all the parameters to strictly follow the direction of the move. 
     * @param direction asserts the direction of this vector
     */
    public void setDirection(int direction) {
        this.direction = direction;
        this.vx = Math.abs(this.vx) * direction;
        this.vy = Math.abs(this.vy) * direction;
        this.ax = Math.abs(this.ax) * direction;
        this.ay = Math.abs(this.ay) * direction;
    }
    /**
     * Applies the acceleration
     */
    public void applyAcceleration(){
        this.vx += this.ax;
        /*
         * We use Math.round here because we got problems with float numbers
         * at the 10th or something decimal, and made it so the acceleration
         * was'nt a factor of the velocity anymore.
         */
        this.vx = Math.round(this.vx * 10000d) / 10000d;
        if (this.vx == 0){
            this.ax = 0;
        }
        this.vy += this.ay;
        this.vy = Math.round(this.vy * 10000d) / 10000d;
        if (this.vy == 0){
            this.ay = 0;
        }
    }
}
