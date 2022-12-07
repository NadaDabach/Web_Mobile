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
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        val searchItem = menu?.findItem(R.id.menu_search)

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
                    Toast.makeText(applicationContext, "fail created", Toast.LENGTH_SHORT).show()
                }
            })

    }

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
                    val getAllSchools: List<School>? = response.body() as ArrayList<School>
                    getAllSchools?.forEach { schools.addSchool(it) }
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
                }

                override fun onFailure(call: Call<List<School>>, t: Throwable) {
                    error("Maps failed to launch")
                }
            })
    }
}
