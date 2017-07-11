package com.ztiany.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ztiany.kotlin.anko.common.AnkoCommonActivity
import com.ztiany.kotlin.anko.common.DialogsActivity
import com.ztiany.kotlin.anko.common.LoggerActivity
import com.ztiany.kotlin.anko.common.intents.RawActivity
import com.ztiany.kotlin.anko.coroutines.CoroutinesActivity
import com.ztiany.kotlin.anko.layouts.AnkoLayoutsActivity
import org.jetbrains.anko.button
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.verticalLayout


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verticalLayout {
            button("anko Common") {
                onClick {
                    startActivity(intentFor<AnkoCommonActivity>())
                }
            }
            button("anko Common Intents") {
                onClick {
                    startActivity(intentFor<RawActivity>())
                }
            }
            button("anko Common Dialogs") {
                onClick {
                    startActivity(intentFor<DialogsActivity>())
                }
            }
            button("anko Common Logger") {
                onClick {
                    startActivity(intentFor<LoggerActivity>())
                }
            }
            button("Anko Layouts") {
                onClick {
                    startActivity(intentFor<AnkoLayoutsActivity>())
                }
            }
            button("Anko Coroutines") {
                onClick {
                    startActivity(intentFor<CoroutinesActivity>())
                }
            }
        }
    }


}
