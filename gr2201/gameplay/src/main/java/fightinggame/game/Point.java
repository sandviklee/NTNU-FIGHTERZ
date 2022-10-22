package fightinggame.game;

public class Point {
    private Vector actionVector = new Vector();
    private Vector gravityVector = new Vector();
    private double x;
    private double y;

    
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

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
