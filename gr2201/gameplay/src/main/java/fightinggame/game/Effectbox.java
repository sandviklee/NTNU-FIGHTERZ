package fightinggame.game;

public class Effectbox {
    private WorldEntity owner;
    private Point center;
    private boolean isTraversable;
    private int length;
    private int height;

    public Effectbox(WorldEntity owner, Point center, boolean isTraversable) {

    }

    public boolean worldEntityInEffectArea(WorldEntity entity) {
        // TODO:
        return false;
    }

    public void setIsTraversable(boolean state){
        // TODO:
    }

    private boolean pointInArea(Point p){
        // TODO:
        return false;
    }
    
    private boolean EffectBoxInEffectBox(Effectbox otherBox){
        // TODO:
        return false;
    }
    private void setPosition(Point p){
        // TODO:
    }
    private void changePosition(Vector v){
        // TODO:
    }
    private void move(){
        // TODO:
    }
    private void expandEffectBox(int length, int height) {

    }

}
