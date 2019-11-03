package com.lam.quan.intuitrepo.ui.repodetail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.lam.quan.intuitrepo.R
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.lam.quan.intuitrepo.api.GithubApi
import com.lam.quan.intuitrepo.databinding.RepoDetailFragmentBinding
import com.lam.quan.intuitrepo.model.Issue
import com.lam.quan.intuitrepo.ui.constants.NavigationExtraArgsConstants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Fragment that display detail information of a repo from Github repos API
 */
class RepoDetailFragment : Fragment() {
    private lateinit var viewModel: RepoDetailViewModel
    private lateinit var issuesListButton:Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Data Binding
        val binding:RepoDetailFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.repo_detail_fragment, container, false
        )
        val view = binding.getRoot()

        //Init ViewModel and other UI
        viewModel = ViewModelProviders.of(this).get(RepoDetailViewModel::class.java)
        arguments?.let {
            it.getParcelable<Parcelable>("SELECTED_ITEM")?.let {
                viewModel.initWithRepo(it)
            }
            viewModel.ownerName = it.getString(NavigationExtraArgsConstants.OWNER_NAME)?:""
        }
        issuesListButton = view.findViewById(R.id.viewIssuesListButton)
        //View Issues Button stay invisible until data is ready
        issuesListButton.visibility = View.INVISIBLE
        issuesListButton.setOnClickListener {
            val bundle = bundleOf(NavigationExtraArgsConstants.ISSUES_LIST to viewModel.issuesList)
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_repoDetailFragment_to_issuesRepoListFragment, bundle)
        }
        binding.viewmodel = viewModel
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (viewModel.issuesList.isEmpty()) {
            if (viewModel.issuesCount.toInt()>0) {
                Toast.makeText(this.context, "Fetching Issue List.", Toast.LENGTH_LONG).show()
                fetchIntuitRepoIssuesList()
            }
        } else {
            issuesListButton.visibility = View.VISIBLE
        }
    }

    //Fetching Issues List
    private val intuitRepoApi by lazy {
        GithubApi.create()
    }
    private var disposable: Disposable? = null

    private fun fetchIntuitRepoIssuesList() {
        disposable =
            intuitRepoApi.fetchIntuitRepoIssues(viewModel.ownerName, viewModel.name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result -> onResultFetched(result) },
                    { error -> onResultError(error.message) }
                )
    }

    private fun onResultFetched(list:List<Issue>) {
        if (list.size>0) {
            viewModel.issuesList = list
            issuesListButton.visibility = View.VISIBLE
        }
    }

    private fun onResultError(error:String?) {
        Toast.makeText(this.context, "Fetch Error $error", Toast.LENGTH_LONG).show()
    }
}
