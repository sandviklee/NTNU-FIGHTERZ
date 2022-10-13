package fightinggame.game;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class World {
    private ArrayList<WorldEntity> worldEntities;
    private GameCharacter gameCharacter1;
    private GameCharacter gameCharacter2;

    public World(ArrayList<WorldEntity> worldEntities){
        this.worldEntities = worldEntities;
        for (WorldEntity entity : worldEntities) {
            if(entity.getClass().equals(GameCharacter.class)){
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

    public void updateWorld(ArrayList<String> input){
        handleCollisions();
        setActions(input);
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

    private void setActions(ArrayList<String> input){
        String inputString = String.join("", input);
        String validInput = inputString.replaceAll("[^WASDKL ]", "");
        gameCharacter1.setCurrentAction(validInput);
    }

    private void applyActions(){
        for (WorldEntity worldEntity : worldEntities) {
            worldEntity.doAction();
        }
    }
}
