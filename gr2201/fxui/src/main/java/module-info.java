module fightinggame.ui {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires fightinggame.base;
    requires transitive fightinggame.gameplay;
    requires com.fasterxml.jackson.databind;
    
    opens fightinggame.ui to javafx.fxml, javafx.graphics;
    exports fightinggame.ui;
    
}
