package org.gogroup.server;

import java.util.Map;

public class Response {
    private final int statusCode;

    private final Map<String, Object> body;

    public Response(int statusCode, Map<String, Object> body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public int getStatusCode()
    {
        return this.statusCode;
    }

    public Map<String, Object> getBody()
    {
        return body;
    }
}
