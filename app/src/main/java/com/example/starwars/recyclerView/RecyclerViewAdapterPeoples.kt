package com.example.starwars.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import com.example.starwars.swClass.People
import com.example.starwars.swClass.Peoples
import com.example.starwars.swClass.Planet
import com.example.starwars.swClass.Planets
import kotlinx.android.synthetic.main.main_row.view.*

class RecyclerViewAdapterPeoples(private val peoples: Peoples): RecyclerView.Adapter<CustomSecondViewHolder>() {

    override fun getItemCount(): Int {
        return peoples.results.count()
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomSecondViewHolder {
        val layoutInflater=LayoutInflater.from(p0.context)
        val cellForRow = layoutInflater.inflate(R.layout.main_row_second,p0,false)
        return CustomSecondViewHolder(cellForRow)
    }


    override fun onBindViewHolder(p0: CustomSecondViewHolder, p1: Int) {
        val swTitle= peoples.results[p1].name
        p0.view.tvTitle.text=swTitle
        p0.url=peoples.results[p1].url
        p0.name="people"
    }

    fun addOnList(pl: List<People>){
        peoples.results=peoples.results+pl
        notifyDataSetChanged()
    }
}



