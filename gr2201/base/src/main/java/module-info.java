module fightinggame.base {
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    exports fightinggame.users;
    exports fightinggame.dbaccess;
    exports fightinggame.json;
    // Do not want dbaccess to be open
}
