package com.lam.quan.intuitrepo.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.lam.quan.intuitrepo.R
import com.lam.quan.intuitrepo.api.GithubApi
import com.lam.quan.intuitrepo.model.Repo
import com.lam.quan.intuitrepo.ui.constants.NavigationExtraArgsConstants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Main Fragment serve as the first destination of the Navigation Map
 */
class MainFragment : Fragment() {
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        val fetchButton = view.findViewById<Button>(R.id.fetch_button)
        val repoNameInput = view.findViewById<TextInputEditText>(R.id.repo_name_input)
        fetchButton.setOnClickListener { fetchIntuitRepos(repoNameInput.text.toString()) }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    private val intuitRepoApi by lazy {
        GithubApi.create()
    }
    private var disposable: Disposable? = null

    private fun fetchIntuitRepos(name:String) {
        disposable =
            intuitRepoApi.fetchIntuitRepos(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result -> onResultFetched(result) },
                    { error -> onResultError(error.message) }
                )
    }

    private fun onResultFetched(list:List<Repo>) {
        val bundle = bundleOf(NavigationExtraArgsConstants.REPO_LIST to list)
        findNavController(this)
            .navigate(R.id.action_mainFragment_to_repoListItemFragment, bundle)
    }

    private fun onResultError(error:String?) {
        Toast.makeText(this.context, "Fetch Error "+error, Toast.LENGTH_LONG).show()
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}
