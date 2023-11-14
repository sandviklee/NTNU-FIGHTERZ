package fightinggame.utils.json.characterinfo;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;

import fightinggame.utils.CharacterInformationObject;

public class CharacterInformationModule extends SimpleModule {
    private static final String NAME = "CharacterInformationModule";

    /**
     * Initializes this GameplayModule with appropriate deserializers.
     */
    public CharacterInformationModule() {
        super(NAME, Version.unknownVersion());
        addDeserializer(CharacterInformationObject.class, new CharacterInformationDeserializer());
    }
}
