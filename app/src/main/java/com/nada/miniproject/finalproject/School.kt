package com.nada.miniproject.finalproject

data class School(
    val libelle: String,
    val sigle: String,
    val type: String,
    val secteur: String,
    val vague: String,
    val geolocalisation: DoubleArray?,
    val date: String,
    val departement: String,
    val region: String,
    val adresse: String,
    val code_postal: Int,
    val numero_telephone: String,
    val site_web: String,
    val compte_fb: String,
    val compte_twitter: String,
    val compte_insta: String,
    var favorite: Boolean,
): java.io.Serializable
