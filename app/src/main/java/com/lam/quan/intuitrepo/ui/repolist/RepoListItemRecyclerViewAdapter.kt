package com.lam.quan.intuitrepo.ui.repolist

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lam.quan.intuitrepo.R

import kotlinx.android.synthetic.main.fragment_repolistitem.view.*

class RepoListItemRecyclerViewAdapter(
    private val mValues: List<RepoListItem>,
    private val mListenerRepo: OnRepoListFragmentInteractionListener?
) : RecyclerView.Adapter<RepoListItemRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as RepoListItem
            mListenerRepo?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_repolistitem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.name
        holder.mContentView.text = item.description

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.item_title
        val mContentView: TextView = mView.item_state

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
