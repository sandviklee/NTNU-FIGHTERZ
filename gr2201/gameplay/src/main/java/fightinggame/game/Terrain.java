package fightinggame.game;

import java.util.ArrayList;

public class Terrain extends WorldEntity{
    private Effectbox hitBox;

    public Terrain(String name, ArrayList<Integer> pos, ArrayList<Integer> hitBoxProperties) {
        super(name, pos);
        this.hitBox = new Effectbox(this, getPoint(), false, hitBoxProperties);
    }

    public Effectbox getHitBox() {
        return hitBox;
    }
    
    public boolean hitboxCollision(Effectbox effectbox) {
        //NB: SJEKKER BARE FOR UNDER Y AKKURAT NÅ
        if ((effectbox.getPosY() + effectbox.getHeight() + 1) > (hitBox.getPosY())) {
            if ((hitBox.getPosX() < (effectbox.getPosX() + effectbox.getWidth())) && 
            ((hitBox.getPosX() + hitBox.getWidth()) > (effectbox.getPosX()))) {
                return true;
            }
        }
        return false;
    }
    
    
}
