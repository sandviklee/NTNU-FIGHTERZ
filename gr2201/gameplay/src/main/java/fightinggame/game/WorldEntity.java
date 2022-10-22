package fightinggame.game;

import java.util.ArrayList;

public abstract class WorldEntity {
	protected int id;
	protected Effectbox hitBox;
	protected Action currentAction;
	protected boolean isAlive;
	protected String name;
	protected Point point;

	public WorldEntity(String name, ArrayList<Integer> pos) {
		this.name = name;
		this.point = new Point((double) pos.get(0), (double) pos.get(1));
	}
	
	public void doAction(){
		this.getCurrentAction().nextActionFrame();
	}

	public String getName() {
		return name;
	}

	public Point getPoint() {
		return point;
	}

	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	public Effectbox getHitBox() {
		return hitBox;
	}

	private void setHitBox(Effectbox hitBox) {
		this.hitBox = hitBox;
	}

	public Action getCurrentAction() {
		return currentAction;
	}

	public void setCurrentAction(Action currentAction) {
		this.currentAction = currentAction;
	}

	public int getCurrentFrame(){
		return currentAction.getCurrentFrame();
	}

	public boolean isAlive() {
		return isAlive;
	}

	private void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
}
