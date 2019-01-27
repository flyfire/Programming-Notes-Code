package rx2

import io.reactivex.Observable
import io.reactivex.Single
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.rx2.await
import kotlinx.coroutines.rx2.awaitFirst
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

/* https://github.com/Kotlin/kotlinx.coroutines/blob/master/reactive/kotlinx-coroutines-rx-example/src/main.kt */

interface GitHub {
    @GET("/repos/{owner}/{repo}/contributors")
    fun contributors(
            @Path("owner") owner: String,
            @Path("repo") repo: String
    ): Observable<List<Contributor>>

    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Single<List<Repo>>
}

data class Contributor(val login: String, val contributions: Int)
data class Repo(val name: String)

fun main() = runBlocking {

    println("Making GitHub API request")

    val retrofit = Retrofit.Builder().apply {
        baseUrl("https://api.github.com")
        addConverterFactory(GsonConverterFactory.create())
        addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }.build()

    val github = retrofit.create(GitHub::class.java)


    val launch = launch {

        val contributors = github.contributors("JetBrains", "Kotlin")
                .delay(60, TimeUnit.MILLISECONDS)
                .doOnNext {
                    println(it)
                }
                .doOnDispose {
                    println("doOnDispose")
                }
                .doAfterTerminate {
                    println("doAfterTerminate")
                }
                .awaitFirst()
                .take(10)

        println("contributors = $contributors")

        for ((name, contributions) in contributors) {
            println("$name has $contributions contributions, other repos: ")
            val otherRepos = github.listRepos(name).await().map(Repo::name).joinToString(", ")
            println(otherRepos)
        }

    }

    println("end...")
    delay(50)
    launch.cancel()
    delay(3000)
}