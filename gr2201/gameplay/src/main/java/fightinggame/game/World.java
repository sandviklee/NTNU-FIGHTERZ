package fightinggame.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
public class World {
    private ArrayList<WorldEntity> worldEntities;
    private ArrayList<String> keysHeld = new ArrayList<>();
    private ArrayList<Projectile> projectileReadyList = new ArrayList<>();
    private HashMap<GameCharacter, Boolean> isOnGroundHash = new HashMap<>();
    private HashMap<GameCharacter, Boolean> clickActionHash = new HashMap<>();
    private HashMap<GameCharacter, ArrayList<String>> heldKeyHash = new HashMap<>();
    private HashMap<GameCharacter, Boolean> spawnProjectileHash = new HashMap<>();
    private String NewHeldKey = "Idle";

    public World(ArrayList<WorldEntity> worldEntities){
        this.worldEntities = worldEntities;
        for (WorldEntity entity : worldEntities) {
            if(entity instanceof GameCharacter){
                isOnGroundHash.put((GameCharacter) entity, false);
                clickActionHash.put((GameCharacter) entity, false);
                spawnProjectileHash.put((GameCharacter) entity, false);
                ArrayList<String> heldKeysList = new ArrayList<>(Arrays.asList("Idle", "Idle"));
                heldKeyHash.put((GameCharacter) entity, heldKeysList);
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
                    if (entity2.hitboxCollision(entity1.getHurtBox())) {
                        isOnGroundHash.put((GameCharacter) entity1, true);
                    }
                    
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

                if (!keysHeld.isEmpty()) { //get first key
                    heldKeyHash.get(worldEntity).set(0, "");
                    NewHeldKey = "";    
                    for (String key : keysHeld) {
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
                if (actionAvailKeys.contains(NewHeldKey)) {
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
                    if (!projectileReadyList.contains(projectile) && projectile != null) {
                        projectileReadyList.add(projectile);
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

        if (spawnProjectileHash.values().stream().anyMatch(x -> x == true) && !projectileReadyList.isEmpty()) {
            ArrayList<Projectile> finishedProjectiles = new ArrayList<>();
            for (WorldEntity entity  : worldEntities) {
                if (entity instanceof Projectile) {
                    if (entity.getCurrentAction().getIsDone()) {
                        finishedProjectiles.add((Projectile) entity);
                    }
                }
            }
            for (Projectile finishedProjectile : finishedProjectiles) {
                worldEntities.remove(finishedProjectile);
            }
            
            for (Projectile projectile : projectileReadyList) {
                if (projectile != null) {
                    projectile.setCurrentAction(0);
                    worldEntities.add(projectile);
                }

            }
            
            projectileReadyList.clear();
            
            for (WorldEntity worldEntity : worldEntities) {
                if (worldEntity instanceof GameCharacter) {
                    spawnProjectileHash.put((GameCharacter) worldEntity, false);
                }
            }
            
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
