package com.nada.miniproject.finalproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController


class CreateSchoolFragment : Fragment() {
    private var listener: SchoolCreator? = null

    lateinit var btnBack: Button

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_create_school, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = "Ajouter un nouveau établissement"

        //(activity as AppCompatActivity?)?.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        btnBack = rootView.findViewById(R.id.backButton)

        btnBack.setOnClickListener {
            activity?.let {
                val intent = Intent(it,MainActivity::class.java)
                it.startActivity(intent)
            }
        }


        edtLibelle = rootView.findViewById(R.id.libelleEditText)
        edtSigle = rootView.findViewById(R.id.sigleEditText)
        edtType = rootView.findViewById(R.id.typeEditText)
        edtSecteur = rootView.findViewById(R.id.sectorEditText)
        edtVague = rootView.findViewById(R.id.vagueEditText)
        edtX = rootView.findViewById(R.id.XEditText)
        edtY = rootView.findViewById(R.id.YEditText)
        edtDate = rootView.findViewById(R.id.dateEditText)
        edtDepartement = rootView.findViewById(R.id.departementEditText)
        edtRegion = rootView.findViewById(R.id.regionEditText)
        edtAdresse = rootView.findViewById(R.id.adressEditText)
        edtCodePostal = rootView.findViewById(R.id.codePostalEditText)
        edtNumTel = rootView.findViewById(R.id.phoneEditText)
        edtSite = rootView.findViewById(R.id.websiteEditText)
        edtFb = rootView.findViewById(R.id.fbEditText)
        edtInst = rootView.findViewById(R.id.instEditText)
        edtTwitter = rootView.findViewById(R.id.twitterEditText)
        btnAdd = rootView.findViewById(R.id.addButton)

        btnAdd.setOnClickListener {
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

            /*val arrayList = ArrayList<Double>(5)
            arrayList.add(x)//Adding object in arraylist
            arrayList.add(y)*/

            val school = School(
                libelle, sigle, type, secteur, vague, doubleArrayOf(x, y), date,
                departement, region, adresse, codePostal, num, site, fb, inst, twitter, false
            )

            listener?.onSchoolCreated(school)
        }

        return rootView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is SchoolCreator) {
            listener = context
        } else {
            throw RuntimeException("$context must implement SchoolCreator")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = CreateSchoolFragment()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            android.R.id.home -> {
                activity?.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }



}


