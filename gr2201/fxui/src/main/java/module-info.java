module fightinggame.ui {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires fightinggame.base;
    requires fightinggame.gameplay;
    
    opens fightinggame.ui to javafx.fxml;
    exports fightinggame.ui;
}
