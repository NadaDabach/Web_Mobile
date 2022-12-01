package com.nada.miniproject.finalproject

import android.content.Intent
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

const val SERVER_BASE_URL = "https://app-cb40d835-d4b4-407f-83a6-0452ebe04576.cleverapps.io"

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(SERVER_BASE_URL)
        .build()

    val schoolService = retrofit.create(SchoolService::class.java)

    private val schoolList = Schools()

    private val btnCreateSchool: FloatingActionButton by lazy { findViewById(R.id.btn_create_school) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        schoolService.getAllBooks()
            .enqueue(object : Callback<List<School>> {
                override fun onResponse(
                    call: Call<List<School>>,
                    response: Response<List<School>>
                ) {
                    val getAllBooks: List<School>? = response.body()
                    getAllBooks?.forEach { schoolList.addSchool(it) }
                    displaySchoolListFragment()
                }

                override fun onFailure(call: Call<List<School>>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(applicationContext, "It fails with error", Toast.LENGTH_SHORT).show()
                }
            })

        displaySchoolListFragment()

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navigation_bar_home -> replaceFragment(HomeFragment())
                R.id.navigation_bar_map -> replaceFragment(MapsFragment())
                R.id.navigation_bar_about -> replaceFragment(AboutFragment())
                else ->{
                }
            }
            true
        }

        btnCreateSchool.setOnClickListener {
            navigateToAddSchoolActivity()
        }

    }

    private fun replaceFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }

    private fun navigateToAddSchoolActivity(){
        startActivity(Intent(applicationContext, AddSchool::class.java))
        finish()
        btnCreateSchool.hide()
    }

    private fun displaySchoolListFragment() {
        val schoolListFragment = HomeFragment.newInstance(schoolList.getAllSchools())
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, schoolListFragment)
            .commit()
        btnCreateSchool.show()
    }

    /*private fun addSchool(holder: SchoolViewHolder, school: School){
        schoolService.createBook(school).enqueue(object: Callback<School>{
            override fun onResponse(call: Call<School>, response: Response<School>) {
                val createSchool: List<School>? = response.body()
                createSchool?.forEach { schoolList.addSchool(it) }
            }

            override fun onFailure(call: Call<School>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }*/
}