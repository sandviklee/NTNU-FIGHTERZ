package fightinggame.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
public class World {
    private ArrayList<WorldEntity> worldEntities;
    private ArrayList<GameCharacter> gameCharacters = new ArrayList<>();
    private ArrayList<String> keysHeld = new ArrayList<>();
    
    private ArrayList<Projectile> finishedProjectiles;
    private HashMap<GameCharacter, Boolean> isOnGroundHash = new HashMap<>();
    private HashMap<GameCharacter, Boolean> clickActionHash = new HashMap<>();
    private HashMap<GameCharacter, ArrayList<String>> heldKeyHash = new HashMap<>();
    private HashMap<GameCharacter, ArrayList<String>> inputPerEntity = new HashMap<>();
    private HashMap<GameCharacter, Projectile> projectileReady = new HashMap<>();
    private HashMap<GameCharacter, Boolean> spawnProjectileHash = new HashMap<>();
    private String NewHeldKey = "Idle";

    public World(ArrayList<WorldEntity> worldEntities){
        this.worldEntities = new ArrayList<>(worldEntities);
        for (WorldEntity entity : worldEntities) {
            if(entity instanceof GameCharacter){
                isOnGroundHash.put((GameCharacter) entity, false);
                clickActionHash.put((GameCharacter) entity, false);
                spawnProjectileHash.put((GameCharacter) entity, false);
                ArrayList<String> heldKeysList = new ArrayList<>(Arrays.asList("Idle", "Idle"));
                heldKeyHash.put((GameCharacter) entity, heldKeysList);
                projectileReady.put((GameCharacter) entity, null);
                inputPerEntity.put((GameCharacter) entity, new ArrayList<>());
                gameCharacters.add((GameCharacter) entity);
            }
        }
    }

    public void addWorldEntity(WorldEntity worldEntity){
        worldEntities.add(worldEntity);
        if (worldEntity instanceof GameCharacter){
            isOnGroundHash.put((GameCharacter) worldEntity, false);
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
                isOnGroundHash.put((GameCharacter) entity1, false);
            }
            for (WorldEntity entity2 : worldEntities) {
                if (entity1 instanceof GameCharacter && entity2 instanceof Terrain) {
                    switch (entity1.getHurtBox().EffectBoxInEffectBox(entity2.getHitBox())) {
                        case "Top":
                        isOnGroundHash.put((GameCharacter) entity1, true);
                        break;

                        case "Bottom":
                        //TODO:
                        break;

                        case "Left":
                        //TODO:
                        break;

                        case "Right":
                        //TODO:
                        break;
                    }
                    /*
                    if (entity2.hitboxCollision(entity1.getHurtBox())) {
                        isOnGroundHash.put((GameCharacter) entity1, true);
                    }
                    */
                    
                }
                
                if (entity1 instanceof GameCharacter) {
                    entity1.setOnGround(isOnGroundHash.get(entity1));
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

        //System.out.println("Keys Held: " + keysHeld + " keyArray: " + keyInputArray);

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

                if (actionAvailKeys.contains(NewHeldKey) && actionAvailKeys.indexOf(NewHeldKey) <= 15) {
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
                worldEntities.add(projectileReady.get(character));
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

    
}
