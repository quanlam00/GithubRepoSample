package com.lam.quan.intuitrepo

import android.os.Bundle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule
import androidx.test.rule.ActivityTestRule
import com.lam.quan.intuitrepo.model.Repo
import com.lam.quan.intuitrepo.ui.repodetail.RepoDetailViewModel
import org.junit.Before
import org.junit.Assert.*


@RunWith(AndroidJUnit4::class)
class RepoDetailTest {
    @Test
    fun testIssueDetailViewModel_initWithRepo_Success() {
        val viewmodel = RepoDetailViewModel()
        val mockRepo = Repo(name = "Mock")
        viewmodel.initWithRepo(mockRepo)
        assertEquals(viewmodel.name, mockRepo.name)
    }

    @Test
    fun testIssueDetailViewModel_initWithRepo_Failure() {
        val viewmodel = RepoDetailViewModel()
        val mockIssue = Bundle()
        viewmodel.initWithRepo(mockIssue)
        assertFalse(viewmodel.initialized)
    }
}