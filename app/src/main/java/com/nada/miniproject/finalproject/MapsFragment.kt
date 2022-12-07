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
