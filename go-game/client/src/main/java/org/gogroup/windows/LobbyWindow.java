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
import org.gogroup.components.menuComponents.MenuButton;
import org.gogroup.components.menuComponents.RoomRow;
import org.gogroup.controllers.LobbyController;
import org.gogroup.exceptions.JoinRoomException;
import org.gogroup.exceptions.NewGameFailedException;
import org.gogroup.model.Room;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class LobbyWindow extends AbstractWindow {
    private LobbyController lobbyController;
    private ScrollPane scrollPane;
    private RoomRow roomRow;
    private LobbyVBox topVBox;
    private LobbyVBox bottomVBox;
    private LobbyVBox scrollPaneVBox;
    private ArrayList<Room> rooms;

    public LobbyWindow(LobbyController lobbyController)
    {
        this.lobbyController = lobbyController;
    }

    public void setRooms()
    {
        this.rooms = this.lobbyController.loadRooms();
    }

    public void setRoomRow()
    {
        setRooms();
        this.roomRow = new RoomRow("lala", 1);
        this.roomRow.setAlignment(Pos.CENTER);
        this.rooms.forEach(room -> {
            LobbyButton child = new LobbyButton("Room" + room.getGameId());
            //PlayerCountLabel playerLabel = new PlayerCountLabel();
            child.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        lobbyController.joinRoom(room.getGameId());
                    } catch (JoinRoomException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            this.roomRow.getChildren().add(child);
            //this.roomRow.getChildren().add(playerLabel);

            VBox.setMargin(child, new Insets(0, 0, 5, 15));
        });

    }

    public void setScrollPane()
    {
        setRoomRow();
        this.scrollPane = new ScrollPane();
        this.scrollPaneVBox = new LobbyVBox(Pos.CENTER);
        this.scrollPaneVBox.getChildren().add(this.roomRow);
        this.scrollPane  = new ScrollPane(this.scrollPaneVBox);
    }

    public void setTopVBox()
    {
        setScrollPane();
        this.topVBox = new LobbyVBox(Pos.CENTER);
        this.topVBox.getChildren().add(this.scrollPane);
        this.topVBox.getChildren().add(this.createNewGameButton());
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
                try {
                    lobbyController.returnFromLobby();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        bottomVBox.getChildren().add(backFromLobbyButton);
    }

    @Override
    public Scene getScene(HashMap<String, Object> parameters)
    {
        setTopVBox();
        setBottomVBox();


        VBox root = new VBox();
        root.setAlignment(Pos.TOP_CENTER);
        root.setSpacing(20);
        root.getChildren().add(this.topVBox);
        root.getChildren().add(this.bottomVBox);
        return new Scene(root, 1000, 700);
    }

    //public


    private MenuButton createNewGameButton() {
        MenuButton button = new MenuButton("Create new game");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    lobbyController.createNewRoom();
                } catch (NewGameFailedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return button;
    }
}
