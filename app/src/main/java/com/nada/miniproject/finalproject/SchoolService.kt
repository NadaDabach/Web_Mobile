package com.nada.miniproject.finalproject

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SchoolService {
    @GET("/schools")
    fun getAllBooks(): Call<List<School>>

    @POST("/schools")
    fun createBook(@Body() book:School): Call<School>
}