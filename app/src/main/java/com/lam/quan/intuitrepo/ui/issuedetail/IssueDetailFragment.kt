package com.lam.quan.intuitrepo.ui.issuedetail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.lam.quan.intuitrepo.R
import com.lam.quan.intuitrepo.databinding.IssueDetailFragmentBinding
import com.lam.quan.intuitrepo.ui.constants.NavigationExtraArgsConstants

/**
 * Fragment that display the detail information of an single issue from a repo issue list
 */
class IssueDetailFragment : Fragment() {
    private lateinit var viewModel: IssueDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Data Binding
        val binding:IssueDetailFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.issue_detail_fragment, container, false
        )
        val view = binding.getRoot()

        //Init ViewModel
        viewModel = ViewModelProviders.of(this).get(IssueDetailViewModel::class.java)
        arguments?.let {
            it.getParcelable<Parcelable>(NavigationExtraArgsConstants.SELECTED_ITEM)?.let {
                viewModel.initWithIssue(it)
            }
        }
        binding.viewmodel = viewModel
        return view
    }
}
