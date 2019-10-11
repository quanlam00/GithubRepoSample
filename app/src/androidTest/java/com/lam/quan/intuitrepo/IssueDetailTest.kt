package com.lam.quan.intuitrepo

import android.os.Bundle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lam.quan.intuitrepo.model.Issue
import com.lam.quan.intuitrepo.ui.issuedetail.IssueDetailViewModel
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class IssueDetailTest {

    @Test
    fun testIssueDetailViewModel_initWithIssue_Success() {
        val viewmodel = IssueDetailViewModel()
        val mockIssue = Issue(title = "Mock")
        viewmodel.initWithIssue(mockIssue)
        assertEquals(viewmodel.title, mockIssue.title)
    }

    @Test
    fun testIssueDetailViewModel_initWithIssue_Failure() {
        val viewmodel = IssueDetailViewModel()
        val mockIssue = Bundle()
        viewmodel.initWithIssue(mockIssue)
        assertFalse(viewmodel.initialized)
    }
}