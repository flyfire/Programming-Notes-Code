package com.example

import groovy.transform.ToString
import org.gradle.api.Plugin
import org.gradle.api.Project

class SRCPlugin implements Plugin<Project> {

    private String compileModule

    SRCPlugin() {
        println "===================================="
        println("SRCPlugin initialized ${this}")
    }

    @Override
    void apply(Project project) {
        //应该在task运行期获取Info中的内容
        project.extensions.create("srcPlugin", Info)

        println(project.name)
        println(project.path)
        println "taskNames =${project.gradle.startParameter.taskNames.toString()}"
        String currentModule = project.path.replace(":", "")
        println "current module is ${currentModule}"

        AssembleTask assembleTask = getTaskInfo(project.gradle.startParameter.taskNames)
        println("assembleTask-->$assembleTask")
        fetchMainModuleName(assembleTask)

        println("compileModule = $compileModule")

        boolean isRunAsApp = true
        if (project.name != 'app' && project.name != compileModule) {
            isRunAsApp = false
        }

        project.metaClass.RunAsApp = isRunAsApp
        println("isRunAsApp = $isRunAsApp")
        println "===================================="

        if (isRunAsApp) {

            project.apply(plugin: 'com.android.application')
            project.android.defaultConfig.applicationId = "com.ztiany.build.src.${project.name}"
            println("depProject -------------------------start")

            if (project.hasProperty("depends")) {
                String depends = project.property("depends")
                String[] dependsArr = depends.split(',')
                for (String depend : dependsArr) {
                    println(depend)
                    project.dependencies.add('implementation', project.project(":$depend"))
                }
            }
            println("depProject -------------------------end")

        } else {
            project.apply(plugin: 'com.android.library')
        }
    }

    @ToString
    private class AssembleTask {
        boolean isAssemble = false
        boolean isDebug = false
        List<String> modules = new ArrayList<>()
    }


    private AssembleTask getTaskInfo(List<String> taskNames) {
        AssembleTask assembleTask = new AssembleTask()
        println("getTaskInfo--------------------------------------------->start")
        for (String task : taskNames) {
            println("task " + task)
            if (task.toLowerCase().contains("assemble")/*打包*/
                    || task.contains("aR")/*打Release包*/
                    || task.toLowerCase().contains("tinker")/*微信热修复*/
                    || task.toLowerCase().contains("install")/*安装*/
                    || task.toLowerCase().contains("build")/*构造*/
                    || task.toLowerCase().contains("resguard")) /*微信资源压缩*/ {

                if (task.toLowerCase().contains("debug")) {
                    assembleTask.isDebug = true
                }
                assembleTask.isAssemble = true
                //:app:assemble->[app, assemble]->app
                String[] strArr = task.split(":")
                println("task arr" + strArr)
                if (strArr.length > 1) {
                    assembleTask.modules.add(strArr[strArr.length - 2])
                }
                break
            }
        }
        println("getTaskInfo--------------------------------------------->end")
        return assembleTask
    }


    private void fetchMainModuleName(AssembleTask assembleTask) {
        if (assembleTask.modules.size() > 0 &&
                assembleTask.modules.get(0) != null &&
                assembleTask.modules.get(0).trim().length() > 0) {
            compileModule = assembleTask.modules.get(0)
        } else {
            compileModule = "app"
        }
    }
}




