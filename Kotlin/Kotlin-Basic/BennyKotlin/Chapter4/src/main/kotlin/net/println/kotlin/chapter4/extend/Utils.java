package net.println.kotlin.chapter4.extend;

/**
 * Created by benny on 4/4/17.
 */
public class Utils {
    public static boolean isEmpty(String[] args){
        return args.length == 0;
    }

    public static boolean isNotEmpty(String[] args){
        return args.length > 0;
    }
}
