package fightinggame.utils.json.characterattributes;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;

import fightinggame.game.Effectbox;
import fightinggame.game.Vector;
import fightinggame.game.ActionProperties;
import fightinggame.game.PlayerProperties;

public class GameplayModule extends SimpleModule {
    private static final String NAME = "GameplayModule";

    /**
     * Initializes this GameplayModule with appropriate deserializers.
     */
    public GameplayModule() {
        super(NAME, Version.unknownVersion());
        addDeserializer(ActionProperties.class, new ActionPropertiesDeserializer());
        addDeserializer(Effectbox.class, new EffectboxDeserializer());
        addDeserializer(Vector.class, new VectorDeserializer());
        addDeserializer(PlayerProperties.class, new PlayerPropertiesDeserializer());
    }
}
