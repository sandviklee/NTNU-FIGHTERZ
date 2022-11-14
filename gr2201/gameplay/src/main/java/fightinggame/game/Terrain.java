package fightinggame.game;

import java.util.ArrayList;

public class Terrain extends WorldEntity{
    private Effectbox hitBox;

    public Terrain(String name, ArrayList<Integer> pos, int width, int height) {
        super(name, pos);
        this.hitBox = new Effectbox(this, getPoint(), false, width, height);
    }

    public Effectbox getHitBox() {
        return hitBox;
    }
    
    
}
