package fightinggame.game;

public class GameCharacter extends WorldEntity{
    private int weight;
    private int health;

    public boolean canMove(){
        return true;
    }

    public void heal(int healing){
        this.health -= healing;
    }

    public void hurt(int damage){
        this.health += damage;
    }
}
