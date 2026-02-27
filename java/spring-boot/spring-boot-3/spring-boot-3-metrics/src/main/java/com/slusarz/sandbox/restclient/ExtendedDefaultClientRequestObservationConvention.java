package com.slusarz.sandbox.restclient;

import com.slusarz.sandbox.headers.HeaderHolder;
import io.micrometer.common.KeyValue;
import io.micrometer.common.KeyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.observation.ClientRequestObservationContext;
import org.springframework.http.client.observation.DefaultClientRequestObservationConvention;
import org.springframework.stereotype.Component;

@Component
public class ExtendedDefaultClientRequestObservationConvention extends DefaultClientRequestObservationConvention {

    @Autowired
    private HeaderHolder headerHolder;

    @Override
    public KeyValues getLowCardinalityKeyValues(ClientRequestObservationContext context) {
        // here, we just want to have an additional KeyValue to the observation, keeping the default values
        return super.getLowCardinalityKeyValues(context).and(additionalTags());
    }

    protected KeyValues additionalTags() {
        KeyValues keyValues = KeyValues.empty();
        keyValues = keyValues.and(KeyValue.of("client.tag", headerHolder.get().getOrDefault("X-TAG", "none")));
        return keyValues;
    }


}
