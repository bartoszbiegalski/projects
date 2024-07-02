package org.gogroup.game.domain.notification;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public abstract class AbstractNotification
{
    public String toJson() {
        ObjectWriter objectWriter = new ObjectMapper().writer();
        try {
            return objectWriter.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Json proccessing exception.");
        }
    }
}
