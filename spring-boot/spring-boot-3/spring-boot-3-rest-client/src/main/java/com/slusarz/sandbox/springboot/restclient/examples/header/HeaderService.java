package com.slusarz.sandbox.springboot.restclient.examples.header;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;

public interface HeaderService {
    @GetExchange("/header/default")
    String defaultHeader();

    @GetExchange("/header/custom")
    String customHeader(@RequestHeader("X-CUSTOM-SAMPLE") String value);
}
