package fightinggame.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class World {
    private ArrayList<WorldEntity> worldEntities;
    private ArrayList<String> keysHeld = new ArrayList<>();
    private HashMap<GameCharacter, Boolean> isOnGroundHash = new HashMap<>();
    private String HeldKey = "Idle";
    private String NewHeldKey = "Idle";
    private boolean clickAction = false;

    public World(ArrayList<WorldEntity> worldEntities){
        this.worldEntities = worldEntities;
        for (WorldEntity entity : worldEntities) {
            if(entity instanceof GameCharacter){
                isOnGroundHash.put((GameCharacter) entity, false);
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

        //System.out.println(clickAction + " ClickAction");

        //System.out.println("Keys Held: " + keysHeld + " keyArray: " + keyInputArray);

        for (WorldEntity worldEntity : worldEntities) {
            if (worldEntity instanceof GameCharacter) {
                
                if (!keysHeld.isEmpty()) { //get first key
                    NewHeldKey = "";
                    for (String key : keysHeld) {
                        NewHeldKey += key;
                    }
                } else {
                    NewHeldKey = "+";
                }

                //System.out.println(NewHeldKey);

                Action currentAction = worldEntity.getCurrentAction();
                ArrayList<String> actionAvailKeys = worldEntity.getAvailKeys();

                if (currentAction.getIsDone() && clickAction) {
                    clickAction = false;
                }

                if (worldEntity.getAvailKeys().contains(NewHeldKey)) {
                    //System.out.println(HeldKey + " New: " + NewHeldKey);

                    if (NewHeldKey.contains("W") || NewHeldKey.contains("G")) {
                        
                        if (((worldEntity.getAction(actionAvailKeys.indexOf(NewHeldKey)).getActionPriority() > 
                        currentAction.getActionPriority()) && worldEntity.getJumpCounter() <= 1)) {
                            clickAction = true;
                            HeldKey = NewHeldKey;
                            //System.out.println(worldEntity.getAvailKeys().indexOf(HeldKey) + " Index");
                            worldEntity.setCurrentAction(actionAvailKeys.indexOf(HeldKey));
                        } else {
                            if (currentAction.getIsDone()){
                                worldEntity.setCurrentAction(0);
                            }
                            
                        }
                    } else {
                        
                        if (((currentAction.getIsDone() || currentAction.getName().equals("Idle")) || !HeldKey.equals(NewHeldKey)) && !clickAction) {
                            
                            HeldKey = NewHeldKey;
                            //System.out.println(actionAvailKeys.indexOf(HeldKey) + " Index");
                            worldEntity.setCurrentAction(actionAvailKeys.indexOf(HeldKey));
                        }
                    }


                    //List<String> keyInputList = keyInputArray.stream().filter(worldEntity.getPredicate()).collect(Collectors.toList()); //Legger alle mulige keyInputs i en liste
                    //Så iterer og gjør alle som er mulig til true i hashmappet

                } else {
                    if ((worldEntity.getCurrentAction().getIsDone() || !worldEntity.getCurrentAction().getName().equals("Idle")) && !clickAction) {
                        worldEntity.setCurrentAction(0);
                    }
                }
            }
            
        }
        HeldKey = NewHeldKey;
    }

    private void applyActions(){
        for (WorldEntity worldEntity : worldEntities) {
            worldEntity.doAction();
        }
    }
}
