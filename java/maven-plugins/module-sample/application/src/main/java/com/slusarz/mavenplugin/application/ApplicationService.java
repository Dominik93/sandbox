package com.slusarz.mavenplugin.application;

import com.slusarz.annotation.SampleAnnotation;


/**
 * Lorem ipsum
 */
@SampleAnnotation
public interface ApplicationService {


    /**
     * Lorem ipsum
     * @param index
     * @return
     * @throws Exception
     */
    String getValue(int index) throws Exception;

}
