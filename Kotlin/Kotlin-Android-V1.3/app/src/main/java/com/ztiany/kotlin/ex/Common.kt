package com.ztiany.kotlin.ex

/**
 *@author Ztiany
 *      Email: ztiany3@gmail.com
 *      Date : 2018-11-09 17:53
 */
fun <T> T?.ifNull(action: () -> Unit) {
    if (this == null) {
        action.invoke()
    }
}