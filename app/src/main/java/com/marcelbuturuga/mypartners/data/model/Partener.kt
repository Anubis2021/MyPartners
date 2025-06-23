package com.marcelbuturuga.mypartners.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Partner(
    val id: Int,
    val name: String,
    val description: String,
    val rating: Int,
    val image_url: String,
    val isRemoved: Boolean = false
) : Parcelable
