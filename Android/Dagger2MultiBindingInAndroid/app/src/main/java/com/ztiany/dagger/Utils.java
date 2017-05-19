package com.ztiany.dagger;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-05-17 17:14
 */
public class Utils {


    public String getMessage(Object object) {
        return object.getClass().getName() + " installed";
    }
}
