package com.example.starwars.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.starwars.R
import com.example.starwars.recyclerView.*
import com.example.starwars.swClass.*
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_page.*
import okhttp3.*
import java.io.IOException
import java.util.*

class Page : AppCompatActivity() {

    var planet: Planet? = null
    var film: Film? = null
    var specie: Specie? = null
    var empty = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page)
        constraintLayout.setBackgroundColor(Color.BLACK)
        fetchJSON()
    }

    private fun fetchJSON() {
        val url = intent.getStringExtra(CustomSecondViewHolder.KEY_URL)
        val name = intent.getStringExtra(CustomSecondViewHolder.KEY_NAME)
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
                            val result = gson.fromJson(body, People::class.java)
                            tvName.text = result.name
                            tv1Text.text = "Home world"
                            tv2Text.text = "Birth Day"
                            tv3Text.text = "Gender"
                            tv4Text.text = "Films"
                            tv5Text.text = "Species"
                            tv2Value.text = result.birth_year
                            tv3Value.text = result.gender
                            val requestHomeWorld = Request.Builder().url(result.homeworld).build()
                            val clientHomeWorld = OkHttpClient()
                            clientHomeWorld.newCall(requestHomeWorld).enqueue(object : Callback {
                                override fun onFailure(call: Call, e: IOException) {
                                    print("Failed to execute request")
                                }

                                override fun onResponse(call: Call, response: Response) {
                                    val body = response.body?.string()
                                    val gson = GsonBuilder().create()
                                    val planet = gson.fromJson(body, Planet::class.java)
                                    runOnUiThread {
                                        tv1Value.text = planet.name
                                    }

                                }
                            })  //получение данных о связаной планете
                            result.films.forEach { s ->
                                val requestFilm = Request.Builder().url(s).build()
                                val clientFilm = OkHttpClient()
                                clientFilm.newCall(requestFilm).enqueue(object : Callback {
                                    override fun onFailure(call: Call, e: IOException) {
                                        print("Failed to execute request")
                                    }

                                    override fun onResponse(call: Call, response: Response) {
                                        val body = response.body?.string()
                                        val gson = GsonBuilder().create()
                                        val film = gson.fromJson(body, Film::class.java)
                                        runOnUiThread {
                                            tv4Value.append(film.title + "\n")
                                        }

                                    }
                                })
                            }                                                 //получение данных о связаных фильмах
                            result.species.forEach { s ->
                                val requestSpecies = Request.Builder().url(s).build()
                                val clientSpecies = OkHttpClient()
                                clientSpecies.newCall(requestSpecies).enqueue(object : Callback {
                                    override fun onFailure(call: Call, e: IOException) {
                                        print("Failed to execute request")
                                    }

                                    override fun onResponse(call: Call, response: Response) {
                                        val body = response.body?.string()
                                        val gson = GsonBuilder().create()
                                        val specie = gson.fromJson(body, Specie::class.java)
                                        runOnUiThread {
                                            tv4Value.append(specie.name + "\n")
                                        }

                                    }
                                })
                            }                                               //получение данных о связанных расах


                        }
                    }
                    "planets" -> {
                        runOnUiThread {
                            val result = gson.fromJson(body, Planet::class.java)
                            tvName.text = result.name
                            tv1Text.text = "Population"
                            tv2Text.text = "Climate"
                            tv3Text.text = "Terrain"
                            tv4Text.text = "Films"
                            tv5Text.text = "Residents"
                            tv1Value.text = result.population
                            tv2Value.text = result.climate
                            tv3Value.text = result.terrain
                            result.films.forEach { s ->
                                val requestFilm = Request.Builder().url(s).build()
                                val clientFilm = OkHttpClient()
                                clientFilm.newCall(requestFilm).enqueue(object : Callback {
                                    override fun onFailure(call: Call, e: IOException) {
                                        print("Failed to execute request")
                                    }

                                    override fun onResponse(call: Call, response: Response) {
                                        val body = response.body?.string()
                                        val gson = GsonBuilder().create()
                                        val film = gson.fromJson(body, Film::class.java)
                                        runOnUiThread {
                                            tv4Value.append(film.title + "\n")
                                        }

                                    }
                                })
                            }
                            result.residents.forEach { s ->
                                val requestResidents = Request.Builder().url(s).build()
                                val clientResidents = OkHttpClient()
                                clientResidents.newCall(requestResidents).enqueue(object : Callback {
                                    override fun onFailure(call: Call, e: IOException) {
                                        print("Failed to execute request")
                                    }

                                    override fun onResponse(call: Call, response: Response) {
                                        val body = response.body?.string()
                                        val gson = GsonBuilder().create()
                                        val people = gson.fromJson(body, People::class.java)
                                        runOnUiThread {
                                            tv5Value.append(people.name + "\n")
                                        }

                                    }
                                })
                            }
                        }
                    }
                    "films" -> {
                        val result = gson.fromJson(body, Film::class.java)
                        runOnUiThread {
                        }
                    }
                    "species" -> {
                        val result = gson.fromJson(body, Specie::class.java)
                        runOnUiThread {

                        }
                    }
                    "vehicles" -> {
                        val result = gson.fromJson(body, Vehicle::class.java)
                        runOnUiThread {
                        }
                    }
                    "starships" -> {
                        val result = gson.fromJson(body, Starship::class.java)
                        runOnUiThread {
                        }
                    }
                }
            }
        })
    }
}
