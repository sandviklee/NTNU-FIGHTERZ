package fightinggame.game;
/**
 * The {@code Point} class holds an universal position for any object, 
 * for example WorldEntity or Effectbox.
 */
public class Point {
    private double x;
    private double y;
    /**
     * Makes a point for a WorldEntity or Effectbox.
     * The point holds the objects position.
     * @param x asserts the x position
     * @param y asserts the y position
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Makes a point with 0 as positional values.
     * Used when the position of the point is meaningless.
     */
    public Point() {
        this.x = 0;
        this.y = 0;
    }
    /**
     * Getter for X position
     * @return x double
     */
    public double getX() {
        return x;
    }
    /**
     * Getter for Y position
     * @return y double
     */
    public double getY() {
        return y;
    }
    /**
     * Setter for X position
     * @param x double
     */
    public void setX(double x) {
        this.x = x;
    }
    /**
     * Setter for Y position
     * @param y double
     */
    public void setY(double y) {
        this.y = y;
    }
}
