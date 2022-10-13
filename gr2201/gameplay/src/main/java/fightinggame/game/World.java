package fightinggame.game;

import java.util.ArrayList;
import java.util.List;

public class World {
    private ArrayList<WorldEntity> worldEntities;

    public World(ArrayList<WorldEntity> worldEntites) {
        if (worldEntites != null) {
            this.worldEntities = worldEntites;
        }
    }

    public void addWorldEntity(WorldEntity worldEntity) {
        if (worldEntity != null) {
            worldEntities.add(worldEntity);
        } else throw new IllegalArgumentException();
    }

    public void updateWorld() {
        handleCollisions();
        setActions(null);
        applyActions();
    }

    private void handleCollisions() {
        
    }

    private void setActions(List<String> input) {

    }

    private void applyActions() {
        for (WorldEntity worldEntity : worldEntities) {
            worldEntity.doAction();
        }
    }

    







}
