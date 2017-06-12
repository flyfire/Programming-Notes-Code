package com.ztiany.module_a.feature1;

import dagger.Binds;
import dagger.Module;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-05-22 18:41
 */
@Module
public abstract class ModuleA_1Module {

    @Binds
    abstract ModuleA_1View provideModuleA_1View(ModuleA_1Activity a_1Activity);
}
