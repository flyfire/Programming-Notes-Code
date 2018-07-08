package cn.kotliner.kotlin.kapt

import cn.kotliner.kotlin.kapt.module.RetrofitModule
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by benny on 12/11/16.
 */
@Singleton
@Component(modules = arrayOf(RetrofitModule::class))
interface RESTFulComponent {
    fun retrofit(): Retrofit
}