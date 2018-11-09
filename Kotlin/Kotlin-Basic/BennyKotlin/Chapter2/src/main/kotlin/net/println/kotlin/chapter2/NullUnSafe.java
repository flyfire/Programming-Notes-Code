package net.println.kotlin.chapter2;

/**
 * Created by benny on 2/26/17.
 */
public class NullUnSafe {
    public static void main(String... args) {
        String name = getName();
        if(name == null){
            System.out.println("name is invalid!!");
        }else{
            System.out.println(name.length());
        }
    }

    public static String getName(){
        return "";
    }
}
