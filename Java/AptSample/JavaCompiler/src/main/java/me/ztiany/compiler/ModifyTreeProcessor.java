package me.ztiany.compiler;

import com.sun.source.util.Trees;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Names;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * @author ztiany
 * Email: ztiany3@gmail.com
 */
@SupportedSourceVersion(SourceVersion.RELEASE_7)
@SupportedAnnotationTypes({
        "me.ztiany.compiler.HelloTag"
})
public class ModifyTreeProcessor extends AbstractProcessor {

    private JavacProcessingEnvironment env;
    private Trees trees;
    private TreeMaker treeMaker;
    private Names names;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.trees = Trees.instance(processingEnv);
        this.env = (JavacProcessingEnvironment) processingEnv;
        this.treeMaker = TreeMaker.instance(env.getContext());
        this.names = Names.instance(env.getContext());
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (roundEnv.processingOver()) {
            return true;
        }

        for (Element element : roundEnv.getElementsAnnotatedWith(HelloTag.class)) {

            //com.sun.tools.javac.code.Symbol$MethodSymbol
            System.out.println(element.getClass());

            if (!(element instanceof ExecutableElement)) {
                continue;
            }

            ExecutableElement executableElement = (ExecutableElement) element;
            JCTree.JCMethodDecl methodDecl = (JCTree.JCMethodDecl) trees.getTree(executableElement);
            System.out.println(methodDecl);
        }

        return true;
    }

}
