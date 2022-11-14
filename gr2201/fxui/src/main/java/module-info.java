module fightinggame.ui {
    requires com.fasterxml.jackson.databind;
    requires java.net.http;
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires fightinggame.base;
    requires fightinggame.gameplay;
    
    opens fightinggame.ui to javafx.fxml, javafx.graphics;
    exports fightinggame.ui;
    
}
