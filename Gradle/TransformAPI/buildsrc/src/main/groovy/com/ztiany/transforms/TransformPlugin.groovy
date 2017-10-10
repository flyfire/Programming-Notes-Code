import com.android.build.gradle.AppExtension
import com.ztiany.transforms.PrintTransform
import org.gradle.api.Plugin
import org.gradle.api.Project

class TransformPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        def android = project.extensions.findByType(AppExtension)
        android.registerTransform(new PrintTransform(project))
    }

}