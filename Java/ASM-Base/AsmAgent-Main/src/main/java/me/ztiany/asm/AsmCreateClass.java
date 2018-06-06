package me.ztiany.asm;

import org.objectweb.asm.ClassWriter;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

/**
 * @author ztiany
 * Email: ztiany3@gmail.com
 */
public class AsmCreateClass {

    public static void main(String... args) throws ClassNotFoundException {
        final byte[] aClass = createClass();

        Class exampleClass = new ClassLoader() {
            @SuppressWarnings("unchecked")
            protected Class findClass(String name) {
                return defineClass(name, aClass, 0, aClass.length);
            }
        }.loadClass("pkg.Comparable");

        System.out.println(exampleClass);
    }

    private static byte[] createClass() {
        /*
        package pkg;
        public interface Comparable  {
            int LESS = -1;
            int EQUAL = 0;
            int GREATER = 1;
            int compareTo(Object o);
        }
         */
        ClassWriter cw = new ClassWriter(0);

        cw.visit(V1_5, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE,
                "pkg/Comparable",
                null,
                "java/lang/Object",
                null);

        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "LESS", "I",
                null, -1).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "EQUAL", "I",
                null, 0).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "GREATER", "I",
                null, 1).visitEnd();

        cw.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "compareTo",
                "(Ljava/lang/Object;)I", null, null).visitEnd();

        cw.visitEnd();

        return cw.toByteArray();
    }


}

