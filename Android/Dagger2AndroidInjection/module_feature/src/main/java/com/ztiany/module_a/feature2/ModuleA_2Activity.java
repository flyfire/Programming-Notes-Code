package com.ztiany.module_a.feature2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ztiany.module_a.databinding.ModuleA2ActivityBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-05-22 18:20
 */
public class ModuleA_2Activity extends AppCompatActivity {

    @Inject
    ModuleA_2ViewModel mModuleA_2ViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        ModuleA2ActivityBinding moduleA2ActivityBinding = ModuleA2ActivityBinding.inflate(getLayoutInflater());
        setContentView(moduleA2ActivityBinding.getRoot());
        moduleA2ActivityBinding.setView(this);
        moduleA2ActivityBinding.setViewmodel(mModuleA_2ViewModel);
        mModuleA_2ViewModel.start();
    }


}
