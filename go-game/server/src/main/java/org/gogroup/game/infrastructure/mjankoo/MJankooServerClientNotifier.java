package org.gogroup.game.infrastructure.mjankoo;

import org.gogroup.game.domain.notification.AbstractNotification;
import org.gogroup.game.domain.notification.ClientNotifier;
import org.mjankoo.framework.requestHandler.Server;

public class MJankooServerClientNotifier implements ClientNotifier
{
    private final Server server;

    public MJankooServerClientNotifier(Server server)
    {
        this.server = server;
    }

    @Override
    public void notify(String clientId, AbstractNotification notification)
    {
        System.out.println("asdasd");
        this.server.sendToClient(clientId, this.getConvertedNotification(notification));
    }

    private String getConvertedNotification(AbstractNotification notification) {
        return notification.toJson();
    }
}
