package com.lam.quan.intuitrepo.api

import com.lam.quan.intuitrepo.model.Issue
import com.lam.quan.intuitrepo.model.Repo
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    /**
     * Fetch Intuit Repos from Github
     */
    @GET("/users/{name}/repos")
    fun fetchIntuitRepos(@Path("name") name: String): Observable<List<Repo>>

    /**
     * Fetch issues list from Intuit repo with @name
     */
    @GET("/repos/{ownerName}/{repoName}/issues")
    fun fetchIntuitRepoIssues(@Path("ownerName") ownerName:String,
                              @Path("repoName") repoName:String): Observable<List<Issue>>

    /**
     * Provide Implementation of Github API to any one who would depends on it.
     */
    companion object {
        fun create(): GithubApi {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl("https://api.github.com/")
                .build()

            return retrofit.create(GithubApi::class.java)
        }
    }
}