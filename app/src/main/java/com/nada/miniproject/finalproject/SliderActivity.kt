package com.nada.miniproject.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton

class SliderActivity : AppCompatActivity() {

    private lateinit var sliderItemsAdapter: SliderItemsAdapter
    private lateinit var indicatorsContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slider)
        setSliderItems()
        setupIndicators()
        setCurrentIndicator(0)

    }

    private fun setSliderItems(){
        sliderItemsAdapter = SliderItemsAdapter(
            listOf(
                SliderItem(
                    sliderImage = R.drawable.logo,
                    title = "Title 1",
                    description = "Description 1"
                ),
                SliderItem(
                    sliderImage = R.drawable.logo,
                    title = "Title 2",
                    description = "Description 2"
                ),
                SliderItem(
                    sliderImage = R.drawable.logo,
                    title = "Title 3",
                    description = "Description 3"
                )
            )
        )
        val sliderViewPager = findViewById<ViewPager2>(R.id.sliderViewPager)
        sliderViewPager.adapter = sliderItemsAdapter
        sliderViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
            })
        (sliderViewPager.getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        findViewById<ImageView>(R.id.imageNext).setOnClickListener{
            if(sliderViewPager.currentItem + 1 < sliderItemsAdapter.itemCount){
                sliderViewPager.currentItem += 1
            }else {
                navigateToMainActivity()
            }
        }
        findViewById<TextView>(R.id.textSkip).setOnClickListener{
            navigateToMainActivity()
        }
        findViewById<MaterialButton>(R.id.getStartedButton).setOnClickListener{
            navigateToMainActivity()
        }
    }

    private fun navigateToMainActivity(){
        startActivity(Intent(applicationContext, MainActivity::class.java))
        finish()
    }

    private fun setupIndicators(){
        indicatorsContainer = findViewById(R.id.indicatorsContainer)
        val indicators = arrayOfNulls<ImageView>(sliderItemsAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for(i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
                it.layoutParams = layoutParams
                indicatorsContainer.addView(it)
            }
        }
    }

    private fun setCurrentIndicator(position: Int){
        val childCount = indicatorsContainer.childCount
        for(i in 0 until childCount){
            val imageView = indicatorsContainer.getChildAt(i) as ImageView
            if (i == position){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active_background
                    )
                )
            }else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
            }
        }
    }
}