package com.lam.quan.intuitrepo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Github repo's issue response model from issues API
 */
@Parcelize
data class Issue (val url:String = "",
                  val repository_url:String = "",
                  val labels_url:String = "",
                  val comments_url:String = "",
                  val events_url:String = "",
                  val html_url:String = "",
                  val id:String = "",
                  val node_id:String = "",
                  val number:Int = 0,
                  val title:String = "",
                  val user:User = User(),
                  val state:String = "",
                  val locked:Boolean = false,
                  val comments:Int = 0,
                  val created_at:String = "",
                  val updated_at:String = "",
                  val closed_at:String = "",
                  val author_association:String = "",
                  val body:String = ""): Parcelable