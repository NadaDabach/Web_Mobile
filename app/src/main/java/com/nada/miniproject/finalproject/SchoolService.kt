package com.nada.miniproject.finalproject

import retrofit2.Call
import retrofit2.http.*

interface SchoolService {
    @GET("/schools")
    fun getAllSchool(): Call<List<School>>

    @POST("/schools")
    fun createSchool(@Body() school: School): Call<School>

    @GET("/schools")
    fun searchByTitle(@Query("departement") departement: String): Call<List<School>>

    @PUT("schools/{libelle}")
    fun addToFavorite(@Path("libelle") libelle: String): Call<School>

    @DELETE("schools/{libelle}")
    fun deleteSchool(@Path("libelle") libelle: String)
}