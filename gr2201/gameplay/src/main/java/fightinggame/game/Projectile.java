package fightinggame.game;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * The {@code Projectile} class represents all projectile World entities.
 */
public class Projectile extends WorldEntity {
    private HashMap<Integer, ActionProperties> actionHash = new HashMap<>();
    private ActionProperties property;
    private Effectbox hitBox;
    private Vector mainVector;
    private int facingDirection;
    /**
     * Creates a projectile with the give parameters. 
     * A projectile is a WorldEntity that like Terrain is not playable,
     * but is movable like a game character.
     * @param name      declares the name
     * @param pos       declares the starting position
     * @param knockback declares the vector of this projectile
     * @param hitbox    declares the hitbox
     * @param damage    declares the damage
     */
    public Projectile(String name, ArrayList<Integer> pos, Vector knockback, Effectbox hitbox, int damage) {
        super(name, pos);
        int hitBoxWidth = (int) hitbox.getWidth();
        int hitBoxHeight = (int) hitbox.getHeight();
        this.hitBox = new Effectbox(this, super.point, false, hitBoxWidth, hitBoxHeight);
        this.mainVector = new Vector(knockback);
        /*
         * Here we define the one action a projectile has to have. In this case for our project
         * the only projectile in play is Angry Cyclist wheel. Therefore the only action a projectile needs
         * is to be able to move accordingly to its knockback, and have damage and a hitbox.
         * 
         * If we wanted to implement other projectiles, like Jonathan Trumpists metronome (see documentation),
         * we would have to add more actions to projectiles.
         */
        actionHash.put(0, new ActionProperties("Projectile", 30, 2, true, 0, mainVector, hitBox, damage));
    } 
    /**
     * Sets the current action for projectile
     * @param actionNumber integer
     */
    public void setCurrentAction(Integer actionNumber) {
        if (actionNumber != null) {
            property = actionHash.get(actionNumber);
            this.currentAction = new Action(property);     
        } 
    }
    /**
     * Performs the current action, and increments it. 
     */
    public void doAction() {
        if (currentAction.getKnockback().getVx() > 0) {
            facingDirection = 1;
        } else {
            facingDirection = -1;
        }
        if (currentAction != null && !currentAction.getIsDone()) {
            point.setX(point.getX() + currentAction.getKnockback().getVx());
            point.setY(point.getY() + currentAction.getKnockback().getVy());
            hitBox.updatePos();
            currentAction.nextActionFrame();
        }
    }
    /**
     * Getter for hitbox
     * @return hitBox Effectbox
     */
    public Effectbox getHitBox() {
        return hitBox;
    }
    /**
     * Getter for facing direction
     * @return facingDirection integer
     */
    public int getFacingDirection() {
        return facingDirection;
    }
}
