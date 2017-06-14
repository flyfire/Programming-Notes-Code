package com.ztiany.androidarchitecturecomponent.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.ztiany.androidarchitecturecomponent.R
import com.ztiany.androidarchitecturecomponent.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycle.addObserver(LifecycleObserverMain())
        val mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainViewModel.start()
    }

}
