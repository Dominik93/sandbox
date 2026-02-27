package com.slusarz.sandbox.headers;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HeaderHolder {

    private final ThreadLocal<Map<String, String>> holder = ThreadLocal.withInitial(HashMap::new);

    public void set(Map<String, String> headers) {
        holder.get().putAll(headers);
    }

    public Map<String, String> get() {
        return holder.get();
    }

}
