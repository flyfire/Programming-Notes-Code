package com.app.plugins.aspectj

import org.aspectj.bridge.IMessage
import org.aspectj.bridge.MessageHandler
import org.aspectj.tools.ajc.Main
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * @使用ajc编译java代码 ， 同时织入切片代码
 * 使用 AspectJ 的编译器（ajc，一个java编译器的扩展）
 * 对所有受 aspect 影响的类进行织入。
 * 在 gradle 的编译 task 中增加额外配置，使之能正确编译运行。
 */
public abstract class BaseAspectjPlugin implements Plugin<Project> {

    void apply(Project project) {

        //依赖aspectjrt
        project.dependencies {
            compile 'org.aspectj:aspectjrt:1.8.9'
        }

        final def log = project.logger
        log.error "========================";
        log.error "Aspectj切片开始编织Class!";
        log.error "========================";
        //开始
        def variants = getVariants()
        project.android[variants].all {
            variant ->
                def javaCompile = variant.javaCompile
                javaCompile.doLast {
                    //java编译后加入action
                    String[] args = [
                            "-showWeaveInfo",
                            "-1.8",
                            "-inpath", javaCompile.destinationDir.toString(),
                            "-aspectpath", javaCompile.classpath.asPath,
                            "-d", javaCompile.destinationDir.toString(),
                            "-classpath", javaCompile.classpath.asPath,
                            "-bootclasspath", project.android.bootClasspath.join(File.pathSeparator)]

                    log.debug "ajc args: " + Arrays.toString(args)

                    MessageHandler handler = new MessageHandler(true);

                    new Main().run(args, handler);//执行代码织入

                    for (IMessage message : handler.getMessages(null, true)) {
                        switch (message.getKind()) {
                            case IMessage.ABORT:
                            case IMessage.ERROR:
                            case IMessage.FAIL:
                                log.error message.message, message.thrown
                                break;
                            case IMessage.WARNING:
                                log.warn message.message, message.thrown
                                break;
                            case IMessage.INFO:
                                log.info message.message, message.thrown
                                break;
                            case IMessage.DEBUG:
                                log.debug message.message, message.thrown
                                break;
                        }
                    }
                }
        }
    }

    abstract def String getVariants()

}