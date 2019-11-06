package com.example.myapplication.ui.gallery

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_gallery.*
import kotlinx.android.synthetic.main.fragment_list_view.*
import kotlinx.android.synthetic.main.fragment_list_view.Delete
import kotlinx.android.synthetic.main.fragment_list_view.Duplicate
import kotlinx.android.synthetic.main.fragment_list_view.listViewFrame

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class GalleryFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    //    private var listener: OnFragmentInteractionListener? = null
    private var listener: OnListViewInteractionListener? = null
    private var myListener: MyItemClickListener? = null
    private val movies = MovieList()
    val adapter = MyMovieListViewAdapter(ArrayList(movies.movieList), 1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        retainInstance = true
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listViewFrame.adapter = adapter
        listViewFrame.setOnItemClickListener { parent, view, position, id ->
            if(parent == null)
                Log.d("MY DEBUG", "List Fragment - OnItemClick - No Parent")
            if(view == null)
                Log.d("MY DEBUG", "List Fragment - OnItemClick - No View")
            val movie = adapter.getItem(position) as MovieData
            listener = activity as ListViewActivity
            onItemClicked(movie, position, view)
            Log.d("MY DEBUG", "testing pressed")
        }

        listViewFrame.setOnItemLongClickListener { parent, view, position, id ->
            Log.d("MY DEBUG", "testing pressed")
            activity!!.startActionMode(ActionBarCallBack(position))
            this.myListener = myListener
            myListener!!.onItemLongClickedFromAdapter(position)
//            items[position].checked = isChecked
//            notifyDataSetChanged()
            true
        }
        Delete.setOnClickListener {
            adapter.deleteMovies()
            adapter.notifyDataSetChanged()
        }
        Duplicate.setOnClickListener {
            adapter.notifyDataSetChanged()
            adapter.addMovies()
        }
    }

    fun setMyItemClickListener ( listener: MyItemClickListener){
        this.myListener = listener
    }

    interface MyItemClickListener {
        fun onItemLongClickedFromAdapter(position : Int)
        fun onOverflowMenuClickedFromAdapter(view: View, position: Int)
    }

    fun onItemClicked(movie : MovieData, position: Int, view: View) {
        listener?.onListViewInteraction(movie, position, view)
    }
    fun getMovie(index: Int): Any {
        return adapter.getItem(index)
    }




    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        if( menu?.findItem(R.id.action_search) == null )
            inflater?.inflate(R.menu.movie_search_toolbar, menu)
        val search = menu?.findItem(R.id.action_search)!!.actionView as SearchView
        if ( search != null ){
            search.setOnQueryTextListener( object: SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
//                    val pos = adapter.findFirst( query!!)
//                    if (pos >= 0) {
//                        //rview3.smoothScrollToPosition(pos)
//                        Toast.makeText(context, "Search Movie " + query + " found ... ", Toast.LENGTH_SHORT).show()
//                    } else {
//                        //rview3.smoothScrollToPosition(0)
//                        Toast.makeText(context, "Search Movie " + query + " not found ... ", Toast.LENGTH_SHORT).show()
//                    }
                    return true
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })
        }
    }



    inner class ActionBarCallBack(val position:Int): ActionMode.Callback {
        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            when(item!!.itemId){
                R.id.action_dup -> {
                    //adapter.addMovie(position)
                    mode!!.finish()
                }
                R.id.action_rem -> {
                    //adapter.deleteMovie(position)
                    mode!!.finish()
                }
            }
            return false
        }
        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            mode!!.menuInflater.inflate(R.menu.movie_list_toolbar, menu)
            return true
        }
        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            val movie = adapter.getItem(position) as MovieData
            mode!!.title = movie.title
            return false
        }
        override fun onDestroyActionMode(mode: ActionMode?) {
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    interface OnListViewInteractionListener {
        fun onListViewInteraction(movie: MovieData, position: Int, view: View)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListViewFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListViewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}