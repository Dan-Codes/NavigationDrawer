package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.view.get
import com.example.myapplication.movieList
import com.example.myapplication.ui.gallery.GalleryFragment
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_list_view.*
import kotlinx.android.synthetic.main.fragment_list_view.*
var movieList: List<MovieData> = Gson().fromJson(movies, Array<MovieData>::class.java).asList()
class ListViewActivity : AppCompatActivity(), GalleryFragment.OnListViewInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)
        val fragment   = GalleryFragment()
        val view = movieList

        //val toolbar: Toolbar = findViewById(R.id.toolbar2)
        setSupportActionBar(toolbar2)
        //toolbar.title = "MOVIE LIST"
        supportFragmentManager.beginTransaction().replace(view.id, fragment).commit()
    }

    override fun onListViewInteraction(movie: MovieData, position: Int, view: View) {
        Log.d("test", "pressed")
        val title = movie.title
        val posterid = posterTable[title]
        val fragment = MovieFragment.newInstance(movie, posterid!!)
        val view = movieList
        supportFragmentManager.beginTransaction().replace(view.id, fragment).addToBackStack(null).commit()

    }


}
