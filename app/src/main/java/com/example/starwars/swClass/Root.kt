package com.example.starwars.swClass

import com.example.starwars.R

class Root(
    val people: String,
    val planets: String,
    val films: String,
    val species: String,
    val vehicles: String,
    val starships: String
) {

}

class MainList(

) {
    val listName = listOf(
        "People",
        "Planets",
        "Films",
        "Species",
        "Vehicles",
        "Starships"
    )
    val listImage = listOf(
        R.drawable.main_vaider,
        R.drawable.main_planet,
        R.drawable.main_film,
        R.drawable.main_species,
        R.drawable.main_vicheles,
        R.drawable.main_spiceship
    )
}

class Peoples(
    val next: String?,
    var results: List<People>
)

class People(
    val name: String,
    val url:String,
    val birth_year: String,
    val gender: String,
    val homeworld: String,
    val films: List<String>,
    val species: List<String>,
    val vehicles: List<String>,
    val starships: List<String>
)

class Planets(val next: String?, var results: List<Planet>)

class Planet(
    val name: String,
    val url:String,
    val rotation_period:String,
    val orbital_period:String,
    val diameter:String,
    val terrain:String,
    val surface_water:String,
    val climate: String,
    val population: String,
    val residents:List<String>,
    val films:List<String>
)

class Films(val next: String?,var results:List<Film>)

class Film(
    val title:String,
    val episode_id:String,
    val opening_crawl:String,
    val director:String,
    val producer:String,
    val release_date:String,
    val characters:List<String>,
    val planets:List<String>,
    val starships:List<String>,
    val vehicles:List<String>,
    val species:List<String>,
    val url:String
)

class Species(val next:String?,var results:List<Specie>)

class Specie(
    val name:String,
    val classification:String,
    val designation:String,
    val average_height:String,
    val skin_colors:String,
    val hair_colors:String,
    val eye_colors:String,
    val average_lifespan:String,
    val homeworld:String,
    val language:String,
    val people:List<String>,
    val films:List<String>,
    val url:String
)

class Vehicles(val next:String?,var results:List<Vehicle>)

class Vehicle(
    val name: String,
    val model:String,
    val manufacturer:String,
    val cost_in_credits:String,
    val length:String,
    val max_atmosphering_speed:String,
    val crew:String,
    val passengers:String,
    val cargo_capacity:String,
    val consumables:String,
    val vehicle_class:String,
    val pilots:List<String>?,
    val films:List<String>,
    val url:String

)

class Starships(val next: String?,var results:List<Starship>)

class Starship(
    val name: String,
    val model:String,
    val manufacturer:String,
    val cost_in_credits:String,
    val length:String,
    val max_atmosphering_speed:String,
    val crew:String,
    val passengers:String,
    val cargo_capacity:String,
    val consumables:String,
    val hyperdrive_rating:String,
    val MGLT:String,
    val starships_class:String,
    val pilots:List<String>,
    val films: List<String>,
    val url: String
)