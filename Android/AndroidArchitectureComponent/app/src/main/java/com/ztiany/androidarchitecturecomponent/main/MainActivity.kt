package com.ztiany.androidarchitecturecomponent.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ztiany.androidarchitecturecomponent.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        lifecycle.addObserver(LifecycleObserverMain())
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainViewModel.start()
    }

}
