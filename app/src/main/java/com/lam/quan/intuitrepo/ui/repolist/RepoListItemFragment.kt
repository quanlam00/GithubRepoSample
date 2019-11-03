package com.lam.quan.intuitrepo.ui.repolist

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.lam.quan.intuitrepo.R
import com.lam.quan.intuitrepo.ui.constants.NavigationExtraArgsConstants

/**
 * A fragment representing a list of Repos from Github repos API.
 */
class RepoListItemFragment : Fragment(),
    OnRepoListFragmentInteractionListener {
    private lateinit var viewModel: RepoListViewModel
    //Any activity can reuse this fragment by implementing this Listener.
    //In this small sample app, listener is set to the fragment itself for convenience.
    private var listener: OnRepoListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RepoListViewModel::class.java)
        arguments?.let { bundle ->
            bundle.getParcelableArrayList<Parcelable>(NavigationExtraArgsConstants.REPO_LIST)?.let {
                viewModel.initWithRepoList(it)
            }
            viewModel.ownerName = bundle.getString(NavigationExtraArgsConstants.OWNER_NAME)?:""
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_repolistitem_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = RepoListItemRecyclerViewAdapter(
                    viewModel.repoList.value!!,
                    listener
                )
            }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = this
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onListFragmentInteraction(item: RepoListItem?) {
        val bundle = bundleOf(NavigationExtraArgsConstants.SELECTED_ITEM to arguments?.
            getParcelableArrayList<Parcelable>(NavigationExtraArgsConstants.REPO_LIST)?.get(item!!.index))
            bundle.putString(NavigationExtraArgsConstants.OWNER_NAME, viewModel.ownerName)
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_repoListItemFragment_to_repoDetailFragment, bundle)
    }
}
