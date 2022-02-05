package com.example.progettoispw;

import javafx.scene.layout.AnchorPane;

public class EnablePane {
    private EnablePane(){}

    public static void enablePane(AnchorPane pane, boolean able) {
        if (!able) {
            pane.toBack();
            pane.setOpacity(0);
            pane.setDisable(true);
        }
        else{
            pane.toFront();
            pane.setOpacity(1);
            pane.setDisable(false);
        }
    }
}
