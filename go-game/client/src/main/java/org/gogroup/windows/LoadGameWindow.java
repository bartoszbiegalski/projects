package org.gogroup.windows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import org.gogroup.components.BackButton;
import org.gogroup.components.lobbyComponents.LobbyButton;
import org.gogroup.components.lobbyComponents.LobbyVBox;
import org.gogroup.components.lobbyComponents.PlayerCountLabel;
import org.gogroup.components.menuComponents.RoomRow;
import org.gogroup.controllers.LoadGameWindowController;
import org.gogroup.exceptions.JoinRoomException;
import org.gogroup.model.Room;

import java.util.ArrayList;
import java.util.HashMap;

public class LoadGameWindow extends AbstractWindow {
    private LoadGameWindowController loadGameWindowController;
    private ScrollPane scrollPane;
    private RoomRow gamesRow;
    private LobbyVBox topVBox;
    private LobbyVBox bottomVBox;
    private LobbyVBox scrollPaneVBox;
    private ArrayList<Room> games;

    public LoadGameWindow(LoadGameWindowController loadGameWindowController) { this.loadGameWindowController = loadGameWindowController; }

    public void setGames()
    {
        this.games = this.loadGameWindowController.loadGames();
    }

    public void setGamesRow()
    {
        setGames();
        this.gamesRow = new RoomRow("lala", 1);
        this.gamesRow.setAlignment(Pos.CENTER);
        this.games.forEach(room -> {
            LobbyButton child = new LobbyButton("Room " + room.getGameId());
            PlayerCountLabel playerLabel = new PlayerCountLabel();
            child.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        loadGameWindowController.joinRoom(room.getGameId());
                    } catch (JoinRoomException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            this.gamesRow.getChildren().add(child);
            this.gamesRow.getChildren().add(playerLabel);

            VBox.setMargin(child, new Insets(0, 0, 5, 15));
        });

    }

    public void setScrollPane()
    {
        setGamesRow();
        this.scrollPane = new ScrollPane();
        this.scrollPaneVBox = new LobbyVBox(Pos.CENTER);
        this.scrollPaneVBox.getChildren().add(this.gamesRow);
        this.scrollPane  = new ScrollPane(this.scrollPaneVBox);
    }

    public void setTopVBox()
    {
        setScrollPane();
        this.topVBox = new LobbyVBox(Pos.CENTER);
        this.topVBox.getChildren().add(this.scrollPane);
    }

    public void setBottomVBox()
    {
        bottomVBox = new LobbyVBox(Pos.BOTTOM_LEFT);

        this.bottomVBox.setPrefHeight(Region.USE_COMPUTED_SIZE);
        this.bottomVBox.setVgrow(this.bottomVBox, Priority.ALWAYS);

        BackButton backFromLobbyButton = new BackButton();
        backFromLobbyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                loadGameWindowController.returnFromLoadGame();
            }
        });
        bottomVBox.getChildren().add(backFromLobbyButton);
    }



    @Override
    public Scene getScene(HashMap<String, Object> parameters) {

        setTopVBox();
        setBottomVBox();
        VBox root = new VBox();
        root.setAlignment(Pos.TOP_CENTER);
        root.setSpacing(20);
        root.getChildren().add(this.topVBox);
        root.getChildren().add(this.bottomVBox);
        return new Scene(root, 1000, 700);
    }
}
