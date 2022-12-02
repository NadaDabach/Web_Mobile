package com.nada.miniproject.finalproject

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val SCHOOLS = "schools"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var schoolAdapter: SchoolAdapter
    private lateinit var schoolFavorite: SchoolFavorite
    private lateinit var rcvSchools: RecyclerView
    private var schools: ArrayList<School> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            schools = it.getSerializable(SCHOOLS) as ArrayList<School>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        schoolAdapter = SchoolAdapter(schools)

        rcvSchools = rootView.findViewById(R.id.rcv_schools)
        rcvSchools.adapter = schoolAdapter

        val linearLayoutManager = LinearLayoutManager(context)
        rcvSchools.layoutManager = linearLayoutManager

        return rootView
    }


    companion object {
        @JvmStatic
        fun newInstance(schools: ArrayList<School>) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(SCHOOLS, schools)
                }
            }
    }
}
