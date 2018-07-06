package net.println.kotlin.chapter9

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.relativeLayout
import org.jetbrains.anko.textView

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        relativeLayout {
            textView(intent.extras["Key"]?.toString()?: "Hello") {

            }.lparams(matchParent, matchParent){
                gravity = Gravity.CENTER
            }
        }
    }
}
