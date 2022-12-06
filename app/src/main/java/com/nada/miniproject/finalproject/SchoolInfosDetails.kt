package com.nada.miniproject.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class SchoolInfosDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school_infos_details)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        var txvlibelle = findViewById<TextView>(R.id.textView)
        var txvSecteur = findViewById<TextView>(R.id.textView2)
        var txvAdresse = findViewById<TextView>(R.id.textView3)

        var intent=intent
        val dTitre=intent.getStringExtra("libelle")
        val dImage=intent.getStringExtra("secteur")
        val dLegende=intent.getStringExtra("adresse")
        txvlibelle.text = dTitre
        txvSecteur.text = dImage
        txvAdresse.text = dLegende
    }
}