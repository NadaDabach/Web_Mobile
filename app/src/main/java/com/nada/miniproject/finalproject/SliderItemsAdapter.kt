package com.nada.miniproject.finalproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SliderItemsAdapter(private val sliderItems: List<SliderItem>):
    RecyclerView.Adapter<SliderItemsAdapter.SliderItemViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderItemViewHolder {
        return SliderItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.slider_item_container,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SliderItemViewHolder, position: Int) {
        holder.bind(sliderItems[position])
    }

    override fun getItemCount(): Int {
        return sliderItems.size
    }

    inner class SliderItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val imageSlider = view.findViewById<ImageView>(R.id.imageOnSlider)
        private val textTitle = view.findViewById<TextView>(R.id.textTitle)
        private val textDescription = view.findViewById<TextView>(R.id.textDescription)

        fun bind(sliderItem: SliderItem){
            imageSlider.setImageResource(sliderItem.sliderImage)
            textTitle.text = sliderItem.title
            textDescription.text = sliderItem.description
        }
    }



}