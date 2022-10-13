package fightinggame.game;

public class Point {
    private Vector actionVector = new Vector();
    private Vector gravityVector = new Vector();
    private double x = 0;
    private double y = 0;

    public void move(){
        this.x += actionVector.getVx();
        this.y += actionVector.getVy();
        this.x += gravityVector.getVx();
        this.y += gravityVector.getVy();
    }

    public void setActionVector(Vector vector){
        this.actionVector = vector;
    }

    public void setGravityVector(Vector vector){
        this.gravityVector = vector;
    }

    public void addActionVector(Vector vector){
        this.actionVector.addVector(vector);
    }
}
