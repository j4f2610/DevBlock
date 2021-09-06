package com.example.devblock.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContactInfo(
    var id: String,
    var email: String,
    var first_name: String,
    var last_name: String,
    var avatar: String
) : Parcelable {

}