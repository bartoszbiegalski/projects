package org.gogroup.components.gameComponents.bottomVBox.buttonVBox;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.gogroup.components.gameComponents.bottomVBox.GameButton;

public class ButtonVBox extends HBox {
    private GameButton skipButton;
    private GameButton surrenderButton;

    public ButtonVBox() {
        skipButton = new GameButton("Skip turn");
        surrenderButton = new GameButton("Surrender");
        getChildren().add(skipButton);
        getChildren().add(surrenderButton);
        setAlignment(Pos.CENTER);
        setSpacing(100);
    }

    public GameButton getSurrenderButton() {
        return this.surrenderButton;
    }

    public GameButton getSkipButton() {
        return this.skipButton;
    }
}
