package cn.kotliner.kotlin.kapt.module

import dagger.Module
import dagger.Provides
import retrofit2.CallAdapter
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import javax.inject.Singleton

/**
 * Created by benny on 12/11/16.
 */
@Module
class RxAdapterModule {
    @Singleton @Provides fun adapter(): CallAdapter.Factory = RxJavaCallAdapterFactory.create()
}
