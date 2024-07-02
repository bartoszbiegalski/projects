package org.gogroup;

import org.gogroup.gameRetrospective.domain.Move;
import org.gogroup.gameRetrospective.infrastructure.hibernate.HibernateMoveRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.mjankoo.framework.dependencyInjection.Application;
import org.mjankoo.framework.dependencyInjection.Container;
import org.mjankoo.framework.eventDispatcher.EventDispatcherBundle;
import org.mjankoo.framework.requestHandler.RequestHandlerBundle;
import org.mjankoo.framework.requestHandler.Server;

public class Main {
    public static void main(String[] args) 
    {
        Application application = new Application();
        application.registerBundle(new EventDispatcherBundle());
        application.registerBundle(new RequestHandlerBundle());
        application.registerBundle(new ServerBundle());
        Container container = application.run();

        Server server = (Server) container.get("org.mjankoo.framework.requestHandler.Server");
        server.run();
    }
}
