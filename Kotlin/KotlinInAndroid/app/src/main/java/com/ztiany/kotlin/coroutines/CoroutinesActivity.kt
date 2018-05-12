package com.ztiany.kotlin.coroutines

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.ztiany.kotlin.R

/**
 *参考代码：
 * <pre>
 *     https://github.com/dmytrodanylyk/coroutine-recipes
 * </pre>
 *
 *@author Ztiany
 *      Email: ztiany3@gmail.com
 *      Date : 2018-05-04 17:33
 */
class CoroutinesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fl_content, LaunchCoroutinesFragment())
                    .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        return super.onCreateOptionsMenu(menu)
    }

}