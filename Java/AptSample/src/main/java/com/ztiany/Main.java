package com.ztiany;

import com.ztiany.runtimeannotation.ClassInfo;
import com.ztiany.runtimeannotation.User;

public class Main {

    public static void main(String... args) {
        processUserAnnotation(new User());
    }

    private static void processUserAnnotation(Object object) {
        if (object instanceof User) {
            User user = (User) object;

            ClassInfo annotation = user.getClass().getAnnotation(ClassInfo.class);

            if (annotation != null) {
                System.out.println(annotation.author());
                System.out.println(annotation.version());
                System.out.println(annotation.createData());
            }
        }
    }


}
