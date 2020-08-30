package com.example.starwars.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import com.example.starwars.swClass.Film
import com.example.starwars.swClass.Films
import com.example.starwars.swClass.Planet
import com.example.starwars.swClass.Planets
import kotlinx.android.synthetic.main.main_row.view.*

class RecyclerViewAdapterFilms(private val films: Films): RecyclerView.Adapter<CustomSecondViewHolder>() {

    override fun getItemCount(): Int {
        return films.results.count()
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomSecondViewHolder {
        val layoutInflater=LayoutInflater.from(p0.context)
        val cellForRow = layoutInflater.inflate(R.layout.main_row_second,p0,false)
        return CustomSecondViewHolder(cellForRow)
    }


    override fun onBindViewHolder(p0: CustomSecondViewHolder, p1: Int) {
        val swTitle= films.results[p1].title
        p0.view.tvTitle.text=swTitle
        p0.url=films.results[p1].url
        p0.name="films"
    }

    fun addOnList(pl: List<Film>){
        films.results=films.results+pl
        notifyDataSetChanged()
    }
}



