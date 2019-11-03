package com.lam.quan.intuitrepo.ui.repolist

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lam.quan.intuitrepo.model.Repo

class RepoListViewModel : ViewModel() {
    //Implementing Live Data in viewmodel with the intent of allowing frequent data refresh feature
    //later on.
    val repoList: MutableLiveData<List<RepoListItem>> = MutableLiveData()
    lateinit var ownerName: String
    fun initWithRepoList(list: List<Parcelable>) {
        val itemList = ArrayList<RepoListItem>()
        list.forEachIndexed{
                index, it ->
            if (it is Repo) itemList.add(RepoListItem(it, index))
        }
        repoList.value = itemList
    }
}