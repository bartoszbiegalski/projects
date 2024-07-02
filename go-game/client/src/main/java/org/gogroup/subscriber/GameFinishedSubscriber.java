package org.gogroup.subscriber;

import javafx.application.Platform;
import org.gogroup.WindowManager;
import org.gogroup.event.GameFinishedEvent;
import org.gogroup.windows.EndGameWindow;
import org.mjankoo.framework.eventDispatcher.EventSubscriber.EventSubscriberInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GameFinishedSubscriber implements EventSubscriberInterface
{
    private WindowManager windowManager;

    public GameFinishedSubscriber(WindowManager windowManager) {
        this.windowManager = windowManager;
    }
    @Override
    public HashMap<Class<?>, ArrayList<Object>> getSubscribedEvents() {
        return new HashMap<>() {{
            put(GameFinishedEvent.class, new ArrayList<>(
                    Arrays.asList("finishGame", 0)
            ));
        }};
    }

    public void finishGame(GameFinishedEvent event) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("hostPoints", event.getHostPoints());
        parameters.put("guestpoinst", event.getGuestpoints());
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                windowManager.show(EndGameWindow.class, parameters);
            }
        });
    }
}
