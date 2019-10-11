package com.lam.quan.intuitrepo

import android.os.Bundle
import com.lam.quan.intuitrepo.model.Issue
import com.lam.quan.intuitrepo.ui.issueslist.IssuesListViewModel
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner


@Suppress("DEPRECATION")
@RunWith(RobolectricTestRunner::class)
class IssuesListTest {
    @Test
    fun testIssuesListViewModel_initWithIssuesList_Success() {
        val controller = Robolectric.setupActivity<MainActivity>(MainActivity::class.java)
        controller.runOnUiThread {
            val issuesListViewModel = IssuesListViewModel()
            val issuesList = Array<Issue>(10){ index -> Issue(title = "Mock"+index)}.toList()
            issuesListViewModel.initWithIssuesList(issuesList)
            assertNotNull(issuesListViewModel.issuesList.value)
            assertEquals(issuesListViewModel.issuesList.value!!.size, issuesList.size)
            assertEquals(issuesListViewModel.issuesList.value!!.get(0).title, issuesList[0].title)

            val issuesList2 = ArrayList<Issue>(0)
            issuesListViewModel.initWithIssuesList(issuesList2)

            assertNotNull(issuesListViewModel.issuesList.value)
            assertEquals(issuesListViewModel.issuesList.value!!.size, issuesList2.size)
        }
    }

    fun testIssuesListViewModel_initWithIssuesList_Failure() {
        val controller = Robolectric.setupActivity<MainActivity>(MainActivity::class.java)
        controller.runOnUiThread {
            val issuesListViewModel = IssuesListViewModel()
            val issuesList = Array<Bundle>(10) { Bundle() }.toList()
            issuesListViewModel.initWithIssuesList(issuesList)
            assertNotNull(issuesListViewModel.issuesList.value)
            assertEquals(issuesListViewModel.issuesList.value!!.size, 0)
        }
    }
}