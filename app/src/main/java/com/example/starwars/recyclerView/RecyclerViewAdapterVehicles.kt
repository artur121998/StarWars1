package com.example.starwars.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import com.example.starwars.swClass.Specie
import com.example.starwars.swClass.Species
import com.example.starwars.swClass.Vehicle
import com.example.starwars.swClass.Vehicles
import kotlinx.android.synthetic.main.main_row.view.*

class RecyclerViewAdapterVehicles(private val vehicles: Vehicles): RecyclerView.Adapter<CustomSecondViewHolder>() {

    override fun getItemCount(): Int {
        return vehicles.results.count()
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomSecondViewHolder {
        val layoutInflater=LayoutInflater.from(p0.context)
        val cellForRow = layoutInflater.inflate(R.layout.main_row_second,p0,false)
        return CustomSecondViewHolder(cellForRow)
    }


    override fun onBindViewHolder(p0: CustomSecondViewHolder, p1: Int) {
        val swTitle= vehicles.results[p1].name
        p0.view.tvTitle.text=swTitle
        p0.url=vehicles.results[p1].url
        p0.name="vehicles"
    }

    fun addOnList(pl: List<Vehicle>){
        vehicles.results=vehicles.results+pl
        notifyDataSetChanged()
    }
}



