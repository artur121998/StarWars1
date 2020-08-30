package com.example.starwars.recyclerView

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.activity.Page

class CustomSecondViewHolder(val view: View, var url:String?=null, var name:String?=null): RecyclerView.ViewHolder(view) {

    companion object {
        val KEY_URL = "KEY_URL"
        val KEY_NAME = "KEY_NAME"
    }

    init {
        view.setOnClickListener {
            val intent = Intent(view.context, Page::class.java)
            intent.putExtra(KEY_URL, url)
            intent.putExtra(KEY_NAME, name)
            view.context.startActivity(intent)
        }
    }
}