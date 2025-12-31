package com.slusarz.mavenplugin.services;

import java.util.ArrayList;
import java.util.List;

public class StepHandler {
    public List<Handler> handlers = new ArrayList<>();

    public void add(Handler handler) {
        this.handlers.add(handler);
    }

    public <R, P> R execute(final P args) {
        Object params = args;
        for (final Handler handler : this.handlers) {
            params = handler.handle(params);
        }
        return (R) params;
    }

}
