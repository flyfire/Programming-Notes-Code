package me.ztiany.asm.creator;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class BuildClass extends ClassLoader implements Opcodes {

    public static void main(String[] args) throws IOException {

        // 定义一个类
        ClassWriter classWriter = new ClassWriter(0);
        classWriter.visit(V1_8, ACC_PUBLIC, "Example", null, "java/lang/Object", null);

        // 生成默认的构造方法
        MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        // 生成构造方法的字节码指令
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        methodVisitor.visitInsn(RETURN);
        methodVisitor.visitMaxs(1, 1);
        methodVisitor.visitEnd();

        // 生成main方法
        methodVisitor = classWriter.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        // 生成main方法的字节码指令
        methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        methodVisitor.visitLdcInsn("Hello world!");
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        methodVisitor.visitInsn(RETURN);
        methodVisitor.visitMaxs(2, 2);

        // 字节码生成 完成
        methodVisitor.visitEnd();

        // 获取生成的的class文件对应的二进制流
        byte[] code = classWriter.toByteArray();

        // 将二进制流写到本地磁盘上
        FileOutputStream fos = new FileOutputStream("Example.class");
        fos.write(code);
        fos.flush();
        fos.close();

        // 直接将二进制流加载到内存中
        BuildClass buildClass = new BuildClass();
        Class<?> example = buildClass.defineClass("Example", code, 0, code.length);
        try {
            example.getMethods()[0].invoke(null, new Object[]{null});
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
} 