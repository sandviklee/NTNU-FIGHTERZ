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
        origoPoint.setGravityVector(gravityVector);
        positivePoint.setGravityVector(gravityVector);

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
    @DisplayName("Test if moves point correctly")
    public void testMoveWithoutActionVector() {

        // if there are no actionVector point shall only be affected by gravity
        for (int i = 1; i < 100; i++) {
            origoPoint.move();
            positivePoint.move();

            assertEquals(0 + i * (gravityVector.getVx()), origoPoint.getX());
            assertEquals(0 + i * (gravityVector.getVy()), origoPoint.getY());

            assertEquals(x + i * (gravityVector.getVx()), positivePoint.getX());
            assertEquals(y + i * (gravityVector.getVy()), positivePoint.getY());
        }
        

        // check if the speed accelerate with vectors given acceleration.

        for (int i = 1; i < 100; i++) {
            origoPoint.move();
            positivePoint.move();

            assertEquals(0 + i * (gravityVector.getVx()), origoPoint.getX());
            assertEquals(0 + i * (gravityVector.getVy()), origoPoint.getY());

            assertEquals(x + i * (gravityVector.getVx()), positivePoint.getX());
            assertEquals(y + i * (gravityVector.getVy()), positivePoint.getY());
        }
        
    }
    @Test
    @DisplayName("Test if moves point correctly")
    public void testMoveWithActionVector() {
        positivePoint.addActionVector(actionVector1);

        positivePoint.move();
        assertEquals(x + (gravityVector.getVx() + actionVector1.getVx()), positivePoint.getX());
        assertEquals(y + (gravityVector.getVy() + actionVector1.getVy()), positivePoint.getY());
        

        // check if the speed accelerate with vectors given acceleration.
        double currentXChange = x + (gravityVector.getVx() + actionVector1.getVx());
        double currentYChange = y + (gravityVector.getVy() + actionVector1.getVy());
        
        positivePoint.move();
        assertEquals(2 * currentXChange + Ax, positivePoint.getX(), "the acceleration was not added after move to point cordinate, point does not mainpulate actionVector correctly");
        assertEquals(2 * currentYChange + Ay, positivePoint.getY(), "the acceleration was not added after move to point cordinate, point does not mainpulate actionVector correctly");
    }

    @Test
    public void testAddActionVector() {
        // check if it adds to the vector
        double changeX = gravityVector.getVx() + actionVector1.getVx() + actionVector2.getVx();
        double changeY = gravityVector.getVy() + actionVector1.getVy() + actionVector2.getVy();

        origoPoint.setActionVector(actionVector1);
        origoPoint.addActionVector(actionVector2);

        origoPoint.move();
        assertEquals(changeX, origoPoint.getX(), " does not add new vector to the old vector");
        assertEquals(changeY, origoPoint.getY(), " does not add new vector to the old vector");
    }

    @Test
    @DisplayName("Check if SetActionVector sets vector correctly")
    public void testSetActionVector() {
        origoPoint.setActionVector(actionVector1);
        origoPoint.move();
        assertEquals(gravityVector.getVx() + actionVector1.getVx(), origoPoint.getX(), " does not set new vector as actionVector vector");
        assertEquals(gravityVector.getVy() + actionVector1.getVy(), origoPoint.getY(), " does not set new vector as actionVector vector");

        double currentX = gravityVector.getVx() + actionVector1.getVx();
        double currentY = gravityVector.getVy() + actionVector1.getVy();

        origoPoint.setActionVector(actionVector2);
        origoPoint.move();
        assertEquals(currentX + gravityVector.getVx() + actionVector2.getVx(), origoPoint.getX(), " does not set new vector as actionVector vector");
        assertEquals(currentY + gravityVector.getVy() + actionVector2.getVy(), origoPoint.getY(), " does not set new vector as actionVector vector");
    }

    @Test
    @DisplayName("Check if SetGravityVector sets vector correctly")
    public void testSetGravityVector() {
        origoPoint.setGravityVector(gravityVector);
        origoPoint.move();
        assertEquals(gravityVector.getVx(), origoPoint.getX(), " does not set new vector as actionVector vector");
        assertEquals(gravityVector.getVy(), origoPoint.getY(), " does not set new vector as actionVector vector");

        double currentX = gravityVector.getVx();
        double currentY = gravityVector.getVy();

        origoPoint.setGravityVector(gravityVector);
        origoPoint.move();
        assertEquals(currentX + gravityVector.getVx(), origoPoint.getX(), " does not set new vector as actionVector vector");
        assertEquals(currentY + gravityVector.getVy(), origoPoint.getY(), " does not set new vector as actionVector vector");
    }
}