package com.nada.miniproject.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class SchoolInfosDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school_infos_details)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        var txvSigle = findViewById<TextView>(R.id.textViewSigle)
        var txvlibelle = findViewById<TextView>(R.id.textViewLibelle)
        var txvSecteur = findViewById<TextView>(R.id.textViewSecteur)
        var txvVague = findViewById<TextView>(R.id.textViewVague)
        var txvRegion = findViewById<TextView>(R.id.textViewRegion)
        var txvAdresse = findViewById<TextView>(R.id.textViewAdresse)
        var txvNum = findViewById<TextView>(R.id.textViewNum)

        //var btnFb = findViewById<ImageButton>(R.id.fb_button)

        var intent=intent
        val iSigle=intent.getStringExtra("sigle")
        val iLibelle=intent.getStringExtra("libelle")
        val iSecteur=intent.getStringExtra("secteur")
        val iVague=intent.getStringExtra("vague")
        val iRegion=intent.getStringExtra("region")
        val iAdresse=intent.getStringExtra("adresse")
        val iNum=intent.getStringExtra("num")


        txvSigle.text = iSigle
        txvlibelle.text = iLibelle
        txvSecteur.text = iSecteur
        txvVague.text = iVague
        txvRegion.text = iRegion
        txvAdresse.text = iAdresse
        txvNum.text = iNum
    }
}