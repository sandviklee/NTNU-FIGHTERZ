package fightinggame.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;

public class VectorTest {
    private int validVx;
    private int validVy;
    private int validAx;
    private int validAy;
    private int leftDirection;
    private int rightDirection;

    private int nonValidVx;
    private int nonValidVy;
    private int nonValidAx;
    private int nonValidAy;

    private Vector leftVector;
    private Vector rightVector;
    private Vector standardVec;


    public void setup() {
        validVx = 10;
        validVy = 10;
        validAx = 10;
        validAy = 10;
        leftDirection = -1;
        rightDirection = 1;

        leftVector = new Vector(validVx, validVy, validAx, validAy, leftDirection);
        rightVector = new Vector(validVx, validVy, validAx, validAy, rightDirection);
        standardVec = new Vector();
    }
    @Test
    @DisplayName("Check if the Constructor works ")        
    public void testConstructor() {
        // clean test for Constructor
        
        try {
            leftVector = new Vector(validVx, validVy, validAx, validAy, leftDirection);
            rightVector = new Vector(validVx, validVy, validAx, validAy, rightDirection);
            standardVec = new Vector();
        }
        catch(Exception e) {
            System.out.println(e.getLocalizedMessage());
            fail();
        }
        // check values for leftVector and rightVector
        assertEquals(-validVx, leftVector.getVx() , "did not set correct cordinate");
        assertEquals(-validVy, leftVector.getVy() , "did not set correct cordinate");
        assertEquals(-validAx, leftVector.getAx() , "did not set correct cordinate");
        assertEquals(-validAy, leftVector.getAy() , "did not set correct cordinate");

        assertEquals(validVx, rightVector.getVx() , "did not set correct cordinate");
        assertEquals(validVy, rightVector.getVy() , "did not set correct cordinate");
        assertEquals(validAx, rightVector.getAx() , "did not set correct cordinate");
        assertEquals(validAy, rightVector.getAy() , "did not set correct cordinate");
        
        // empty constructor
        assertEquals(0, standardVec.getVx() , "did not set correct cordinate");
        assertEquals(0, standardVec.getVy() , "did not set correct cordinate");
        assertEquals(0, standardVec.getAx() , "did not set correct cordinate");
        assertEquals(0, standardVec.getAy() , "did not set correct cordinate");

        // dirty test for constructor
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Vector(nonValidVx, nonValidVy, nonValidAx, nonValidAy, leftDirection);
            new Vector(nonValidVx, nonValidVy, nonValidAx, nonValidAy, rightDirection);
		}, "This Vector is not possible");

    }
    
    @Test
    @DisplayName("check that the vector adds the new vector to values")
    public void testAddVector() {
        for (int i = 1; i < 100; i++) {
            standardVec.addVector(rightVector);

            assertEquals(i * validVx, standardVec.getVx() , "did not add correctly");
            assertEquals(i * validVy, standardVec.getVy() , "did not add correctly");
            assertEquals(i * validAx, standardVec.getAx() , "did not add correctly");
            assertEquals(i * validAy, standardVec.getAy() , "did not add correctly");
        }

        standardVec = Vector();
        for (int i = 1; i < 100; i++) {
            standardVec.addVector(leftVector);
            assertEquals(i * (-validVx), standardVec.getVx() , "did not add correctly");
            assertEquals(i * (-validVy), standardVec.getVy() , "did not add correctly");
            assertEquals(i * (-validAx), standardVec.getAx() , "did not add correctly");
            assertEquals(i * (-validAy), standardVec.getAy() , "did not add correctly");
        }
        standardVec = Vector();
        standardVec.addVector(leftVector);
        standardVec.addVector(rightVector);

        assertEquals(0, standardVec.getVx() , "did not add correctly");
        assertEquals(0, standardVec.getVy() , "did not add correctly");
        assertEquals(0, standardVec.getAx() , "did not add correctly");
        assertEquals(0, standardVec.getAy() , "did not add correctly");
    }

    @Test
    @DisplayName("check if applyAcceleration adjusts velocity correctly")
    public void testApplyAcceleration() {
        // Try to apply zero acceleration
        standardVec.applyAcceleration();
        assertEquals(0, standardVec.getVx() , "did not add correctly");
        assertEquals(0, standardVec.getVy() , "did not add correctly");
        assertEquals(0, standardVec.getAx() , "did not add correctly");
        assertEquals(0, standardVec.getAy() , "did not add correctly");

        rightVector.applyAcceleration();
        assertEquals(validVx + validAx, rightVector.getVx() , "did not add correctly");
        assertEquals(validVy + validAy, rightVector.getVy() , "did not add correctly");
        assertEquals(validAx, rightVector.getAx() , "did not add correctly");
        assertEquals(validAy, rightVector.getAy() , "did not add correctly");
    }
}
