package fightinggame.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class World {
    private ArrayList<WorldEntity> worldEntities;
    private GameCharacter gameCharacter1;
    private GameCharacter gameCharacter2;
    private int amountUntilHeld = 0;
    private boolean held = false;
    private HashMap<String, Boolean> booleanHash = new LinkedHashMap<>();
    private String HeldKey = "Idle";
    private String NewHeldKey = "Idle";
    private boolean clickAction = false;

    public World(ArrayList<WorldEntity> worldEntities){
        this.worldEntities = worldEntities;
        for (WorldEntity entity : worldEntities) {
            if(entity instanceof GameCharacter){
                if(gameCharacter1==null){
                    gameCharacter1 = (GameCharacter) entity;
                } else {
                    gameCharacter2 = (GameCharacter) entity;
                }
            }
        }
    }

    public void addWorldEntity(WorldEntity worldEntity){
        this.worldEntities.add(worldEntity);
    }

    public void updateWorld(String input, String inputR){
        handleCollisions();
        setActions(input, inputR);
        applyActions();
    }

    private void handleCollisions(){
        for (WorldEntity entity1 : worldEntities) {
            for (WorldEntity entity2 : worldEntities) {
                if (entity1 instanceof GameCharacter && entity2 instanceof Terrain) {
                    entity1.setOnGround(entity2.hitboxCollision(entity1.getHurtBox()));
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
            if (!booleanHash.keySet().contains(key) && key != "") {
                booleanHash.put(key, true);
            }
        }

        for (String key : keyInputRArray) {
            if (booleanHash.keySet().contains(key)) {
                booleanHash.remove(key);
            }
        }

        if (!booleanHash.isEmpty()) {
            Map.Entry<String, Boolean> entry = booleanHash.entrySet().iterator().next();
            NewHeldKey = entry.getKey();
        } else {
            NewHeldKey = "+";
        }

        //System.out.println(clickAction + " ClickAction");

        System.out.println(booleanHash + " keyArray: " + keyInputArray);

        for (WorldEntity worldEntity : worldEntities) {
            if (worldEntity instanceof GameCharacter) {
                //System.out.println(worldEntity.getOnGround());
                Action currentAction = worldEntity.getCurrentAction();
                ArrayList<String> actionAvailKeys = worldEntity.getAvailKeys();

                if (currentAction.getIsDone() && clickAction) {
                    clickAction = false;
                }

                if (worldEntity.getAvailKeys().contains(NewHeldKey)) {
                    //System.out.println(HeldKey + " New: " + NewHeldKey);

                    if (NewHeldKey.equals("W")) {
                        
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
                        
                        if (((currentAction.getIsDone() || currentAction.getName().equals("Idle")) || HeldKey != NewHeldKey) && !clickAction) {
                            
                            HeldKey = NewHeldKey;
                            //System.out.println(actionAvailKeys.indexOf(HeldKey) + " Index");
                            worldEntity.setCurrentAction(actionAvailKeys.indexOf(HeldKey));
                        }
                    }


                    //List<String> keyInputList = keyInputArray.stream().filter(worldEntity.getPredicate()).collect(Collectors.toList()); //Legger alle mulige keyInputs i en liste
                    //Så iterer og gjør alle som er mulig til true i hashmappet

                } else {
                    if ((worldEntity.getCurrentAction().getIsDone() || !worldEntity.getCurrentAction().getName().equals("Idle")) && booleanHash.isEmpty() && !clickAction) {
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
