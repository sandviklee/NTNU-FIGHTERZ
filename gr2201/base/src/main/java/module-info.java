module fightinggame.base {
    requires transitive com.fasterxml.jackson.core;
    requires transitive com.fasterxml.jackson.databind;
    requires transitive com.fasterxml.jackson.annotation;
    requires transitive fightinggame.gameplay;
    exports fightinggame.users;

    exports fightinggame.dao; // to fightinggame.ui, fightinggame.springboot.restserver;
    exports fightinggame.utils; // to fightinggame.springboot.restserver, fightinggame.ui;
    exports fightinggame.utils.json.characterattributes; // to fightinggame.ui;
    exports fightinggame.utils.json.characterinfo; // to fightinggame.ui;
    exports fightinggame.utils.json.users; // to fightinggame.ui;
    // Do not want dbaccess to be open
}
