package cn.kotliner.java.box;

import java.util.Map;

/**
 * Created by benny on 5/28/17.
 */
public interface BoxIf2 extends Map<Integer, String> {

    String get(int key);

    String get(Integer key);

}
