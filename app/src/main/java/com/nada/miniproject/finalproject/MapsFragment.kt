package com.nada.miniproject.finalproject


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

private const val SCHOOLS = "schools"

class MapsFragment : Fragment() {
    private var schools: ArrayList<School> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            schools = it.getSerializable(SCHOOLS) as ArrayList<School>
        }
    }

    private val callback = OnMapReadyCallback { googleMap ->

        schools.forEach{
                it ->
            val coord1 = it.geolocalisation?.get(0)
            val coord2 = it.geolocalisation?.get(1)
                if (coord2 != null && coord1 != null) {
                    var location = LatLng(coord1, coord2)
                    googleMap.addMarker(MarkerOptions().position(location).title(it.libelle))
                }

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_maps, container, false)
        /*val mapFragment =
            childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync { mMap ->
            mMap.mapType = GoogleMap.MAP_TYPE_NORMAL

            mMap.clear()

            val googlePlex = CameraPosition.builder()
                .target(LatLng( 46.63728, 2.3382623))
                .zoom(10f)
                .bearing(0f)
                .tilt(45f)
                .build()
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null)

            schools.forEach {
                println(schools)
                val location = LatLng(it.geolocalisation[0], it.geolocalisation[1])
                mMap.addMarker(MarkerOptions().position(location).title(it.libelle))
            }

        }*/
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    companion object {
        @JvmStatic
        fun newInstance(schools: ArrayList<School>) =
            MapsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(SCHOOLS, schools)
                }
            }
    }
}
/*import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

private const val SCHOOLS = "schools"

class MapsFragment() : Fragment() {

    private var schools: ArrayList<School> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            schools = it.getSerializable(SCHOOLS) as ArrayList<School>
        }
        println(schools)
    }

    private val callback = OnMapReadyCallback { googleMap ->
        println(schools)
        schools.forEach{
                it ->
            val coord1 = it.geolocalisation[0]
            val coord2 = it.geolocalisation[1]
            println(coord1)
            println(coord2)
            val location = LatLng(coord1, coord2)
            googleMap.addMarker(MarkerOptions().position(location).title(it.libelle))
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    companion object {
        @JvmStatic
        fun newInstance(monuments: ArrayList<School>) =
            MapsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(SCHOOLS, monuments)
                }
            }
    }
*/
    /**lateinit var mapFragment: SupportMapFragment
    lateinit var googleMap: GoogleMap
    private val schoolsList: ArrayList<School> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        println(schoolsList)
        val view = inflater.inflate(R.layout.fragment_maps, container, false)
        mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(OnMapReadyCallback {
            googleMap = it
            schoolsList.forEach { it1 ->

                val location = LatLng(it1.geolocalisation[0], it1.geolocalisation[1])
                googleMap.addMarker(MarkerOptions().position(location).title(it1.libelle))
                print(location)

            }
        })
        return view
    }**/

    /**private var schools: ArrayList<School> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            schools = it.getSerializable(SCHOOLS) as ArrayList<School>
        }
        println(schools)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_maps, container, false)
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync { mMap ->
            mMap.mapType = GoogleMap.MAP_TYPE_NORMAL

            mMap.clear() //clear old markers

            val googlePlex = CameraPosition.builder()
                .target(LatLng( 36.806389, 10.181667))
                .zoom(10f)
                .bearing(0f)
                .tilt(45f)
                .build()
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null)

            schools.forEach {
                val location = LatLng(it.geolocalisation[0], it.geolocalisation[1])
                mMap.addMarker(MarkerOptions().position(location).title(it.libelle))
            }

        }
        return rootView
    }

    companion object {
        @JvmStatic
        fun newInstance(schools: ArrayList<School>) =
            MapsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(SCHOOLS, schools)
                }
            }
    }*/

    /*private lateinit var myMap: GoogleMap
    private lateinit var schoolsMap: SupportMapFragment
    private val schools = Schools()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }*/

    /*private val callback = OnMapReadyCallback { googleMap ->
        /*val sydney = LatLng(-34.0, 151.0)
        schools.getAllSchools().forEach { it1 ->
            val location = LatLng(it1.geolocalisation[0], it1.geolocalisation[1])

            //Log.i("loc", location.toString())
            myMap.addMarker(MarkerOptions().position(location).title(it1.libelle))
            //googleMap.addMarker(MarkerOptions().position(location).title("Marker in Sydney"))
            //googleMap.moveCamera(CameraUpdateFactory.newLatLng(location))
            // myMap.addMarker(MarkerOptions().position(location).title(it1.libelle))
        }
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))*/

        schools.getAllSchools().forEach { it ->
            val coord1 = it.geolocalisation[0]
            val coord2 = it.geolocalisation[1]
            val location = LatLng(coord1, coord2)
            myMap.addMarker(MarkerOptions().position(location).title(it.libelle))
        }

    }*/

    /*override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }*/

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        schoolsMap = (childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?)!!
        schoolsMap?.getMapAsync(callback)
    }*/

    /*private val callback = OnMapReadyCallback { mMap ->
        //myMap.mapType = GoogleMap.MAP_TYPE_NORMAL

        //myMap.clear() //clear old markers

        val googlePlex = CameraPosition.builder()
            .target(LatLng( 46.63728, 2.3382623))
            .zoom(10f)
            .bearing(0f)
            .tilt(45f)
            .build()

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null)
        schools.getAllSchools().forEach {
            val location = LatLng(it.geolocalisation[0], it.geolocalisation[1])
            mMap.addMarker(MarkerOptions().position(location).title(it.libelle))
            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(0.0, 0.0))
                    .title("Nada")
            )
        }

    }*/

    /*override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_maps, container, false)
        schoolsMap = (childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?)!!
        schoolsMap?.getMapAsync(callback)
        return rootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        schoolsMap = (childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?)!!
        schoolsMap?.getMapAsync(callback)
    }*/

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }*/

    /*companion object {
        @JvmStatic
        fun newInstance(schools: ArrayList<School>) =
            MapsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(SCHOOLS, schools)
                }
            }
    }*/
