package com.lam.quan.intuitrepo.ui.issueslist

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
 * A fragment representing a list of Issues Items from a specific repo.
 */
class IssuesListFragment : Fragment(),
    OnIssueListFragmentInteractionListener {

    private lateinit var viewModel: IssuesListViewModel
    //Any activity can reuse this fragment by implementing this Listener.
    //In this small sample app, listener is set to the fragment itself for convenience.
    private var listener: OnIssueListFragmentInteractionListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(IssuesListViewModel::class.java)
        arguments?.let { bundle ->
            bundle.getParcelableArrayList<Parcelable>(NavigationExtraArgsConstants.ISSUES_LIST)?.let {
                viewModel.initWithIssuesList(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_issuesitem_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = IssuesListItemRecyclerViewAdapter(
                    viewModel.issuesList.value!!,
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

    override fun onListFragmentInteraction(item: IssuesListItem?) {
        val bundle = bundleOf(NavigationExtraArgsConstants.SELECTED_ITEM to arguments?.
            getParcelableArrayList<Parcelable>(NavigationExtraArgsConstants.ISSUES_LIST)?.get(item!!.index))
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_issuesRepoListFragment_to_issueDetailFragment, bundle)
    }
}
