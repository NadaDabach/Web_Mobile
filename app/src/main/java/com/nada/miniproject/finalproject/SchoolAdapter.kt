package com.nada.miniproject.finalproject

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class SchoolAdapter(private var schools: List<School>) : RecyclerView.Adapter<SchoolViewHolder>()  {

    private lateinit var context: Context
    private var schoolFavorite: SchoolFavorite? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(
            R.layout.row_school, parent,
            false
        )
        context=parent.context
        return SchoolViewHolder(row)
    }

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        /*val (
            libelle,
            sigle,
            type,
            secteur,
            vague,
            geolocalisation,
            date,
            departement,
            region,
            adresse,
            code_postal,
            numero_telephone,
            site_web,
            compte_fb,
            compte_twitter,
            compte_insta,
            favorite,
            imageResource
        ) = schools[position]*/

        val school = schools[position];

        //holder.image.setImageResource(school.image)
        holder.libelle.text = school.libelle
        holder.secteur.text = school.secteur
        holder.favorite.setOnClickListener{
            school.favorite = !school.favorite
            addToFavorite(holder, school.favorite)
            schoolFavorite?.onFavoriteSchool(school)
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(context, SchoolInfosDetails::class.java)
            intent.putExtra("libelle",school.libelle)
            intent.putExtra("secteur",school.secteur)
            intent.putExtra("adresse",school.adresse)
            //intent.putExtra("date",school.date)
            //intent.putExtra("dep",school.departement)
            //intent.putExtra("tel",school.numero_telephone)

            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return schools.size
    }

    private fun addToFavorite(holder: SchoolViewHolder, favorite: Boolean){
        var btnFavorite: Int
        if(favorite){
            btnFavorite = R.drawable.ic_starred
        }
        else{
            btnFavorite = R.drawable.ic_favorite
        }
        holder.favorite.setImageDrawable(ContextCompat.getDrawable(context, btnFavorite))
    }

}


