package com.example.myapplication
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.io.Serializable
data class MovieData(
    val vote_count: Int?,
    val id: Int?,
    val video: Boolean?,
    val vote_average: Double?,
    var title: String?,
    val popularity: Double?,
    val poster_path: String?,
    val original_language: String?,
    val original_title: String?,
    val genre_ids: List<Int?>?,
    val backdrop_path: String?,
    val overview: String?,
    val release_date: String?,
    var poster_id: Int?,
    var genres: String?,
    var checked: Boolean?
) : Serializable

class MovieList {
    val movieList: List<MovieData> = Gson().fromJson(movies, Array<MovieData>::class.java).asList()
    val posterTable: MutableMap<String, Int> = mutableMapOf()
    val genreTable: MutableMap<Int, String> = mutableMapOf()
    init {
// create poster table mapping title and image id
        posterTable["It Chapter Two"] = R.drawable.it
        posterTable["Spider-Man: Far from Home"] = R.drawable.spiderman
        posterTable["The Old Man & the Gun"] = R.drawable.old
        posterTable["Hustlers"] = R.drawable.hustlers
        posterTable["John Wick: Chapter 3 – Parabellum"] = R.drawable.johnwick
        for(movie in movieList){
            movie.poster_id = posterTable[movie.title]
            var g = ""
            for(i in movie.genre_ids!!){
                g += genreTable[i]
            }
            movie.genres = g
            movie.checked = false
        }
    }
}

val movies =""" [
    {
        "popularity": 403.64,
        "vote_count": 663,
        "video": false,
        "poster_path": "/zfE0R94v1E8cuKAerbskfD3VfUt.jpg",
        "id": 474350,
        "adult": false,
        "backdrop_path": "/4W0FnjSGn4x0mKZlBRx8OjFxQUM.jpg",
        "original_language": "en",
        "original_title": "It Chapter Two",
        "genre_ids": [
        35,
        27
        ],
        "title": "It Chapter Two",
        "vote_average": 7.2,
        "overview": "27 years after overcoming the malevolent supernatural entity Pennywise, the former members of the Losers' Club, who have grown up and moved away from Derry, are brought back together by a devastating phone call.",
        "release_date": "2019-09-06"
    },
    {
        "popularity": 357.172,
        "vote_count": 3548,
        "video": false,
        "poster_path": "/lcq8dVxeeOqHvvgcte707K0KVx5.jpg",
        "id": 429617,
        "adult": false,
        "backdrop_path": "/5myQbDzw3l8K9yofUXRJ4UTVgam.jpg",
        "original_language": "en",
        "original_title": "Spider-Man: Far from Home",
        "genre_ids": [
        28,
        12,
        878
        ],
        "title": "Spider-Man: Far from Home",
        "vote_average": 7.7,
        "overview": "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.",
        "release_date": "2019-07-02"
    },
    {
        "popularity": 261.026,
        "vote_count": 512,
        "video": false,
        "poster_path": "/a4BfxRK8dBgbQqbRxPs8kmLd8LG.jpg",
        "id": 429203,
        "adult": false,
        "backdrop_path": "/6X2YjjYcs8XyZRDmJAHNDlls7L4.jpg",
        "original_language": "en",
        "original_title": "The Old Man & the Gun",
        "genre_ids": [
        35,
        80,
        18
        ],
        "title": "The Old Man & the Gun",
        "vote_average": 6.4,
        "overview": "The true story of Forrest Tucker, from his audacious escape from San Quentin at the age of 70 to an unprecedented string of heists that confounded authorities and enchanted the public. Wrapped up in the pursuit are a detective, who becomes captivated with Forrest’s commitment to his craft, and a woman, who loves him in spite of his chosen profession.",
        "release_date": "2018-09-28"
    },
    {
        "popularity": 228.407,
        "vote_count": 76,
        "video": false,
        "poster_path": "/zBhv8rsLOfpFW2M5b6wW78Uoojs.jpg",
        "id": 540901,
        "adult": false,
        "backdrop_path": "/mBBJ3N3an8FLkp0ZpGgIJwHKhBP.jpg",
        "original_language": "en",
        "original_title": "Hustlers",
        "genre_ids": [
        35,
        80,
        18
        ],
        "title": "Hustlers",
        "vote_average": 6.5,
        "overview": "A crew of savvy former strip club employees band together to turn the tables on their Wall Street clients.",
        "release_date": "2019-09-13"
    },
    {
        "popularity": 130.017,
        "vote_count": 2485,
        "video": false,
        "poster_path": "/ziEuG1essDuWuC5lpWUaw1uXY2O.jpg",
        "id": 458156,
        "adult": false,
        "backdrop_path": "/stemLQMLDrlpfIlZ5OjllOPT8QX.jpg",
        "original_language": "en",
        "original_title": "John Wick: Chapter 3 – Parabellum",
        "genre_ids": [
        28,
        80,
        53
        ],
        "title": "John Wick: Chapter 3 – Parabellum",
        "vote_average": 7.1,
        "overview": "Super-assassin John Wick returns with a $14 million price tag on his head and an army of bounty-hunting killers on his trail. After killing a member of the shadowy international assassin’s guild, the High Table, John Wick is excommunicado, but the world’s most ruthless hit men and women await his every turn.",
        "release_date": "2019-05-17"
    }
    
    ] """.trimIndent()