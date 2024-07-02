package org.gogroup.components.gameComponents.bottomVBox.playerVBox;

import javafx.scene.layout.VBox;
import org.gogroup.components.gameComponents.PlayerInfoVBox.PlayerLabel;

public class PlayerVBox extends VBox {
    private PlayerLabel playerLabel;

    public PlayerVBox(String labelText) {
        this.playerLabel = new PlayerLabel(labelText);
        getChildren().add(playerLabel);
    }

    public PlayerLabel getPlayerLabel() {
        return this.playerLabel;
    }

}
