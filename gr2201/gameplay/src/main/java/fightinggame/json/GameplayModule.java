package fightinggame.json;

import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.module.SimpleModule;

import fightinggame.game.Effectbox;
import fightinggame.game.Vector;
import fightinggame.game.Action;
import fightinggame.game.AnimationSprite;
import fightinggame.game.Projectile;
import fightinggame.game.GameCharacter;


public class GameplayModule extends SimpleModule {
	private static final String NAME = "GameplayModule";
	private static final VersionUtil VERSION_UTIL = new VersionUtil() {};
  
  
	/**
	* Initializes this GameplayModule with appropriate deserializers.
	*/
	public GameplayModule() {
		super(NAME, VERSION_UTIL.version());
		addDeserializer(Action.class, new ActionDeserializer());
		addDeserializer(Effectbox.class, new EffectboxDeserializer());
		addDeserializer(Vector.class, new VectorDeserializer());
		addDeserializer(AnimationSprite.class, new AnimationSpriteDeserializer());
		addDeserializer(Projectile.class, new ProjectileDeserializer());
		addDeserializer(GameCharacter.class, new GameCharacterDeserializer());
	}
}
