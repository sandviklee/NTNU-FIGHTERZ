package fightinggame.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class World {
    private ArrayList<WorldEntity> worldEntities;
    private GameCharacter gameCharacter1;
    private GameCharacter gameCharacter2;
    private int amountUntilHeld = 0;
    private boolean held = false;

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

    public void updateWorld(String input, boolean held){
        //handleCollisions();
        setActions(input, held);
        applyActions();
    }

    private void handleCollisions(){
        for (WorldEntity entity1 : worldEntities) {
            for (WorldEntity entity2 : worldEntities) {
                if(entity1.equals(entity2)){
                    System.out.println("Collision between" + entity1 + "and" + entity2);
                }
            }
        }
    }

    private void setActions(String input, boolean held){
        String[] inputArray = input.split("");
        ArrayList<String> keyInputArray = new ArrayList<>(Arrays.asList(inputArray));
        for (WorldEntity worldEntity : worldEntities) {
            if (worldEntity instanceof GameCharacter) {
                if (keyInputArray.stream().anyMatch(worldEntity.getPredicate())) {
                    String keyInput = keyInputArray.stream().filter(worldEntity.getPredicate()).collect(Collectors.toList()).get(0);
                    if (held) {
                        if (worldEntity.getCurrentAction().getIsDone() || worldEntity.getCurrentAction().getName().equals("Idle")) {
                            System.out.println(keyInput);
                            worldEntity.setCurrentAction(worldEntity.getAvailKeys().indexOf(keyInput));
                        }
                    }

                } else {
                    if ((worldEntity.getCurrentAction().getIsDone() || !worldEntity.getCurrentAction().getName().equals("Idle")) && !held) {
                        worldEntity.setCurrentAction(0);
                    }
                }
            }
        }
    }

    private void applyActions(){
        for (WorldEntity worldEntity : worldEntities) {
            worldEntity.doAction();
        }
    }
}
