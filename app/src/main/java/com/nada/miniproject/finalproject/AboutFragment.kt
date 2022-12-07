package com.nada.miniproject.finalproject

import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class AboutFragment : Fragment() {
    lateinit var apiLink: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_about, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Informations sur l'application"

        apiLink = rootView.findViewById(R.id.api_url)

        apiLink?.setOnClickListener { Toast.makeText(context,
            "https://app-cb40d835-d4b4-407f-83a6-0452ebe04576.cleverapps.io", Toast.LENGTH_LONG).show()  }

        return rootView
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AboutFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}