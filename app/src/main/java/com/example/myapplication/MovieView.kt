package com.example.myapplication

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_list_view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ListViewFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    //    private var listener: OnFragmentInteractionListener? = null
    private var listener: OnListViewInteractionListener? = null
    private val movies = MovieList()
    val adapter = MyMovieListViewAdapter(ArrayList(movies.movieList), 1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_view, container, false)
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
            //listener = activity as MainActivity
            onItemClicked(movie, position, view)
            Log.d("MY DEBUG", "testing pressed")
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

    fun onItemClicked(movie : MovieData, position: Int, view: View) {
        listener?.onListViewInteraction(movie, position, view)
    }
    fun getMovie(index: Int): Any {
        return adapter.getItem(index)
    }

    // TODO: Rename method, update argument and hook method into UI event
//    fun onButtonPressed(uri: Uri) {
//        listener?.onFragmentInteraction(uri)
//    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
//        }
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//        listener = null
//    }

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
