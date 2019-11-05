package com.example.myapplication

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson


val posterTable: MutableMap<String, Int> = mutableMapOf()
class MyMovieListViewAdapter(val items : ArrayList<MovieData>, val type: Int) : BaseAdapter() {


    val genreTable: MutableMap<Int, String> = mutableMapOf()
    init {
// create poster table mapping title and image id
        posterTable["It Chapter Two"] = R.drawable.it
        posterTable["Spider-Man: Far from Home"] = R.drawable.spiderman
        posterTable["The Old Man & the Gun"] = R.drawable.old
        posterTable["Hustlers"] = R.drawable.hustlers
        posterTable["John Wick: Chapter 3 – Parabellum"] = R.drawable.johnwick
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val context = parent!!.context
        var view: View? = convertView
        val viewHolder: ViewHolder
        if (view == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.mylistlayout, parent, false)
            viewHolder = ViewHolder(view!!)
            view.tag = viewHolder
        }
        else {
            viewHolder = view.tag as ViewHolder
        }
        val movie = items[position]
        viewHolder.img.setImageResource(posterTable[movie.title]!!)
        viewHolder.txt1.text = movie.title!!
        var length = movie.overview!!.length
        length = if (length > 150) 150 else length
        viewHolder.txt2.text = movie.overview.substring(0, length - 1) + " ..."
        if (viewHolder.checkbox != null) {
            viewHolder.checkbox!!.setOnCheckedChangeListener { checkBox, isChecked ->
                items[position].checked = isChecked
                notifyDataSetChanged()
            }
            viewHolder.checkbox!!.tag = movie
            //if (items[position].checked!! == null) viewHolder.checkbox!!.isChecked = false else true
            viewHolder.checkbox!!.isChecked = (items[position].checked!!)
        }
        return view
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }

    fun onMovieSelect(v: View) {
        var movieList: List<MovieData> = Gson().fromJson(movies, Array<MovieData>::class.java).asList()
        //val title = findViewById<Button>(v.id).text.toString()
//        var p:Int = 0
//        when (title){
//            "It Chapter Two"-> p = 0
//            "Spider-Man: Far from Home" -> p = 1
//            "John Wick: Chapter 3 – Parabellum"->p=4
//        }
//        val posterid = posterTable[title]!!
//        val fragment = FirstFragment.newInstance(movieList[p], posterid)
//        if (mTwoPane) {
//            supportFragmentManager.beginTransaction().replace(R.id.dContainer, fragment).addToBackStack(null).commit()
//        } else {
//            supportFragmentManager.beginTransaction().replace(R.id.mContainer, fragment).addToBackStack(null).commit()
//        }
    }

    fun addMovies() {
        for (i in items.indices) {
            val movie = items[i]
            if (movie.checked!!) {
                items[i].checked = false
                movie.checked = false
                items.add(i + 1, movie)
            }
        }
        notifyDataSetChanged()
    }

    fun deleteMovies() {
// This looks bad but works
        var cnt = 0
        for (i in 0 until items.size)

            if (items[i].checked!!)
                cnt += 1
        for (i in 0 until cnt) {
            for (j in items.indices) {
                if (items[j].checked!!) {
                    items.removeAt(j)
                    break
                }
            }
        }
        notifyDataSetChanged()
        Log.d("MY DEBUG", "Remaining Movies " + items.size)
    }

    interface MyItemClickListener {
        fun onItemClickedFromAdapter(movie : MovieData)
        fun onItemLongClickedFromAdapter(position : Int)
    }

    private class ViewHolder(view: View) {
        var img: ImageView = view.findViewById(R.id.list_row_image)
        var txt1: TextView = view.findViewById(R.id.title)
        var txt2: TextView = view.findViewById(R.id.description)
        var checkbox: CheckBox? = view.findViewById(R.id.checkBox)
    }


}
