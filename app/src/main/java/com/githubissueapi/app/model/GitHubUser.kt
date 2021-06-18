package com.githubissueapi.app.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GitHubUser(
        val login: String? = null,
        val avatar_url: String? = null,
        val type: String? = null,
) : Parcelable
