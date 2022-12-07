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
import com.squareup.picasso.Picasso


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
        val school = schools[position];

        holder.libelle.text = school.libelle
        holder.secteur.text = school.secteur
        holder.vague.text = school.vague

        when (holder.vague.text){
            "Vague A" -> {val url = "https://pngimg.com/uploads/letter_a/letter_a_PNG19.png"
                Picasso.get().load(url).into(holder.image);}
            "Vague B" -> {val url = "https://pngimg.com/uploads/letter_b/letter_b_PNG14.png"
                Picasso.get().load(url).into(holder.image);}
            "Vague C" -> {val url = "https://pngimg.com/uploads/letter_c/letter_c_PNG8.png"
                Picasso.get().load(url).into(holder.image);}
            "Vague D" -> {val url = "https://pngimg.com/uploads/letter_d/letter_d_PNG12.png"
                Picasso.get().load(url).into(holder.image);}
            " " -> {val url = "https://pngimg.com/uploads/question_mark/question_mark_PNG10.png"
                Picasso.get().load(url).into(holder.image);}
        }

        holder.favorite.setOnClickListener{
            school.favorite = !school.favorite
            schoolFavorite?.onFavoriteSchool(school)
            addToFavorite(holder, school.favorite)

        }

        holder.itemView.setOnClickListener {
            val intent = Intent(context, SchoolInfosDetails::class.java)
            intent.putExtra("sigle",school.sigle)
            intent.putExtra("libelle",school.libelle)
            intent.putExtra("secteur",school.secteur)
            intent.putExtra("vague",school.vague)
            intent.putExtra("region",school.region)
            intent.putExtra("adresse",school.adresse)
            intent.putExtra("num",school.numero_telephone)

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


