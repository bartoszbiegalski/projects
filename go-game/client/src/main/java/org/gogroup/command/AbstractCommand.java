package org.gogroup.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.util.HashMap;

public abstract class AbstractCommand
{
    public abstract String toJSON();

    protected String hashMapToJson(HashMap<String, Object> map)
    {
        ObjectWriter objectWriter = (new ObjectMapper()).writer();

        try {
            return objectWriter.writeValueAsString(map);
        } catch (JsonProcessingException var4) {
            throw new RuntimeException("Json proccessing exception.");
        }
    }
}
