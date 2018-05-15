package com.ztiany.acc.data

import android.app.Application
import android.arch.persistence.room.Room
import com.ztiany.acc.data.adapter.LiveDataCallAdapterFactory
import com.ztiany.acc.data.api.GithubService
import com.ztiany.acc.data.db.GithubDb
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 *
 *@author Ztiany
 *      Email: ztiany3@gmail.com
 *      Date : 2018-04-28 16:40
 */

private val dbCache = HashMap<String, GithubDb>()

private val service: GithubService = Retrofit.Builder()
        .baseUrl("http://gank.io/api/data/all/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(LiveDataCallAdapterFactory())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()
        .create(GithubService::class.java)

fun provideGithubService(): GithubService {
    return service
}

fun provideDb(app: Application, dbName: String): GithubDb {
    return if (dbName in dbCache.keys) {
        dbCache[dbName]!!
    } else {
        val db = Room.databaseBuilder(app, GithubDb::class.java, dbName).build()
        dbCache[dbName] = db
        db
    }
}

