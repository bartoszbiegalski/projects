package org.gogroup;

import javafx.stage.Stage;
import org.gogroup.windows.AbstractWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class WindowManager {
    private ArrayList<AbstractWindow> windows;
    private Stage stage;

    public WindowManager(ArrayList<AbstractWindow> windows)
    {
        this.windows = windows;
    }

    public void show(Class windowClass) {
        this.loadWindow(windowClass, new HashMap<>());
    }

    public void show(Class window, HashMap<String, Object> parameters) {
        this.loadWindow(window, parameters);
    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
    }

    public void loadWindow(Class window, HashMap<String, Object> parameters) {
        this.windows.forEach(currentWindow -> {
            if(currentWindow.getClass().equals(window)) {
                this.stage.setScene(currentWindow.getScene(parameters));
                this.stage.setResizable(false);
                this.stage.show();
            }
        });
    }


}
