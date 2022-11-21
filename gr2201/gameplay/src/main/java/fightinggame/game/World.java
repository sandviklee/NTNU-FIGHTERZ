package fightinggame.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
/**
 * The {@code World} class represents the a World for the WorldEntities.
 */
public class World {
    private int[] screensize = {1920, 1080};
    private int idle = 0;
    private int hitStun = 1;
    private ArrayList<WorldEntity> worldEntities;
    private ArrayList<GameCharacter> gameCharacters = new ArrayList<>();
    private ArrayList<String> keysHeld = new ArrayList<>();
    private ArrayList<String> Sides = new ArrayList<>(Arrays.asList("Top", "Bottom", "Left", "Right"));
    private ArrayList<Projectile> finishedProjectiles;
    /*
     * The reason for all these Hashmaps is to differentiate different characters or projectiles.
     * In hindsight this could have been done in a different class. 
     */
    private HashMap<GameCharacter, HashMap<String, Boolean>> CollisionHash = new HashMap<>();
    private HashMap<GameCharacter, Boolean> clickActionHash = new HashMap<>();
    private HashMap<GameCharacter, ArrayList<String>> heldKeyHash = new HashMap<>();
    private HashMap<GameCharacter, ArrayList<String>> inputPerEntity = new HashMap<>();
    private HashMap<GameCharacter, Projectile> projectileReady = new HashMap<>();
    private HashMap<GameCharacter, Boolean> spawnProjectileHash = new HashMap<>();
    private String NewHeldKey = "Idle";
    private Effectbox worldBox;
    private boolean gameOver = false;
    private int playerNumbWinner;
    /**
     * Creates a World with the gived world entities.
     * @param worldEntities asserts all the world entities that should load in with the world.
     */
    public World(ArrayList<WorldEntity> worldEntities){
        this.worldEntities = new ArrayList<>(worldEntities);
        /*
         * Here we introduce the worldbox. This is the "World hitbox", that holds all the characters inside it. If the character
         * is outside of the box, the character dies.
         * The size of the worldBox size is given by the screensize (1920 * 1080) and adding an offset.
         */
        int worldBoxSizeOffset = 1000;
        this.worldBox = new Effectbox(null, new Point(screensize[0]/2, screensize[1]/2), false, screensize[0] + worldBoxSizeOffset, screensize[1] + worldBoxSizeOffset);
        /*
         * Here all the worldEntities gets loaded in.
         * Especially gamecharacters have alot of logic,
         * where they need to initialize all the Hashmaps with a key and value.
         */
        for (WorldEntity entity : worldEntities) {
            if(entity instanceof GameCharacter){
                /*
                 * The Collidehash is just every side the character should be able to interact with terrain or any other
                 * hitbox or hurtbox.
                 */
                HashMap<String, Boolean> CollideSideHash = new HashMap<>();
                ArrayList<String> heldKeysList = new ArrayList<>(Arrays.asList("Idle", "Idle"));
                for (String side : Sides) {
                    CollideSideHash.put(side, false);
                }
                CollisionHash.put((GameCharacter) entity, CollideSideHash);
                clickActionHash.put((GameCharacter) entity, false);
                spawnProjectileHash.put((GameCharacter) entity, false);
                heldKeyHash.put((GameCharacter) entity, heldKeysList);
                projectileReady.put((GameCharacter) entity, null);
                inputPerEntity.put((GameCharacter) entity, new ArrayList<>());
                gameCharacters.add((GameCharacter) entity);
            }
        }
    }
    /**
     * Updates the world with the current keyInput clicked and keyInput released
     * Using both input and inputR gives us the possibility to make hold movement.
     * @param input asserts the input key from raw keyboard inputs
     * @param inputR asserts the input released key
     */
    public void updateWorld(String input, String inputR){
        /**
         * Update world goes through the checks like this:
         * 1) Handle all the collisions, 2) set all the actions, 3) apply the actions.
         * This is because if a character is hit by another action, it should be 
         recognized before an action can be set. The same with terrain collision.
         */
        handleCollisions();
        setActions(input, inputR);
        applyActions();
    }
    /**
     * Getter for World Entities
     * @return worldEntities ArrayList<WorldEntity>
     */
    public ArrayList<WorldEntity> getWorldEntities() {
        return worldEntities;
    }
    /**
     * Getter for gameOver state
     * @return gameOver boolean
     */
    public boolean getGameOver() {
        return gameOver;
    }
    /**
     * Getter for player winner
     * @return playerNumbWinner integer
     */
    public int getGameWinner() {
        return playerNumbWinner;
    }
    /**
     * Handles all the collisions with the help of Effectboxes.
     */
    private void handleCollisions(){
        /*
         * To check for collisions we first iterate through all the world entities in the world.
         * Then we check for different states, whether it the character hurtbox collides with Terrain,
         or something else. 
         */
        for (WorldEntity entity1 : worldEntities) {
            if (entity1 instanceof GameCharacter) {
                /*
                 * To check if a character is outside of the worldBox, the effectboxineffectbox method
                 gives us "Outside".
                 * If this is true, then the character should reset it's position and iterate the deathcounter.
                 */
                if (entity1.getHurtBox().EffectBoxInEffectBox(worldBox).equals("Outside")) {
                    characterReset((GameCharacter) entity1);
                }
            }  
            /*
             * For every tick we have to reset if the player is colliding or not. 
             * This is because of the logic that comes after this. 
             */
            for (String side : Sides) {
                if (entity1 instanceof GameCharacter) {
                    CollisionHash.get((GameCharacter) entity1).put(side, false);
                }
            }
            for (WorldEntity entity2 : worldEntities) {
                /*
                 * If the character hurtbox is colliding with the terrain hitbox, it should stop the character.
                 */
                if (entity1 instanceof GameCharacter && entity2 instanceof Terrain) {
                    String currentSide = entity1.getHurtBox().EffectBoxInEffectBox(entity2.getHitBox());
                    /*
                     * The Sides have been defined as:
                     * Top, Bottom, Left and Right.
                     */
                    if (Sides.contains(currentSide)) {
                        /*
                         * Sets the side in the collisionhash to true, if the character is touching
                         * sides of the terrain.
                         */
                        CollisionHash.get((GameCharacter) entity1).put(currentSide, true);
                    }
                    /*
                     * SwitchCase to set the current colliding side and send it to the character.
                     */
                    switch (currentSide) {
                        case "Top":
                        entity1.setOnGround(CollisionHash.get((GameCharacter) entity1).get(currentSide));
                        break;
                        case "Bottom":
                        entity1.setOnTop(CollisionHash.get((GameCharacter) entity1).get(currentSide));
                        break;
                        case "Left":
                        entity1.setOnLeft(CollisionHash.get((GameCharacter) entity1).get(currentSide));
                        break;
                        case "Right":
                        entity1.setOnRight(CollisionHash.get((GameCharacter) entity1).get(currentSide));
                        break;
                        /*
                         * If the character is Outside, the characters sides should alle be what they are before.
                         * In this case because we reset them earlier, these would most likely all be false.
                         */
                        case "Outside":
                        entity1.setOnGround(CollisionHash.get((GameCharacter) entity1).get("Top"));
                        entity1.setOnTop(CollisionHash.get((GameCharacter) entity1).get("Bottom"));
                        entity1.setOnRight(CollisionHash.get((GameCharacter) entity1).get("Right"));
                        entity1.setOnLeft(CollisionHash.get((GameCharacter) entity1).get("Left"));
                        break;
                    } 
                } 
                /*
                 * Check for if a projectile is colliding with a character.
                 */
                if (entity1 instanceof Projectile && entity2 instanceof GameCharacter) {
                    if (entity1 != entity2 && entity1.getHitBox() != null) {
                        String currentSide;
                        currentSide = entity1.getHitBox().EffectBoxInEffectBox(entity2.getHurtBox());
                        if (Sides.contains(currentSide) || currentSide.equals("Contained")) {
                            /*
                             * Here we get the first action in the character actionhash.
                             * This is because the first action is the hitstun action.
                             */
                            if (entity2.getCurrentAction().tryEnemyInterrupt(entity2.getAction(1))) {
                                setHitStun(entity1, entity2);
                            }
                        }
                    }
                }
                /*
                 * Check if a character is colliding with a character in some way.
                 */
                if (entity1 instanceof GameCharacter && entity2 instanceof GameCharacter) {
                    if (entity1 != entity2 && entity1.getCurrentAction().getHitBox() != null) {
                        String currentSide;
                        currentSide = entity1.getCurrentAction().getHitBox().EffectBoxInEffectBox(entity2.getHurtBox());
                        if (Sides.contains(currentSide) || currentSide.equals("Contained")) {
                            /*
                             * Here we get the first action in the character actionhash.
                             * This is because the first action is the hitstun action.
                             */
                            if (entity2.getCurrentAction().tryEnemyInterrupt(entity2.getAction(1))) {
                                setHitStun(entity1, entity2);
                            }
                        }
                    }
                }
            }
        }
    }
    /**
     * Sets the current action to the character with input and inputR.
     * Uses the keyinputs to decide which character to move, and applies the action to the correct gameCharacter.
     * Also spawns a projectile and adds it to the worldEntities arraylist.
     */
    private void setActions(String input, String inputR){
        String[] inputArray = input.split("");
        ArrayList<String> keyInputArray =  new ArrayList<>(Arrays.asList(inputArray));
        String[] inputRArray = inputR.split("");
        ArrayList<String> keyInputRArray =  new ArrayList<>(Arrays.asList(inputRArray));
        /*
         * We go through the inputKeys and if a new key is being held,
         * add it to the keysHeld ArrayList.
         */
        for (String key : keyInputArray) {
            if (!keysHeld.contains(key) && key != "") {
                keysHeld.add(key);
            }
        }
        /*
         * If a key is not held anymore,
         * remove it from the ArrayList.
         */
        for (String key : keyInputRArray) {
            if (keysHeld.contains(key)) {
                keysHeld.remove(key);
            }
        }
        /*
         * Now since we have all our own logic for how holding keys work,
         * we can implement the rest of setting WorldEntity actions.
         * The world entities that can set actions are character and projectiles.
         */
        for (WorldEntity worldEntity : worldEntities) {
            if (worldEntity instanceof GameCharacter) {     
                Action currentAction = worldEntity.getCurrentAction();
                boolean actionDone = currentAction.getIsDone();
                boolean clickAction = clickActionHash.get((GameCharacter) worldEntity);
                String actionName = currentAction.getName();
                ArrayList<String> actionAvailKeys = worldEntity.getAvailKeys();
                ArrayList<String> keys = new ArrayList<>();
                /*
                 * You will se heldKeyList be used alot in the logic.
                 * The first index of this list is the NewHeldkey.
                 * The second index is the Held key, for checking what the last action when the key
                 * was being held was.
                 * 
                 * More of this is commented later in the code.
                 */
                ArrayList<String> heldKeyList = heldKeyHash.get(worldEntity);
                /*
                 * Checks for all the keys in keysheld and uses the 
                 * characters key predicate to sort out which keys
                 * the specific character uses.
                 */
                for (String key : keysHeld) {
                    if (worldEntity.getPredicate().test(key)) {
                        keys.add(key);
                    }
                }
                /*
                 * We then put these keys into the characters keyhash.
                 */
                inputPerEntity.put((GameCharacter) worldEntity, keys);
                /*
                 * For the held Key and currently holding key logic.
                 * Since we have the functionality to play two different
                 * characters, we need to save these keys in the keyshash.
                 */
                if (!inputPerEntity.get(worldEntity).isEmpty()) {
                    /*
                     * We need to firstly clear the current held key of the character.
                     */
                    heldKeyList.set(0, "");
                    NewHeldKey = "";    
                    for (String key : inputPerEntity.get(worldEntity)) {
                        NewHeldKey += key;    
                    }
                    /*
                     * Now the heldKey gets set to the character if there are any keys.
                     * The reason NewHeldKey is a String, is because some actions in the game use 
                     * more than one key to activate.
                     */
                    heldKeyList.set(0, NewHeldKey); 
                } else {
                    heldKeyList.set(0, "+"); //If no keys are being held, "+" which is no key gets set. This is because we had bugs when just clearing with " ".
                }
                /*
                 * This is a check for clickactions.
                 * Since out method of applying actions are continious, we need
                 * a state where the action can be "played" after just a click,
                 * and not just get reset to "Idle".
                 */
                if ((actionDone && clickActionHash.get(worldEntity))) {
                    clickActionHash.put((GameCharacter) worldEntity, false);
                }
                /*
                 * NewHeldKey is just a variable holding the NewHeldKey to the character. The first index 0 is NewHeldKey,
                 * and second index 1 is HeldKey.
                 */
                NewHeldKey = heldKeyList.get(0);
                clickAction = clickActionHash.get((GameCharacter) worldEntity);
                /*
                 * The reason for the check <= 15 and >= 2 is because the actual actions that are possible to do with keyinputs
                 * in the actionhash for the character is [2, 15].
                 * For example the 0 idex of actionhash is the "Idle" action and is not a doable action by keyinputs.
                 */
                if (actionAvailKeys.contains(NewHeldKey) && actionAvailKeys.indexOf(NewHeldKey) <= 15 && actionAvailKeys.indexOf(NewHeldKey) >= 2) {
                    /*
                     * Defining what the new key is.
                     * Index 2: For player 1 is default set to W.
                     * Index 7: For player 1 is default set to V.
                     * Index 16: For player 1 is default set to C.
                     * You can read more about this in the Singleplayer/Multiplayer game controllers.
                     */
                    boolean newKeyIsJump = NewHeldKey.contains(actionAvailKeys.get(2));
                    boolean newKeyIsSpecial = NewHeldKey.contains(actionAvailKeys.get(7));
                    boolean newKeyIsNormal = NewHeldKey.contains(actionAvailKeys.get(16));
                    /*
                     * Checks for some "special" actions.
                     */
                    if (newKeyIsJump || newKeyIsNormal || newKeyIsSpecial) {
                        /*
                         * This action is jump.
                         * The statements are there to check if jump is doable.
                         */
                        if (newKeyIsJump && currentAction.trySelfInterrupt(worldEntity.getAction(actionAvailKeys.indexOf(NewHeldKey))) && worldEntity.getJumpCounter() <= 1) {
                            /*
                             * Jump is a click action, and therefore should make clickAction true for the character.
                             */
                            clickActionHash.put((GameCharacter) worldEntity, true);
                            heldKeyList.set(1, NewHeldKey);
                            worldEntity.setCurrentAction(actionAvailKeys.indexOf(heldKeyList.get(1)));
                        }
                        /*
                         * This action is special or normal.
                         */
                        else if (((newKeyIsSpecial && !newKeyIsJump) || newKeyIsNormal) && !clickAction) {
                            clickActionHash.put((GameCharacter) worldEntity, true);
                            heldKeyList.set(1, NewHeldKey);
                            worldEntity.setCurrentAction(actionAvailKeys.indexOf(heldKeyList.get(1)));
                            /*
                             * If the current action should spawn a projectile,
                             * put the projectile into the spawn projectile hash for the character.
                             */
                            if (worldEntity.getCurrentAction().isProjectile()) { //Must redefine getCurrentAction.
                                spawnProjectileHash.put((GameCharacter) worldEntity, true);
                            }
                        } else {
                            /*
                             * If action is done, set to Idle.
                             */
                            if (actionDone){
                                worldEntity.setCurrentAction(idle); //Idle = 0
                            }
                        }
                    } else {
                        /*
                         * If the last action is done or was "Idle", or the key held by the 
                         * character is the same as the new held key, and it is not a click action,
                         * then the character should cancel the action it's performing and do the new action.
                         * 
                         * This is forexample used when you want to run and stop running without the action being done.
                         */
                        if (((actionDone || actionName.equals("Idle")) || !heldKeyList.get(1).equals(NewHeldKey)) && !clickAction) {
                            heldKeyList.set(1, NewHeldKey);
                            worldEntity.setCurrentAction(actionAvailKeys.indexOf(heldKeyList.get(1)));
                        }
                    }
                    /*
                     * If there is nothing special fom the key inputs and action is done
                     * or the action name is not "Idle" and not a clickaction, the new action
                     * should be idle.
                     * 
                     * The reasoning behind the NOT "Idle" is since if it is currently an Idle action, 
                     * it would be immidiately canceled by a new Idle action, and therefore not do 
                     * the animation. The way we worked out actions is that when it gets applied, it should
                     * stay if it is the same action. The Looping of Idle therefore works by it being done.
                     */
                } else {
                    if ((actionDone || !actionName.equals("Idle")) && !clickAction) {
                        worldEntity.setCurrentAction(idle); //Idle = 0
                    }
                }
                /*
                 * From the check earlier, if the action should spawn a projectile, 
                 * the spawn projectile hash gets this information.
                 */
                if (spawnProjectileHash.get((GameCharacter) worldEntity)) {
                    Projectile projectile = worldEntity.getCurrentAction().getProjectile();
                    if (projectile != null && projectileReady.get(worldEntity) != projectile) {
                        projectileReady.put((GameCharacter) worldEntity, projectile);
                    }
                } 
                heldKeyList.set(1, NewHeldKey);
            }
            else if (worldEntity instanceof Projectile) {
                if (worldEntity.getCurrentAction() == null) {
                    worldEntity.setCurrentAction(0);
                }
            }
        }
        /*
         * Checks for every character in the world,
         * spawns in the projectiles that are ready.
         */
        for (GameCharacter character : gameCharacters) {
            if (spawnProjectileHash.get(character) && projectileReady.get(character) != null) {
                projectileReady.get(character).setCurrentAction(0);
                addWorldEntity(projectileReady.get(character));
                spawnProjectileHash.put(character, false);
                projectileReady.put(character, null);
            }
        }
        /*
         * If the projectile is finished, it gets thrown into the finishedProjectile Arraylist.
         */
        finishedProjectiles = new ArrayList<>();
        for (WorldEntity projectile  : worldEntities) {
            if (projectile instanceof Projectile) {
                if (projectile.getCurrentAction().getIsDone()) {
                    finishedProjectiles.add((Projectile) projectile);
                }
            }
        }
        /*
         * All the finished projectiles get removed from the world.
         */
        for (Projectile finishedProjectile : finishedProjectiles) {
            worldEntities.remove(finishedProjectile);
        }
    }
    /**
     * Applies all the actions to the worldEntities.
     * This increments all of the worldEntities doAction,
     * which also increments their animation and the action itself.
     */
    private void applyActions(){
        for (WorldEntity worldEntity : worldEntities) {
            worldEntity.doAction();
        }
    }
    /**
     * Adds a worldEntity to the World
     * @param worldEntity asserts the WorldEntity
     */
    private void addWorldEntity(WorldEntity worldEntity){
        worldEntities.add(worldEntity);
    }
    /**
     * Setter for GameOver state
     * @param gameOver asserts the boolean state
     */
    private void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
    /**
     * Setter for GameWinner
     * @param playerNumb asserts the player number of the game winner
     */
    private void setGameWinner(int playerNumb) {
        this.playerNumbWinner = playerNumb;
    }
    /**
     * Setter for the hitStun to a character and calculates how much the knockback the character getting hit
     * should have
     * @param worldCharacter1 asserts WorldEntity that is hitting
     * @param worldCharacter2 asserts WorldEntity that is getting hit
     */
    private void setHitStun(WorldEntity worldCharacter1, WorldEntity worldCharacter2) {
        /*
         * Under the hitstun we should accomplish a few things:
         * worldCharacter2 should get damaged
         * worldCharacter2 should be put into hitstun
         * worldCharacter1 knockback vector should be transferred to worldCharacter2
         * before transferring the knockback vector it should be recalculated, based on the precentage of worldCharacter2.
         */
        worldCharacter2.setCurrentAction(hitStun);
        int damage = worldCharacter1.getCurrentAction().getDamage();
        /*
         * The vectors we need are vec1 and vec2,
         * vec1: a copy of the worldCharacter1 knockback vector
         * vec2: the main vector of worldCharacter2
         */
        Vector vec1 = new Vector(worldCharacter1.getCurrentAction().getKnockback());
        Vector vec2 = worldCharacter2.getVector();
        /*
         * When setting the X and Y velocity we used an easy formula:
         * 
         * For the X velocity, worldCharacter2's precentage is the main pointer to how much knockback it is getting,
         ofcourse also based of the knockback size from the worldCharacter1 action.
         * We also set the direction on the X axis, but not with the built in setDirection() because it would affect Y.
         * 
         * For the Y velocity we dont care about worldCharacter1's facing direction. Therefore the equation is quite similar,
         but with a constant -16. This is because every action with or without Y velocity from worldCharacter1 should apply a 
         * small knockback into the air.
         */
        vec2.setVx((((worldCharacter2.getPrecentage() / 100)) * Math.abs(vec1.getVx())) * worldCharacter1.getFacingDirection());
        vec2.setVy((2 * (worldCharacter2.getPrecentage() / 100)*(vec1.getVy() - 16)));
        /*
         * While setting the Acceleration, we have to be careful.
         * If the acceleration is not a factor of the velocity, its a big no no.
         * Therefore to set both the X and Y acceleration, we get the decimal and use that as the deacceleration of the knockback.
         * This is also the main reason behind having an acceleration, to help the character recover easier at high and low precentages.
         */
        if (vec2.getVx() != 0) {
            vec2.setAx(vec2.getVx() > 0 ? -(worldCharacter2.getPrecentage() / 100) : (worldCharacter2.getPrecentage() / 100));
        }
        if (vec2.getVy() != 0) {
            vec2.setAy(vec2.getVy() > 0 ? -2 * (worldCharacter2.getPrecentage() / 100) : 2 * (worldCharacter2.getPrecentage() / 100));
        }
        worldCharacter2.addPrecentage(damage);
        clickActionHash.put((GameCharacter) worldCharacter2, true);
    }
    /**
     * "Kills" the current gamecharacter and iterates the information and properties
     needed to finish the game.
     * @param worldCharacter asserts the GameCharacter this should affect
     */
    private void characterReset(GameCharacter worldCharacter) {
        if (worldCharacter.getDeathCounter() < 2) {
            worldCharacter.resetAction();
            worldCharacter.setPosition(worldCharacter.getStartX(), worldCharacter.getStartY());
            worldCharacter.resetPrecentage();
            worldCharacter.iterateDeathCounter();
        } else {
            worldCharacter.iterateDeathCounter();
            setGameWinner(worldCharacter.getPlayerNumb() == 1 ? 2 : 1);
            setGameOver(true);
        }
    }
}
