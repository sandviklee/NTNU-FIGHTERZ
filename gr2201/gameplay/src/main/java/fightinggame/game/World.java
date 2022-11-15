package fightinggame.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
public class World {
    private int[] screensize = {1920, 1080};
    private ArrayList<WorldEntity> worldEntities;
    private ArrayList<GameCharacter> gameCharacters = new ArrayList<>();
    private ArrayList<String> keysHeld = new ArrayList<>();
    private ArrayList<String> Sides = new ArrayList<>(Arrays.asList("Top", "Bottom", "Left", "Right"));
    private ArrayList<Projectile> finishedProjectiles;
    
    private HashMap<GameCharacter, HashMap<String, Boolean>> CollisionHash = new HashMap<>();
    private HashMap<GameCharacter, Boolean> clickActionHash = new HashMap<>();
    private HashMap<GameCharacter, ArrayList<String>> heldKeyHash = new HashMap<>();
    private HashMap<GameCharacter, ArrayList<String>> inputPerEntity = new HashMap<>();
    private HashMap<GameCharacter, Projectile> projectileReady = new HashMap<>();
    private HashMap<GameCharacter, Boolean> spawnProjectileHash = new HashMap<>();

    private String NewHeldKey = "Idle";
    private Effectbox worldBox;

    public World(ArrayList<WorldEntity> worldEntities){
        this.worldEntities = new ArrayList<>(worldEntities);
        this.worldBox = new Effectbox(null, new Point(screensize[0]/2, screensize[1]/2), false, screensize[0] + 500, screensize[1] + 1000);

        for (WorldEntity entity : worldEntities) {
            if(entity instanceof GameCharacter){
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

    public void updateWorld(String input, String inputR){
        handleCollisions();
        setActions(input, inputR);
        applyActions();
    }

    private void handleCollisions(){
        for (WorldEntity entity1 : worldEntities) {
            if (entity1 instanceof GameCharacter) {
                if (entity1.getHurtBox().EffectBoxInEffectBox(worldBox).equals("Outside")) {
                    characterReset((GameCharacter) entity1);
                }
            }

            for (String side : Sides) {
                if (entity1 instanceof GameCharacter) {
                    CollisionHash.get((GameCharacter) entity1).put(side, false);
                }
            }

            for (WorldEntity entity2 : worldEntities) {
                if (entity1 instanceof GameCharacter && entity2 instanceof Terrain) {
                    String currentSide = entity1.getHurtBox().EffectBoxInEffectBox(entity2.getHitBox());

                    if (Sides.contains(currentSide)) {
                        CollisionHash.get((GameCharacter) entity1).put(currentSide, true);
                    }

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

                        case "Outside":
                        entity1.setOnGround(CollisionHash.get((GameCharacter) entity1).get("Top"));
                        entity1.setOnTop(CollisionHash.get((GameCharacter) entity1).get("Bottom"));
                        entity1.setOnRight(CollisionHash.get((GameCharacter) entity1).get("Right"));
                        entity1.setOnLeft(CollisionHash.get((GameCharacter) entity1).get("Left"));
                        break;
                    } 
                } 
                if (entity1 instanceof Projectile && entity2 instanceof GameCharacter) {
                    if (entity1 != entity2 && entity1.getHitBox() != null) {
                        String currentSide;
                        currentSide = entity1.getHitBox().EffectBoxInEffectBox(entity2.getHurtBox());
                        
                        if (Sides.contains(currentSide) || currentSide.equals("Contains")) {
                            if (entity2.getCurrentAction().tryEnemyInterrupt(entity2.getAction(1))) {
                                setHitStun(entity1, entity2);
                            }
                        }
                    }
                }

                if (entity1 instanceof GameCharacter && entity2 instanceof GameCharacter) {
                    if (entity1 != entity2 && entity1.getCurrentAction().getHitBox() != null) {
                        String currentSide;
                        currentSide = entity1.getCurrentAction().getHitBox().EffectBoxInEffectBox(entity2.getHurtBox());
                        
                        if (Sides.contains(currentSide) || currentSide.equals("Contains")) {
                            if (entity2.getCurrentAction().tryEnemyInterrupt(entity2.getAction(1))) {
                                setHitStun(entity1, entity2);
                            }
                        }
                    }
                }
            }
        }
    }

    private void setActions(String input, String inputR){
        String[] inputArray = input.split("");
        ArrayList<String> keyInputArray =  new ArrayList<>(Arrays.asList(inputArray));
        String[] inputRArray = inputR.split("");
        ArrayList<String> keyInputRArray =  new ArrayList<>(Arrays.asList(inputRArray));
        
        for (String key : keyInputArray) {
            if (!keysHeld.contains(key) && key != "") {
                keysHeld.add(key);
            }
        }

        for (String key : keyInputRArray) {
            if (keysHeld.contains(key)) {
                keysHeld.remove(key);
            }
        }
        
        for (WorldEntity worldEntity : worldEntities) {
            if (worldEntity instanceof GameCharacter) {     
                Action currentAction = worldEntity.getCurrentAction();
                ArrayList<String> actionAvailKeys = worldEntity.getAvailKeys();
                ArrayList<String> keys = new ArrayList<>();

                for (String key : keysHeld) {
                    if (worldEntity.getPredicate().test(key)) {
                        keys.add(key);
                    }
                }

                inputPerEntity.put((GameCharacter) worldEntity, keys);

                if (!inputPerEntity.get(worldEntity).isEmpty()) { //get first key
                    heldKeyHash.get(worldEntity).set(0, "");
                    NewHeldKey = "";    
                    for (String key : inputPerEntity.get(worldEntity)) {
                        NewHeldKey += key;    
                    }
                    heldKeyHash.get(worldEntity).set(0, NewHeldKey);

                } else {
                    heldKeyHash.get(worldEntity).set(0, "+");
                }

                if (currentAction.getIsDone() && clickActionHash.get(worldEntity)) {
                    clickActionHash.put((GameCharacter) worldEntity, false);
                }
                
                NewHeldKey = heldKeyHash.get(worldEntity).get(0); //Get the GameCharacters NewHeldKey

                if (actionAvailKeys.contains(NewHeldKey) && actionAvailKeys.indexOf(NewHeldKey) <= 15 && actionAvailKeys.indexOf(NewHeldKey) >= 2) {
                    if (NewHeldKey.contains(actionAvailKeys.get(2)) || NewHeldKey.contains(actionAvailKeys.get(7)) || NewHeldKey.contains(actionAvailKeys.get(15).substring(1))) {
                        if (((NewHeldKey.contains(actionAvailKeys.get(2)) && currentAction.trySelfInterrupt(worldEntity.getAction(actionAvailKeys.indexOf(NewHeldKey)))) && worldEntity.getJumpCounter() <= 1)) {
                            clickActionHash.put((GameCharacter) worldEntity, true);
                            heldKeyHash.get(worldEntity).set(1, NewHeldKey);
                            worldEntity.setCurrentAction(actionAvailKeys.indexOf(heldKeyHash.get(worldEntity).get(1)));
                        }

                        else if (((NewHeldKey.contains(actionAvailKeys.get(7)) && !NewHeldKey.contains(actionAvailKeys.get(2))) || NewHeldKey.contains(actionAvailKeys.get(15).substring(1))) && !clickActionHash.get(worldEntity)) {
                            clickActionHash.put((GameCharacter) worldEntity, true);
                            heldKeyHash.get(worldEntity).set(1, NewHeldKey);
                            worldEntity.setCurrentAction(actionAvailKeys.indexOf(heldKeyHash.get(worldEntity).get(1)));
                            
                            if (worldEntity.getCurrentAction().isProjectile()) {
                                spawnProjectileHash.put((GameCharacter) worldEntity, true);
                            }

                        } else {
                            if (currentAction.getIsDone()){
                                worldEntity.setCurrentAction(0);
                            }
                        }

                    } else {
                        if (((currentAction.getIsDone() || currentAction.getName().equals("Idle")) || !heldKeyHash.get(worldEntity).get(1).equals(NewHeldKey)) && !clickActionHash.get(worldEntity)) {
                            heldKeyHash.get(worldEntity).set(1, NewHeldKey);
                            worldEntity.setCurrentAction(actionAvailKeys.indexOf(heldKeyHash.get(worldEntity).get(1)));
                        }
                    }

                } else {
                    if ((worldEntity.getCurrentAction().getIsDone() || !worldEntity.getCurrentAction().getName().equals("Idle")) && !clickActionHash.get(worldEntity)) {
                        worldEntity.setCurrentAction(0);
                    }
                }
            
                if (spawnProjectileHash.get((GameCharacter) worldEntity)) {
                    Projectile projectile = worldEntity.getCurrentAction().getProjectile();
                    if (projectile != null && projectileReady.get(worldEntity) != projectile) {
                        projectileReady.put((GameCharacter) worldEntity, projectile);
                    }
                    
                
                } 
                heldKeyHash.get(worldEntity).set(1, NewHeldKey);
                
            }

            else if (worldEntity instanceof Projectile) {
                if (worldEntity.getCurrentAction() == null) {
                    worldEntity.setCurrentAction(0);
                }
            }
            
        }
        
        for (GameCharacter character : gameCharacters) {
            if (spawnProjectileHash.get(character) && projectileReady.get(character) != null) {
                projectileReady.get(character).setCurrentAction(0);
                addWorldEntity(projectileReady.get(character));
                spawnProjectileHash.put(character, false);
                projectileReady.put(character, null);
            }
        }

        finishedProjectiles = new ArrayList<>();
        for (WorldEntity projectile  : worldEntities) {
            if (projectile instanceof Projectile) {
                if (projectile.getCurrentAction().getIsDone()) {
                    finishedProjectiles.add((Projectile) projectile);
                }
            }
        }
        
        for (Projectile finishedProjectile : finishedProjectiles) {
            worldEntities.remove(finishedProjectile);
        }
    }

    private void applyActions(){
        for (WorldEntity worldEntity : worldEntities) {
            worldEntity.doAction();
        }
    }

    public ArrayList<WorldEntity> getWorldEntities() {
        return worldEntities;
    }
    
    private void addWorldEntity(WorldEntity worldEntity){
        worldEntities.add(worldEntity);
    }

    private void setHitStun(WorldEntity worldCharacter1, WorldEntity worldCharacter2) {
        worldCharacter2.setCurrentAction(1);
        int damage = worldCharacter1.getCurrentAction().getDamage();
        
        Vector vec1 = new Vector(worldCharacter1.getCurrentAction().getKnockback());
        Vector vec2 = worldCharacter2.getVector();

        vec2.setVx(Math.floor(((worldCharacter2.getPrecentage()/100))*Math.abs(vec1.getVx()))*worldCharacter1.getFacingDirection());
        
    
        vec2.setVy(Math.floor(((worldCharacter2.getPrecentage()/100))*(vec1.getVy() - 24)));
        System.out.println(vec2.getVy());
        if (vec2.getVx() != 0) {
            vec2.setAx(vec1.getVx() > 0 ? -2 : 2);
        }
        if (vec2.getVy() != 0) {
            vec2.setAy(vec1.getVy() > 0 ? -2 : 2);
        }
    
        worldCharacter2.addPrecentage(damage);
        //System.out.println(worldCharacter2.getPrecentage());
        clickActionHash.put((GameCharacter) worldCharacter2, true);
    }

    private void characterReset(GameCharacter worldCharacter) {
        if (worldCharacter.getDeathCounter() < 2) {
            worldCharacter.resetAction();
            worldCharacter.setPosition(worldCharacter.getStartX(), worldCharacter.getStartY());
            worldCharacter.resetPrecentage();
            worldCharacter.iterateDeathCounter();
        }
    }

}
