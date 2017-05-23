package com.ztiany.main.binding;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ztiany.main.databinding.ModuleMainActivityBindingBinding;


/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-05-22 18:35
 */
public class BindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ModuleMainActivityBindingBinding moduleMainActivityBindingBinding = ModuleMainActivityBindingBinding.inflate(getLayoutInflater());
        setContentView(moduleMainActivityBindingBinding.getRoot());
        moduleMainActivityBindingBinding.setView(this);
        moduleMainActivityBindingBinding.setViewmodel(new BindingViewModel());
    }
}
