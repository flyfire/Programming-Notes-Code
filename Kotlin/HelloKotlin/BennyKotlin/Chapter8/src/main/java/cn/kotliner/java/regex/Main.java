package cn.kotliner.java.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by benny on 5/28/17.
 */
public class Main {
    public static void main(String... args) {
        String source = "Hello, This my phone number: 010-12345678. ";
        String pattern = ".*(\\d{3}-\\d{8}).*";
        Matcher matcher = Pattern.compile(pattern).matcher(source);

        while(matcher.find()){
            System.out.println(matcher.group());
            System.out.println(matcher.group(1));
        }
    }
}
