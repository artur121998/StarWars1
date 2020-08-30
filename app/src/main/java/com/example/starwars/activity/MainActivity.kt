package com.example.starwars.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwars.R
import com.example.starwars.recyclerView.RecyclerViewAdapter
import com.example.starwars.swClass.Root
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.setBackgroundColor(Color.BLACK)
        recyclerView.layoutManager=LinearLayoutManager(this)

        fetchJSON()
    }

    private fun fetchJSON(){
        val url="https://swapi.dev/api/"
        val request= Request.Builder().url(url).build()
        val client=OkHttpClient()
        client.newCall(request).enqueue(object:Callback{
            override fun onFailure(call: Call, e: IOException) {
                print("Failed to execute request")
            }

            override fun onResponse(call: Call, response: Response) {
              val body = response.body?.string()
                val gson= GsonBuilder().create()
                val root=gson.fromJson(body, Root::class.java)
                runOnUiThread{
                    recyclerView.adapter=
                        RecyclerViewAdapter(root)
                }
            }

        })
    }


   /* class Planets(val results:List<Planet>)

    class Planet(val name:String)*/
}
