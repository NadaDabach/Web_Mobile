package com.nada.miniproject.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nada.miniproject.finalproject.databinding.ActivityMainBinding

const val SERVER_BASE_URL = "https://app-cb40d835-d4b4-407f-83a6-0452ebe04576.cleverapps.io/schools"

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    private val btnCreateSchool: FloatingActionButton by lazy { findViewById(R.id.btn_create_school) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){

                R.id.navigation_bar_home -> replaceFragment(HomeFragment())
                R.id.navigation_bar_map -> replaceFragment(MapFragment())
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
}