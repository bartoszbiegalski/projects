package org.gogroup.server;

import org.gogroup.command.AbstractCommand;
import org.gogroup.exceptions.ResponseProcessingException;
import org.gogroup.server.responseConverter.ResponseConverter;

import java.io.IOException;

public class ServerConnector {
    private final Client client;

    private final ResponseConverter responseConverter;

    public ServerConnector(Client client, ResponseConverter responseConverter)
    {
        this.client = client;
        this.responseConverter = responseConverter;

        try {
            client.connect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Response send(AbstractCommand command) {
        this.client.write(command.toJSON());
        this.client.setWaitingForResponse(true);

        String response = null;
        while (response == null)
        {
            response = this.client.read();
        }
        this.client.setWaitingForResponse(false);
        try {
            return this.responseConverter.convert(response);
        } catch (ResponseProcessingException e) {
            throw new RuntimeException("Failed to process response because: " + e.getMessage());
        }
    }
}
