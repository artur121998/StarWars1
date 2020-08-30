package com.example.starwars.activity

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import com.example.starwars.recyclerView.*
import com.example.starwars.swClass.*
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException
import java.util.*
import kotlin.properties.Delegates


class SecondActivity : AppCompatActivity() {
    lateinit var linearLayoutManager: LinearLayoutManager
    private val lastVisibleItemPosition: Int get() = linearLayoutManager.findLastVisibleItemPosition()
    private val firstVisibleItemPosition: Int get() = linearLayoutManager.findFirstVisibleItemPosition()
    var resultMemory = mutableListOf<Planet>()
    var numberPage = 1
    var url = ""
    var name = ""
    var isLastPage = false
    var isLoading by Delegates.notNull<Boolean>()
    var isNext by Delegates.notNull<Boolean>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.setBackgroundColor(Color.BLACK)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        name = intent.getStringExtra(CustomViewHolder.KEY_MAIN)!!
        supportActionBar?.title = name
        url = intent.getStringExtra(CustomViewHolder.KEY_URL_MAIN)!!
        fetchJSON(name)

        setRecyclerViewScrollListener()

    }

    private fun setRecyclerViewScrollListener() {
        val scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                isNext = true
                val totalItemCount = recyclerView.layoutManager!!.itemCount

                if (isNext) {
                    if (totalItemCount == lastVisibleItemPosition + 1 && !isLastPage && !isLoading) {
                        fetchJSON(name = name)
                    }
                }
            }
        }
        recyclerView.addOnScrollListener(scrollListener)
    }

    fun fetchJSON(name: String) {
        isLoading = true
        url = intent.getStringExtra(CustomViewHolder.KEY_URL_MAIN)!!
        url = "$url?page=${numberPage++}"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                print("Failed to execute request")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                val gson = GsonBuilder().create()

                when (name.toLowerCase(Locale.ROOT)) {
                    "people" -> {
                        runOnUiThread {
                            val result = gson.fromJson(body, Peoples::class.java)
                            isLastPage = result.next == null
                            val adapter =
                                (recyclerView.adapter as? RecyclerViewAdapterPeoples) //тут другая будет активити
                            if (adapter == null) {
                                recyclerView.adapter = RecyclerViewAdapterPeoples(result)
                            } else {
                                adapter.addOnList(result.results)
                            }
                            isLoading = false
                        }

                    }
                    "planets" -> {
                        val result = gson.fromJson(body, Planets::class.java)
                        isLastPage = result.next == null
                        runOnUiThread {
                            val adapter = (recyclerView.adapter as? RecyclerViewAdapterPlanets)
                            if (adapter == null) {
                                recyclerView.adapter = RecyclerViewAdapterPlanets(result)
                            } else {
                                adapter.addOnList(result.results)
                            }
                            isLoading = false
                        }
                    }
                    "films" -> {
                        val result = gson.fromJson(body, Films::class.java)
                        isLastPage = result.next == null
                        runOnUiThread {
                            val adapter = (recyclerView.adapter as? RecyclerViewAdapterFilms)
                            if (adapter == null) {
                                recyclerView.adapter = RecyclerViewAdapterFilms(result)
                            } else {
                                adapter.addOnList(result.results)
                            }
                            isLoading = false
                        }
                    }
                    "species" -> {
                        val result = gson.fromJson(body, Species::class.java)
                        isLastPage = result.next == null
                        runOnUiThread {
                            val adapter = (recyclerView.adapter as? RecyclerViewAdapterSpecies)
                            if (adapter == null) {
                                recyclerView.adapter = RecyclerViewAdapterSpecies(result)
                            } else {
                                adapter.addOnList(result.results)
                            }
                            isLoading = false
                        }
                    }
                    "vehicles" -> {
                        val result = gson.fromJson(body, Vehicles::class.java)
                        isLastPage = result.next == null
                        runOnUiThread {
                            val adapter = (recyclerView.adapter as? RecyclerViewAdapterVehicles)
                            if (adapter == null) {
                                recyclerView.adapter = RecyclerViewAdapterVehicles(result)
                            } else {
                                adapter.addOnList(result.results)
                            }
                            isLoading = false
                        }
                    }
                    "starships" -> {
                        val result = gson.fromJson(body, Starships::class.java)
                        isLastPage = result.next == null
                        runOnUiThread {
                            val adapter =
                                (recyclerView.adapter as? RecyclerViewAdapterStarships)
                            if (adapter == null) {
                                recyclerView.adapter = RecyclerViewAdapterStarships(result)
                            } else {
                                adapter.addOnList(result.results)
                            }
                            isLoading = false
                        }
                    }
                }
                println(isLastPage)
            }
        })

    }
}