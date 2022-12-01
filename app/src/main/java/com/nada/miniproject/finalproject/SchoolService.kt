package com.nada.miniproject.finalproject

import retrofit2.Call
import retrofit2.http.*

interface SchoolService {
    @GET("/schools")
    fun getAllBooks(): Call<List<School>>

    @POST("/schools")
    fun createBook(@Body() school: School): Call<School>

    @PUT("schools/{libelle}")
    fun addToFavorite(@Path("libelle") libelle: String, @Body favorite: Favorite): Call<School>
}