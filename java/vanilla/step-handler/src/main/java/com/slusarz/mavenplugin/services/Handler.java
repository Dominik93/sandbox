package com.slusarz.mavenplugin.services;

public interface Handler<P, R> {

    R handle(P params);

}
