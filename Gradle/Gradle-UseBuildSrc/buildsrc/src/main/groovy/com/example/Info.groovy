package com.example

import groovy.transform.ToString

/**
 * @author Ztiany
 * Email: ztiany3@gmail.com
 * Date : 2018-04-10 19:32
 */
@ToString
class Info {

    String src_id

    public List<String> info = new ArrayList<>()

    void addInfoList(String... infoArr) {
        info.addAll(infoArr)
    }

}
