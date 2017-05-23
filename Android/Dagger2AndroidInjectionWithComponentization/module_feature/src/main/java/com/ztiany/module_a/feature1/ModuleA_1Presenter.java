package com.ztiany.module_a.feature1;

import javax.inject.Inject;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-05-22 18:23
 */
public class ModuleA_1Presenter {

    private final ModuleA_1View mModuleA_1View;

    @Inject
    public ModuleA_1Presenter(ModuleA_1View moduleA_1View) {
        mModuleA_1View = moduleA_1View;
    }

    void start() {
        mModuleA_1View.showMessage("ModuleA_1Presenter started");
    }

}
