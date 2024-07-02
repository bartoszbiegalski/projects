package org.gogroup;

import javafx.stage.Stage;
import org.gogroup.windows.MenuWindow;
import org.mjankoo.framework.dependencyInjection.Application;
import org.mjankoo.framework.dependencyInjection.Container;
import org.mjankoo.framework.eventDispatcher.EventDispatcherBundle;


public class Main extends javafx.application.Application {

    public static void main(String[] args) 
    {
       launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Application application = new Application();
        application.registerBundle(new EventDispatcherBundle());
        application.registerBundle(new ClientBundle());
        Container container = application.run();

        WindowManager windowManager = (WindowManager) container.get("org.gogroup.WindowManager");


        windowManager.setStage(stage);
        windowManager.show(MenuWindow.class);
    }
}
