package com.nada.miniproject.finalproject

import com.google.gson.annotations.SerializedName

data class UsersResponse(
    val page: Int,
    val total: Int,
    val data: ArrayList<School>
)
