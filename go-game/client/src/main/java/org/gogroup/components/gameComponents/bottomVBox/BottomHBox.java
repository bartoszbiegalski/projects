package org.gogroup.components.gameComponents.bottomVBox;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import org.gogroup.components.gameComponents.PlayerInfoVBox.PlayerLabel;
import org.gogroup.components.gameComponents.bottomVBox.buttonVBox.ButtonVBox;
import org.gogroup.components.gameComponents.bottomVBox.playerVBox.PlayerVBox;

public class BottomHBox extends HBox {
    private PlayerVBox playerVBox;
    private ButtonVBox buttonVBox;

    public BottomHBox(String player)
    {
        this.playerVBox = new PlayerVBox(player);
        this.buttonVBox = new ButtonVBox();

        setSpacing(100);
        setPadding(new javafx.geometry.Insets(70, 0, 0, 0));

        setAlignment(Pos.CENTER);
        getChildren().add(playerVBox);
        getChildren().add(buttonVBox);
    }

    public PlayerVBox getPlayerVBox() {
        return this.playerVBox;
    }

    public void setPlayerVBox(String currentPlayer) {this.playerVBox.getPlayerLabel().setText(currentPlayer);}

    public ButtonVBox getButtonVBox() {
        return this.buttonVBox;
    }
}
