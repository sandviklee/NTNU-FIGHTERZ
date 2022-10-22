package fightinggame.game;

import java.util.ArrayList;

public class GameCharacter extends WorldEntity{
    public GameCharacter(String name, ArrayList<Integer> pos) {
        super(name, pos);
    }
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
