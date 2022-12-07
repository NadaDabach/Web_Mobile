package com.nada.miniproject.finalproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nada.miniproject.finalproject.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), SchoolCreator, SchoolFavorite {

    private lateinit var binding : ActivityMainBinding

    private val schools = Schools()
    private lateinit var mapFragment: MapsFragment
    private lateinit var schoolsListFragment: HomeFragment
    private lateinit var infoFragment : AboutFragment

    private val btnCreateSchool: FloatingActionButton by lazy { findViewById(R.id.btn_create_school) }

    val SERVER_BASE_URL = "https://app-cb40d835-d4b4-407f-83a6-0452ebe04576.cleverapps.io"

    val retrofit = Retrofit.Builder()
        .baseUrl(SERVER_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val schoolService = retrofit.create(SchoolService::class.java)

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displaySchools()
        //onDisplayMaps()

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navigation_bar_home -> displaySchools()
                R.id.navigation_bar_map ->  displayMapsFragment()
                R.id.navigation_bar_about -> displayAboutFragment()
                else ->{
                }
            }
            true
        }

        btnCreateSchool.setOnClickListener {
            navigateToAddSchoolFragment()
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_delete -> {
                schools.clean()
                displaySchoolListFragment()
                true
            }
            R.id.menu_refresh -> {
                displaySchools()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        val searchItem = menu?.findItem(R.id.menu_search)
        val refreshItem = menu?.findItem(R.id.menu_refresh)
        val deleteItem = menu?.findItem(R.id.menu_delete)
        //searchItem?.expandActionView()

        if (searchItem!=null) {
            val searchView = searchItem.actionView as SearchView

            searchView.queryHint = "Search"

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    var allData: ArrayList<School>?
                    schools.clean()
                    schoolService.searchByTitle(query).enqueue(object : Callback<List<School>> {
                        override fun onResponse(
                            call: Call<List<School>>,
                            response: Response<List<School>>
                        ) {
                            allData = ArrayList(response.body())
                            allData?.forEach{schools.addSchool(it)}
                            displaySchoolListFragment()
                        }
                        override fun onFailure(call: Call<List<School>>, t: Throwable) {
                            Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                        }
                    })
                    return true
                }

                override fun onQueryTextChange(p0: String): Boolean {
//                    var allData: ArrayList<School>?
//                    schools.clean()
//                    schoolService.searchByTitle(p0).enqueue(object : Callback<List<School>> {
//                        override fun onResponse(
//                            call: Call<List<School>>,
//                            response: Response<List<School>>
//                        ) {
//                            allData = ArrayList(response.body())
//                            allData?.forEach{schools.addSchool(it)}
//                            displaySchoolListFragment()
//                        }
//                        override fun onFailure(call: Call<List<School>>, t: Throwable) {
//                            Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
//                        }
//                    })
                    return true
                }

            })
        }


        return super.onCreateOptionsMenu(menu)
    }

    override fun onFavoriteSchool(school: School) {
        schoolService.addToFavorite(school.libelle)
            .enqueue(object : Callback<School>{
                override fun onResponse(call: Call<School>, response: Response<School>) {
                    response.body()?.let {schools.addTofavorite(it)}
                    displaySchoolListFragment()
                }
                override fun onFailure(call: Call<School>, t: Throwable) {
                    Toast.makeText(applicationContext, "fail created mdr", Toast.LENGTH_SHORT).show()
                }
            })

    }

    /*override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.navigation_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.navigation_bar_home -> {
                displaySchools()
                true
            }
            R.id.navigation_bar_map -> {
                onDisplayMaps()
                true
            }
            R.id.navigation_bar_about -> {
                displayAboutFragment()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }*/

    private fun displaySchoolListFragment() {
        val schoolListFragment = HomeFragment.newInstance(schools.getAllSchools())
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, schoolListFragment)
            .commit()
        btnCreateSchool.show()
    }

    private fun displaySchools(){
        schoolService.getAllSchool()
            .enqueue(object : Callback<List<School>> {
                override fun onResponse(
                    call: Call<List<School>>,
                    response: Response<List<School>>
                ) {
                    val getAllBooks: List<School>? = response.body() as ArrayList<School>
                    getAllBooks?.forEach { schools.addSchool(it) }
                    displaySchoolListFragment()
                }

                override fun onFailure(call: Call<List<School>>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(applicationContext, "It fails with error", Toast.LENGTH_SHORT).show()
                }
            })
        displaySchoolListFragment()
    }

    private fun navigateToAddSchoolFragment(){
        val createSchoolFragment = CreateSchoolFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, createSchoolFragment)
            .commit()
        btnCreateSchool.hide()
    }

    override fun onSchoolCreated(school: School) {
        schoolService.createSchool(school)
            .enqueue(object : Callback<School>{
                override fun onResponse(call: Call<School>, response: Response<School>) {
                    response.body()?.let {schools.addSchool(it)}
                    displaySchoolListFragment()
                }
                override fun onFailure(call: Call<School>, t: Throwable) {
                    Toast.makeText(applicationContext, "fail created mdr", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun displayAboutFragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,AboutFragment())
        fragmentTransaction.commit()
        btnCreateSchool.hide()
    }

    private fun displayMapsFragment() {


        schoolService.getAllSchool()
            .enqueue(object : Callback<List<School>> {
                override fun onResponse(
                    call: Call<List<School>>,
                    response: Response<List<School>>
                ) {
                    val getAllSchools: List<School>? = response.body() as ArrayList<School>
                    println(getAllSchools)
                    getAllSchools?.forEach { schools.addSchool(it) }
                    mapFragment = MapsFragment.newInstance(schools.getAllSchools())
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, mapFragment)
                        .commit()
                    btnCreateSchool.hide()
//                    val mapFragment =
//                        supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
//                    mapFragment?.getMapAsync { mMap ->
//                        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
//
//                        mMap.clear()
//
//                        val googlePlex = CameraPosition.builder()
//                            .target(LatLng( 46.63728, 2.3382623))
//                            .zoom(10f)
//                            .bearing(0f)
//                            .tilt(45f)
//                            .build()
//                        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null)
//
//                        if (getAllSchools != null) {
//                            getAllSchools.forEach {
//                                println(getAllSchools)
//                                val location = LatLng(it.geolocalisation[0], it.geolocalisation[1])
//                                mMap.addMarker(MarkerOptions().position(location).title(it.libelle))
//                            }
//                        }

                   // }
                }

                override fun onFailure(call: Call<List<School>>, t: Throwable) {
                    error("Maps failed to launch")
                }
            })
    }

    /*private fun onDisplayMaps() {
        schoolService.getAllSchool()
            .enqueue(object : Callback<List<School>> {
                override fun onResponse(
                    call: Call<List<School>>,
                    response: Response<List<School>>
                ) {
                    val getAllSchools: List<School>? = response.body() as ArrayList<School>
                    println(getAllSchools)
                    getAllSchools?.forEach { schools.addSchool(it) }
                    val mapFragment =
                        supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
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

                        if (getAllSchools != null) {
                            getAllSchools.forEach {
                                println(getAllSchools)
                                val location = LatLng(it.geolocalisation[0], it.geolocalisation[1])
                                mMap.addMarker(MarkerOptions().position(location).title(it.libelle))
                            }
                        }

                    }
                }

                override fun onFailure(call: Call<List<School>>, t: Throwable) {
                    error("Maps failed to launch")
                }
            })
        displayMapsFragment()
    }*/


    /*private fun onDisplayMuseumList() {
        museumService.getMuseums()
            .enqueue(object : Callback<List<Musee>> {
                override fun onResponse(
                    call: Call<List<Musee>>,
                    response: Response<List<Musee>>
                ) {
                    val getAllMuseums: List<Musee>? = response.body() as ArrayList<Musee>
                    println(getAllMuseums)
                    getAllMuseums?.forEach { museums.addMusee(it) }
                    displayMuseumListFragment()
                }

                override fun onFailure(call: Call<List<Musee>>, t: Throwable) {
                    error("KO")

                }
            })
    }*/

    /*override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_clean -> {
                museums.clean()
                displayMuseumListFragment()
                true
            }
            R.id.favori -> {
                var favoris = museumListFragment.getAdapter().getFavoris()
                val intent = Intent(this, FavoriteMuseumsActivity::class.java)
                intent.putExtra("favoris", favoris)
                startActivity(intent)

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }*/

}
/*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nada.miniproject.finalproject.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

const val SERVER_BASE_URL = "https://app-cb40d835-d4b4-407f-83a6-0452ebe04576.cleverapps.io"

class MainActivity : AppCompatActivity(), SchoolCreator, SchoolFavorite{

    private lateinit var binding : ActivityMainBinding
    private lateinit var mapFragment: MapsFragment
    private lateinit var schoolsFragment: HomeFragment

    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(SERVER_BASE_URL)
        .build()

    val schoolService = retrofit.create(SchoolService::class.java)

    private val schoolList = Schools()

    private val btnCreateSchool: FloatingActionButton by lazy { findViewById(R.id.btn_create_school) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //onDisplayMaps()
        //replaceFragment(MapsFragment(schoolList))

        displaySchools()

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navigation_bar_home -> displaySchools()
                R.id.navigation_bar_map ->  onDisplayMaps()
                R.id.navigation_bar_about -> replaceFragment(AboutFragment())
                else ->{
                }
            }
            true
        }

        btnCreateSchool.setOnClickListener {
            navigateToAddSchoolFragment()
        }
    }

    /*private fun displayMap() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment = MapsFragment.newInstance(schoolList.getAllSchools())
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }*/

    private fun replaceFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }

    private fun navigateToAddSchoolFragment(){
        val createSchoolFragment = CreateSchoolFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, createSchoolFragment)
            .commit()
        btnCreateSchool.hide()
    }

    private fun displaySchoolListFragment() {
        val schoolListFragment = HomeFragment.newInstance(schoolList.getAllSchools())
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, schoolListFragment)
            .commit()
        btnCreateSchool.show()
    }

    private fun displaySchools(){
        schoolService.getAllSchool()
            .enqueue(object : Callback<List<School>> {
                override fun onResponse(
                    call: Call<List<School>>,
                    response: Response<List<School>>
                ) {
                    val getAllBooks: List<School>? = response.body() as ArrayList<School>
                    getAllBooks?.forEach { schoolList.addSchool(it) }
                    displaySchoolListFragment()
                    //onDisplayMaps()
                    //displaySchoolMapsFragment()
                }

                override fun onFailure(call: Call<List<School>>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(applicationContext, "It fails with error", Toast.LENGTH_SHORT).show()
                }
            })
        displaySchoolListFragment()
    }

    private fun displaySchoolMapsFragment() {
        val schoolListFragment = MapsFragment.newInstance(schoolList.getAllSchools())
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, schoolListFragment)
            .commit()
        btnCreateSchool.hide()
    }

    override fun onSchoolCreated(school: School) {
        schoolService.createSchool(school)
            .enqueue(object : Callback<School>{
                override fun onResponse(call: Call<School>, response: Response<School>) {
                    response.body()?.let {schoolList.addSchool(it)}
                    displaySchoolListFragment()
                }
                override fun onFailure(call: Call<School>, t: Throwable) {
                    Toast.makeText(applicationContext, "fail created mdr", Toast.LENGTH_SHORT).show()
                }
            })
    }

    override fun onFavoriteSchool(school: School) {
        schoolService.addToFavorite(school.libelle)
            .enqueue(object : Callback<School>{
                override fun onResponse(call: Call<School>, response: Response<School>) {
                    response.body()?.let {schoolList.addTofavorite(it)}
                    displaySchoolListFragment()
                }
                override fun onFailure(call: Call<School>, t: Throwable) {
                    Toast.makeText(applicationContext, "fail created mdr", Toast.LENGTH_SHORT).show()
                }
            })

    }

    private fun onDisplayMaps() {
        schoolService.getAllSchool()
            .enqueue(object : Callback<List<School>> {
                override fun onResponse(
                    call: Call<List<School>>,
                    response: Response<List<School>>
                ) {
                    val getAllMuseums: List<School>? = response.body() as ArrayList<School>
                    println(getAllMuseums)
                    getAllMuseums?.forEach { schoolList.addSchool(it) }
                    val mapFragment =
                        supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
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

                        if (getAllMuseums != null) {
                            getAllMuseums.forEach {
                                val location = LatLng(it.geolocalisation[0], it.geolocalisation[1])
                                mMap.addMarker(MarkerOptions().position(location).title(it.libelle))
                                displaySchoolMapsFragment()
                            }
                        }

                    }
                    displaySchoolMapsFragment()
                }

                override fun onFailure(call: Call<List<School>>, t: Throwable) {
                    error("KO")

                }
            })

        displaySchoolMapsFragment()
    }

*/
    /**private fun onDisplayMaps() {
        schoolService.getAllSchool()
            .enqueue(object : Callback<List<School>> {
                override fun onResponse(
                    call: Call<List<School>>,
                    response: Response<List<School>>
                ) {
                    val getAllSchools: List<School>? = response.body() as ArrayList<School>
                    println(getAllSchools)
                    getAllSchools?.forEach { schoolList.addSchool(it) }
                    val mapFragment =
                        supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
                    mapFragment?.getMapAsync { mMap ->
                        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL

                        mMap.clear() //clear old markers

                        /*val googlePlex = CameraPosition.builder()
                            .target(LatLng( 36.806389, 10.181667))
                            .zoom(10f)
                            .bearing(0f)
                            .tilt(45f)
                            .build()
                        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null)*/
                        if(getAllSchools != null) {
                            getAllSchools?.forEach {
                                val location = LatLng(it.geolocalisation[0], it.geolocalisation[1])
                                mMap.addMarker(MarkerOptions().position(location).title(it.libelle))
                            }
                        }


                    }
                }

                override fun onFailure(call: Call<List<School>>, t: Throwable) {
                    error("KO")
                }
            })
        displaySchoolMapsFragment()
    }*/

