package com.lam.quan.intuitrepo.ui.repolist

import com.lam.quan.intuitrepo.model.Repo

data class RepoListItem(val id:String = "",
                        val name:String = "",
                        val description:String = "",
                        val index:Int = 0
                        ) {
    constructor(repo:Repo, index: Int) : this(repo.id, repo.name, repo.description, index)
}