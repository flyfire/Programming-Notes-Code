package com.ztiany.kotlin.anko.coroutines

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 *@author Ztiany
 *      Email: ztiany3@gmail.com
 *      Date : 2017-07-11 23:02
 */
//todo
class CoroutinesActivity : AppCompatActivity(), AnkoLogger {


    private lateinit var frameLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        frameLayout = verticalLayout {
            textView {

            }
            button("click me") {
                onClick {
                    toast("I am a Button")
                }
            }
        }
    }

}