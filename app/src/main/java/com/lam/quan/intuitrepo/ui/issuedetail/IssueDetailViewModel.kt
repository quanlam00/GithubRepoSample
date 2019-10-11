package com.lam.quan.intuitrepo.ui.issuedetail

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import com.lam.quan.intuitrepo.model.Issue

class IssueDetailViewModel : ViewModel() {
    lateinit var id : String
    lateinit var number : String
    lateinit var title : String
    lateinit var body : String
    lateinit var dateCreated : String
    lateinit var dateUpdated : String
    lateinit var state : String
    lateinit var locked : String
    var initialized = false

    fun initWithIssue(issue: Parcelable) {
        if (issue is Issue) {
            title = issue.title
            dateCreated = issue.created_at
            dateUpdated = issue.updated_at
            number = issue.number.toString()
            body = issue.body
            state = issue.state
            locked = issue.locked.toString()
            initialized = true
        }
    }
}
