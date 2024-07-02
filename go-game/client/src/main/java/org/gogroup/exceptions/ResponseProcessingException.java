package org.gogroup.exceptions;

import org.mjankoo.framework.requestHandler.Exception.RequestPropertyException;

public class ResponseProcessingException extends Throwable implements GoGameException
{
    public ResponseProcessingException(String message) {
        super(message);
    }

    public static ResponseProcessingException fromProperty(String propertyName, Class<?> expectedClass, Class<?> providedClass) {
        String message = String.format(
                "Property '%s' should be of type '%s', '%s' provided.",
                propertyName,
                expectedClass.toString(),
                providedClass.toString()
        );
        return new ResponseProcessingException(message);
    }
}
