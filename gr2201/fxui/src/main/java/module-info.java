module fightinggame.ui {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires fightinggame.core;
    
    opens fightinggame.ui to javafx.fxml;
    exports fightinggame.ui;
}
