package com.githubissueapi.app.model


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GitHubIssue(
        val title: String? = null,
        val body: String? = null,
        val user: GitHubUser? = null,
        val created_at: String? = null,
        val updated_at: String? = null
) : Parcelable