package fightinggame.game;

public abstract class WorldEntity {
	protected int id;
	protected Effectbox hitBox;
	protected Action currentAction;
	protected boolean isAlive;

	public WorldEntity() {

	}
	
	public void doAction(){
		this.getCurrentAction().handleAction();
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

	private void setCurrentAction(Action currentAction) {
		this.currentAction = currentAction;
	}

	private Sprite getSprite() {
		return sprite;
	}

	private void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public boolean isAlive() {
		return isAlive;
	}

	private void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
}