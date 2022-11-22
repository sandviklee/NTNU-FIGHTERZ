package fightinggame.game;

import java.util.ArrayList;
/**
 * The {@code Terrain} class represents terrain World entities.
 */
public class Terrain extends WorldEntity{
    private Effectbox hitBox;
    /**
     * Creates a Terrain with the given paramenters.
     * Terrain is a WorldEntity made to stop game characters from
     * moving in a certain direction
     * @param name   declares the name
     * @param pos    declares the starting position
     * @param width  declares the width
     * @param height declares the height
     */
    public Terrain(String name, ArrayList<Integer> pos, int width, int height) {
        super(name, pos);
        this.hitBox = new Effectbox(this, point, false, width, height);
    }
    /**
     * Getter for hitbox
     * Used to check if any game character is touching the terrain.
     * @return hitBox Effectbox
     */
    public Effectbox getHitBox() {
        return hitBox;
    }
}
