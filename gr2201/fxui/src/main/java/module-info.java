module fightinggame.ui {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires transitive com.fasterxml.jackson.core;
    requires transitive com.fasterxml.jackson.databind;
    requires transitive com.fasterxml.jackson.annotation;
    requires fightinggame.base;
    requires transitive fightinggame.gameplay;
    requires com.fasterxml.jackson.databind;
    
    opens fightinggame.ui to javafx.fxml, javafx.graphics;
    exports fightinggame.ui;
    exports fightinggame.characterJSON;
    
}
