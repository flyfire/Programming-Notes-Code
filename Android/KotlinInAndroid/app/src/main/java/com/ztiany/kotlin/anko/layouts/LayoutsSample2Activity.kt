package com.ztiany.kotlin.anko.layouts

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ztiany.kotlin.R
import kotlinx.android.synthetic.main.activity_anko_layouts2.*
import kotlinx.coroutines.experimental.android.UI
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast

class LayoutsSample2Activity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anko_layouts2)

        anko_layouts_tv_name.text = "Click me"
        //todo
        anko_layouts_tv_name.onClick(UI) {
            toast(" I am TextView")
        }

    }
}
