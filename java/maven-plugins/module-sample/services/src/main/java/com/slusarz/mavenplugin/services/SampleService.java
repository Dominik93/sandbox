package com.slusarz.mavenplugin.services;

import com.slusarz.annotation.SampleAnnotation;


/**
 * Lorem ipsum
 */
@SampleAnnotation
public interface SampleService {


    /**
     * Lorem ipsum
     * @param index
     * @return
     * @throws Exception
     */
    String getValue(int index) throws Exception;

}
