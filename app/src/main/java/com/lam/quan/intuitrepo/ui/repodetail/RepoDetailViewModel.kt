package com.lam.quan.intuitrepo.ui.repodetail

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import com.lam.quan.intuitrepo.model.Issue
import com.lam.quan.intuitrepo.model.Repo

class RepoDetailViewModel : ViewModel() {
    //TODO: Refactor this ownerName
    lateinit var ownerName: String
    lateinit var name : String
    lateinit var description : String
    lateinit var watchCount : String
    lateinit var forkCount : String
    lateinit var starCount : String
    lateinit var dateCreated : String
    lateinit var dateUpdated : String
    lateinit var size : String
    lateinit var issuesCount : String
    var issuesList : List<Issue> = ArrayList()
    var initialized: Boolean = false
    fun initWithRepo(repo: Parcelable) {
        if (repo is Repo) {
            name = repo.name
            description = repo.description?:""
            watchCount = repo.watchers_count.toString()
            forkCount = repo.forks_count.toString()
            starCount = repo.stargazers_count.toString()
            dateCreated = repo.created_at
            dateUpdated = repo.updated_at
            size = repo.size.toString()
            issuesCount = repo.open_issues_count.toString()
            initialized = true
        }
    }
}
