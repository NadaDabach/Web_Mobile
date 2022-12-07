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
        var txvFb = findViewById<TextView>(R.id.textViewFb)
        var txvTwitter = findViewById<TextView>(R.id.textViewTwitter)
        var txvInst = findViewById<TextView>(R.id.textViewInst)

        //var btnFb = findViewById<ImageButton>(R.id.fb_button)

        var intent=intent
        val dSigle=intent.getStringExtra("sigle")
        val dLibelle=intent.getStringExtra("libelle")
        val dSecteure=intent.getStringExtra("secteur")
        val dVague=intent.getStringExtra("vague")
        val dRegion=intent.getStringExtra("region")
        val dAFb=intent.getStringExtra("fb")
        val dATwitter=intent.getStringExtra("twt")
        val dAInst=intent.getStringExtra("inst")
        val dAdresse=intent.getStringExtra("adresse")

        txvSigle.text = dSigle
        txvlibelle.text = dLibelle
        txvSecteur.text = dSecteure
        txvVague.text = dVague
        txvRegion.text = dRegion
        txvAdresse.text = dAdresse
        txvFb.movementMethod = LinkMovementMethod.getInstance()
    }
}