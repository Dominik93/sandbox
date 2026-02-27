package com.slusarz.sandbox.server;

import com.slusarz.sandbox.headers.HeaderHolder;
import io.micrometer.common.KeyValue;
import io.micrometer.common.KeyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.observation.DefaultServerRequestObservationConvention;
import org.springframework.http.server.observation.ServerRequestObservationContext;
import org.springframework.stereotype.Component;


@Component
public class ExtendedServerRequestObservationConvention extends DefaultServerRequestObservationConvention {

    @Autowired
    private HeaderHolder headerHolder;

    @Override
    public KeyValues getLowCardinalityKeyValues(ServerRequestObservationContext context) {
        // here, we just want to have an additional KeyValue to the observation, keeping the default values
        return super.getLowCardinalityKeyValues(context).and(custom());
    }

    private KeyValue custom() {
        return KeyValue.of("server.tag", headerHolder.get().getOrDefault("X-TAG", "none"));
    }

}
