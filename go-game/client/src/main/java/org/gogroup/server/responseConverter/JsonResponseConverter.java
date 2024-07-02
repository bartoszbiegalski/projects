package org.gogroup.server.responseConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.gogroup.exceptions.ResponseProcessingException;
import org.gogroup.server.Response;

import java.util.Map;

public class JsonResponseConverter implements ResponseConverter
{
    @Override
    public Response convert(String content) throws ResponseProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> responseMap;
        try {
            responseMap = mapper.readValue(content, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Jsom");
        }

        int statusCode = this.getStatusCode(responseMap);
        Map<String, Object> body = this.getBody(responseMap);

        return new Response(statusCode, body);
    }

    public int getStatusCode(Map<String, Object> responseMap) throws ResponseProcessingException {
        Object statusCode = responseMap.getOrDefault("statusCode", null);
        if (!(statusCode instanceof Integer)) {
            throw ResponseProcessingException.fromProperty("statusCode", statusCode.getClass(), Map.class);
        }

        return (Integer) statusCode;
    }

    public Map<String, Object> getBody(Map<String, Object> responseMap) throws ResponseProcessingException {
        Object body = responseMap.getOrDefault("body", null);
        if (body == null) {
            return null;
        }

        if (!(body instanceof Map)) {
            throw ResponseProcessingException.fromProperty("body", body.getClass(), Map.class);
        }

        return (Map<String, Object>) body;
    }
}
