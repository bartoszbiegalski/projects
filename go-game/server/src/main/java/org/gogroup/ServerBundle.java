package org.gogroup;

import org.mjankoo.framework.dependencyInjection.Bundle;

public class ServerBundle extends Bundle {
    @Override
    public String getServices() {
        return "services.yaml";
    }
}
