package org.gogroup.server.notificationReader;

import org.gogroup.server.Client;
import org.mjankoo.framework.eventDispatcher.EventDispatcherInterface;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotificationReader
{
    private final Client client;

    private final EventDispatcherInterface eventDispatcher;
    public NotificationReader(Client client, EventDispatcherInterface eventDispatcher)
    {
        this.client = client;
        this.eventDispatcher = eventDispatcher;
        this.run();
    }

    public void run() {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Runnable worker = new Thread(
                new NotificationReaderWorker(this.client, this.eventDispatcher)
        );
        executorService.execute(worker);
    }
}
