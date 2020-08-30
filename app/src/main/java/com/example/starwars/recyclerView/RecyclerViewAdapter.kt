package com.example.starwars.recyclerView

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.starwars.R
import com.example.starwars.activity.SecondActivity
import com.example.starwars.swClass.MainList
import com.example.starwars.swClass.Root
import kotlinx.android.synthetic.main.main_row.view.*

class RecyclerViewAdapter(private val root: Root): RecyclerView.Adapter<CustomViewHolder>() {

private val mainMap=MainList()

    override fun getItemCount(): Int {
        return mainMap.listName.count()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {
        val layoutInflater=LayoutInflater.from(p0.context)
        val cellForRow = layoutInflater.inflate(R.layout.main_row,p0,false)
        return CustomViewHolder(cellForRow)
    }


    override fun onBindViewHolder(p0: CustomViewHolder, p1: Int) {
        val swTitle= mainMap.listName[p1]
        val imageView=p0.view.findViewById<ImageView>(R.id.iv)
        p0.view.tvTitle.text=swTitle
        Glide.with(p0.view.context).load(mainMap.listImage[p1]).centerCrop().into(imageView)
        var url=""
        when(swTitle){
            mainMap.listName[0]->{url=root.people}
            mainMap.listName[1]->{url=root.planets}
            mainMap.listName[2]->{url=root.films}
            mainMap.listName[3]->{url=root.species}
            mainMap.listName[4]->{url=root.vehicles}
            mainMap.listName[5]->{url=root.starships}
        }
        p0.record=swTitle
        p0.url=url
    }
}

class CustomViewHolder(val view: View, var record:String?=null,var url:String?=null):RecyclerView.ViewHolder(view){

    companion object{
        val KEY_MAIN="KEY"
        val KEY_URL_MAIN="KEY_URL"
    }

    init {
        view.setOnClickListener {
            val intent= Intent(view.context,
                SecondActivity::class.java)
            intent.putExtra(KEY_MAIN,record)
            intent.putExtra(KEY_URL_MAIN,url)
            view.context.startActivity(intent)
        }
    }
}