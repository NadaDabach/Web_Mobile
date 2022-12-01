package com.nada.miniproject.finalproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AddSchool : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private var listener: SchoolCreator? = null
    lateinit var edtLibelle: EditText
    lateinit var edtSigle: EditText
    lateinit var edtType: EditText
    lateinit var edtSecteur: EditText
    lateinit var edtVague: EditText
    lateinit var edtX: EditText
    lateinit var edtY: EditText
    lateinit var edtDate: EditText
    lateinit var edtDepartement: EditText
    lateinit var edtRegion: EditText
    lateinit var edtAdresse: EditText
    lateinit var edtCodePostal: EditText
    lateinit var edtNumTel: EditText
    lateinit var edtSite: EditText
    lateinit var edtFb: EditText
    lateinit var edtInst: EditText
    lateinit var edtTwitter: EditText

    lateinit var btnAdd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_school)

        edtLibelle = findViewById(R.id.libelleEditText)
        edtSigle = findViewById(R.id.sigleEditText)
        edtType = findViewById(R.id.typeEditText)
        edtSecteur = findViewById(R.id.sectorEditText)
        edtVague = findViewById(R.id.vagueEditText)
        edtX = findViewById(R.id.XEditText)
        edtY = findViewById(R.id.YEditText)
        edtDate = findViewById(R.id.dateEditText)
        edtDepartement = findViewById(R.id.departementEditText)
        edtRegion = findViewById(R.id.regionEditText)
        edtAdresse = findViewById(R.id.adressEditText)
        edtCodePostal = findViewById(R.id.codePostalEditText)
        edtNumTel = findViewById(R.id.phoneEditText)
        edtSite = findViewById(R.id.websiteEditText)
        edtFb = findViewById(R.id.fbEditText)
        edtInst = findViewById(R.id.instEditText)
        edtTwitter = findViewById(R.id.twitterEditText)
        btnAdd = findViewById(R.id.addButton)

        btnAdd.setOnClickListener{
            val libelle = edtLibelle.text.toString()
            val sigle = edtSigle.text.toString()
            val type = edtType.text.toString()
            val secteur = edtSecteur.text.toString()
            val vague = edtVague.text.toString()
            val xGeo = edtX
            val x: Double = xGeo.text.toString().toDouble()
            val yGeo = edtY
            val y: Double = yGeo.text.toString().toDouble()
            val date = edtDate.text.toString()
            val departement = edtDepartement.text.toString()
            val region = edtRegion.text.toString()
            val adresse = edtAdresse.text.toString()
            val code = edtCodePostal
            val codePostal: Int = code.text.toString().toInt()
            val num = edtNumTel.text.toString()
            val site = edtSite.text.toString()
            val fb = edtFb.text.toString()
            val inst = edtInst.text.toString()
            val twitter = edtTwitter.text.toString()

            val arrayList = ArrayList<Double>(5)
            arrayList.add(x)//Adding object in arraylist
            arrayList.add(y)

            val school = School(libelle, sigle, type, secteur, vague, doubleArrayOf(x,y), date,
            departement, region, adresse, codePostal, num, site, fb, inst, twitter, false)

            listener?.onSchoolCreated(school)
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }

       /* val spinner=findViewById<Spinner>(R.id.vagueEditText) as Spinner

        val vague= arrayOf("A","B","C","D","E")
        val adapter=
            ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,vague)

        spinner.adapter=adapter

        spinner.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                Toast.makeText(applicationContext,"You have Selected "+vague[p2],Toast.LENGTH_LONG).show()
            }
        }*/

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val text: String = parent?.getItemAtPosition(position).toString()
    }
}