package com.ztiany.kotlin.extension

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *@author Ztiany
 *      Email: ztiany3@gmail.com
 *      Date : 2017-08-27 17:29
 */

@Parcelize
data class User(val firstName: String, val lastName: String) : Parcelable
