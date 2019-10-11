package com.lam.quan.intuitrepo

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lam.quan.intuitrepo.api.GithubApi

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class GithubApiTest {
    @Test
    fun createApiObject() {
        val githubApi:GithubApi = GithubApi.create()
        assertNotNull(githubApi)
    }
}
