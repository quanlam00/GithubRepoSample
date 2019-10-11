package com.lam.quan.intuitrepo.ui.issueslist

import com.lam.quan.intuitrepo.model.Issue

data class IssuesListItem(val title:String,
                          val state:String,
                          val index:Int) {
    constructor(issue:Issue, index: Int) : this(issue.title, issue.state, index)
}