package com.example.myapplication

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_movie.*
import java.io.Serializable

// TODO: Rename parameter arguments, choose names that match
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MovieFragment : Fragment() {

    private var param1: MovieData? = null
    private var param2: Int? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as MovieData
            param2 = it.getInt(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie = param1
        movieTitle.text = movie!!.title
//        movieID.text = movieList[movieIndex].id.toString()
        movieView.setImageResource(param2!!.toInt())
        val rate = movie!!.vote_average!!.div(2.0)
        ratingBar.rating = rate.toFloat()
        rating.text = "Rate: " + movie!!.vote_average.toString()
        movieOverview.text = movie!!.overview
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

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

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: MovieData, param2: Int) =
            MovieFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1,param1 as Serializable)
                    putInt(ARG_PARAM2, param2)
                }
            }
    }
}
