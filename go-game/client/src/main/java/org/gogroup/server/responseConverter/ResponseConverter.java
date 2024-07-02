package org.gogroup.server.responseConverter;

import org.gogroup.exceptions.ResponseProcessingException;
import org.gogroup.server.Response;
import org.mjankoo.framework.requestHandler.Exception.RequestFormatException;

public interface ResponseConverter {
    public Response convert(String content) throws ResponseProcessingException;
}
