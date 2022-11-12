module fightinggame.gameplay {
    requires transitive com.fasterxml.jackson.core;
    requires transitive com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    exports fightinggame.game;
    exports fightinggame.characterjson;
}
