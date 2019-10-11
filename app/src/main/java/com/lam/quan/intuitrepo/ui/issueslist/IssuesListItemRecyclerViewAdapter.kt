package com.lam.quan.intuitrepo.ui.issueslist

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lam.quan.intuitrepo.R
import kotlinx.android.synthetic.main.fragment_issueslistitem.view.*

class IssuesListItemRecyclerViewAdapter(
    private val mValues: List<IssuesListItem>,
    private val mListener: OnIssueListFragmentInteractionListener?
) : RecyclerView.Adapter<IssuesListItemRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as IssuesListItem
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_issueslistitem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mTitleView.text = item.title
        holder.mStateView.text = item.state

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mTitleView: TextView = mView.item_title
        val mStateView: TextView = mView.item_state

        override fun toString(): String {
            return super.toString() + " '" + mStateView.text + "'"
        }
    }
}
