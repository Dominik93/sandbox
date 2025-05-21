package com.slusarz.sandbox.springboot.restclient.examples.error;

import org.springframework.web.service.annotation.GetExchange;

public interface ErrorService {
    @GetExchange("/error/422")
    String get422Error();
    @GetExchange("/error/404")
    String get404Error();

}
