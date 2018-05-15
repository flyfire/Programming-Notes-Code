package com.ztiany.acc.data.api

import android.arch.lifecycle.LiveData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("{size}/{page}")
    fun searchGank(@Path("size") size: String, @Path("page") page: String): LiveData<ApiResponse<RepoSearchResponse>>

    /**
     * page start with 1
     */
    @GET("{size}/{page}")
    fun searchGankCall(@Path("size") size: String, @Path("page") page: String): Call<RepoSearchResponse>

}
