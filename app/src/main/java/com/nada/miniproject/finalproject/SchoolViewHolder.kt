package com.nada.miniproject.finalproject

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class SchoolViewHolder(rootView: View): ViewHolder(rootView) {
    var image = rootView.findViewById<ImageView>(R.id.title_image)
    var libelle = rootView.findViewById<TextView>(R.id.school_txv_label)
    var secteur = rootView.findViewById<TextView>(R.id.school_txv_secteur_etablissement)
    var vague = rootView.findViewById<TextView>(R.id.school_txv_vague_etablissement)
    var favorite = rootView.findViewById<ImageButton>(R.id.btn_favorite)
}