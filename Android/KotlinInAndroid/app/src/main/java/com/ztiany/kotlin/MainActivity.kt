package com.ztiany.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.ztiany.kotlin.anko.common.AnkoCommonActivity
import com.ztiany.kotlin.anko.common.DialogsActivity
import com.ztiany.kotlin.anko.common.LoggerActivity
import com.ztiany.kotlin.anko.common.intents.RawActivity
import org.jetbrains.anko.intentFor


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun ankoCommonIntents(view: View) {
        startActivity(intentFor<RawActivity>())
    }

    fun ankoCommonDialogs(view: View) {
        startActivity(intentFor<DialogsActivity>())
    }

    fun ankoCommonLogger(view: View) {
        startActivity(intentFor<LoggerActivity>())
    }

    fun ankoCommon(view: View) {
        startActivity(intentFor<AnkoCommonActivity>())
    }
}
