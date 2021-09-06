package com.example.devblock.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfo(var userName: String, var password: String) : Parcelable {

}