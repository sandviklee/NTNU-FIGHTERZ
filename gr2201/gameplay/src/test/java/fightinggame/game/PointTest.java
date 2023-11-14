package fightinggame.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class PointTest {
    
    private Point origoPoint;
    private Point positivePoint;

    private double x;
    private double y;

    private Vector actionVector1;
    private Vector actionVector2;
    private Vector gravityVector;

    private double Vx;
    private double Vy;
    private double Ax;
    private double Ay;
    private int direction;

    @BeforeEach
    public void setup() {
        x = 10;
        y = 10;
        Vx = 15;
        Vy = 15;
        Ax = 5;
        Ay = 5;
        direction = 1;
        gravityVector = new Vector(Vx, Vy, Ax, Ay, direction);

        origoPoint = new Point(0, 0);
        positivePoint = new Point(x, y);

        actionVector1 = new Vector(Vx, Vy, Ax, Ay, direction);
        actionVector2 = new Vector(Vx, Vy, Ax, Ay, direction);

    }


    @Test
    @DisplayName("Test if the constructor creates correct object with correct fields")
    public void testConstructor() {
        try {
            origoPoint = new Point(0, 0);
            positivePoint = new Point(x, y);    
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            fail();
        }
        assertEquals(0, origoPoint.getX(), " did not set correct values to Point");
        assertEquals(0, origoPoint.getY(), " did not set correct values to Point");

        assertEquals(x, positivePoint.getX(), " did not set correct values to Point");
        assertEquals(y, positivePoint.getY(), " did not set correct values to Point");
    }

    @Test
    @DisplayName("test if the setters work")
    public void testSetters() {

        positivePoint.setX(x);
        positivePoint.setY(y);

        assertEquals(x, positivePoint.getX(), "The X value is not correct");
        assertEquals(y, positivePoint.getY(), "The Y value is not correct");

        positivePoint.setX(x * x);
        positivePoint.setY(y * y);

        assertEquals(x * x, positivePoint.getX(), "The X value is not correct");
        assertEquals(y * y, positivePoint.getY(), "The Y value is not correct");
    }



}
