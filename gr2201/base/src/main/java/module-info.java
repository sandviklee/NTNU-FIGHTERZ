module fightinggame.base {
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    exports fightinggame.users;
    // Do not want dbaccess to be open
}
