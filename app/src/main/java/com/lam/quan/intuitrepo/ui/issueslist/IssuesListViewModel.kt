package com.lam.quan.intuitrepo.ui.issueslist

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lam.quan.intuitrepo.model.Issue

class IssuesListViewModel : ViewModel() {
    //Implementing Live Data in viewmodel with the intent of allowing frequent data refresh feature
    //later on.
    var issuesList: MutableLiveData<List<IssuesListItem>> = MutableLiveData()
    fun initWithIssuesList(list: List<Parcelable>) {
        val itemList = ArrayList<IssuesListItem>()
        list.forEachIndexed{
            index, it ->
            if (it is Issue) itemList.add(IssuesListItem(it, index))
        }
        issuesList.value = itemList
    }
}