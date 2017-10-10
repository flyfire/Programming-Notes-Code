import org.gradle.api.Plugin
import org.gradle.api.Project

class SRCPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println "===================================="
        println "SRCPlugin run"
        println "===================================="

        project.extensions.create("srcPlugin", Info)
        project.task("printInfo").doLast {
            println "===================================="
            def info = project['srcPlugin']
            println info.address
            println info.name
            println "===================================="
        }
    }
}

class Info {
    def address;
    def name;
}
