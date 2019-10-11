package com.lam.quan.intuitrepo

import android.os.Bundle
import com.lam.quan.intuitrepo.model.Issue
import com.lam.quan.intuitrepo.model.Repo
import com.lam.quan.intuitrepo.ui.repolist.RepoListViewModel
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner


@Suppress("DEPRECATION")
@RunWith(RobolectricTestRunner::class)
class RepoListTest {
    @Test
    fun testRepoListViewModel_initWithRepoList_Success() {
        val controller = Robolectric.setupActivity<MainActivity>(MainActivity::class.java)
        controller.runOnUiThread {
            val repoListViewModel = RepoListViewModel()
            val repoList = Array(10){ index -> Repo(name = "Mock"+index) }.toList()
            repoListViewModel.initWithRepoList(repoList)
            assertNotNull(repoListViewModel.repoList.value)
            assertEquals(repoListViewModel.repoList.value!!.size, repoList.size)
            assertEquals(repoListViewModel.repoList.value!!.get(0).name, repoList[0].name)

            val repoList2 = ArrayList<Issue>(0)
            repoListViewModel.initWithRepoList(repoList2)

            assertNotNull(repoListViewModel.repoList.value)
            assertEquals(repoListViewModel.repoList.value!!.size, repoList2.size)
        }
    }

    fun testRepoListViewModel_initWithRepoList_Failure() {
        val controller = Robolectric.setupActivity<MainActivity>(MainActivity::class.java)
        controller.runOnUiThread {
            val repoListViewModel = RepoListViewModel()
            val repoList = Array(10) { Bundle() }.toList()
            repoListViewModel.initWithRepoList(repoList)
            assertNotNull(repoListViewModel.repoList.value)
            assertEquals(repoListViewModel.repoList.value!!.size, 0)
        }
    }
}