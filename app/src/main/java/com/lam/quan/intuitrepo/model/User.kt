package com.lam.quan.intuitrepo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Github User response model
 */
@Parcelize
data class User(val login: String = "",
                val id:String = "",
                val node_id:String = "",
                val avatar_url:String = "",
                val gravatar_id:String = "",
                val html_url:String = "",
                val follower_url:String = "",
                val gists_url:String = "",
                val star_url:String = "",
                var subscriptions_url:String = "",
                var organizations_url:String = "",
                var repos_url:String = "",
                var events_url:String = "",
                var received_events_url:String = "",
                var type:String     = "",
                var site_admin:Boolean = false
                ): Parcelable