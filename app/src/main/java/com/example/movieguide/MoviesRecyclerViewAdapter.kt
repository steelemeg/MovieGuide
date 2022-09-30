package com.example.movieguide

import android.app.Activity
import android.content.Context
import android.hardware.SensorManager.getOrientation
import android.media.ExifInterface
import android.util.Log
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.ImageHeaderParserUtils.getOrientation
import com.example.movieguide.R.id

/**
 * [RecyclerView.Adapter] that can display a [Movie] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 */
class MoviesRecyclerViewAdapter(
    private val movies: List<Movie>,
    private val mListener: OnListFragmentInteractionListener?,
    private val orientation: Int
)
    : RecyclerView.Adapter<MoviesRecyclerViewAdapter.MovieViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movie, parent, false)
        return MovieViewHolder(view)
    }

    /**
     * This inner class lets us refer to all the different View elements
     * (Yes, the same ones as in the XML layout files!)
     */
    inner class MovieViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: Movie? = null
        val mMovieTitle: TextView = mView.findViewById<View>(id.movie_title) as TextView
        val mMovieDescription: TextView = mView.findViewById<View>(id.movie_description) as TextView
        val mMovieImage: ImageView = mView.findViewById<View>(id.movie_image) as ImageView
        val mMovieImage2: ImageView = mView.findViewById<View>(id.movie_image2) as ImageView

        override fun toString(): String {
            return mMovieTitle.toString()
        }
    }

    /**
     * This lets us "bind" each Views in the ViewHolder to its' actual data!
     */
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        val baseUrl = "https://image.tmdb.org/t/p/w500"
        holder.mItem = movie
        holder.mMovieTitle.text = movie.title
        holder.mMovieDescription.text = movie.description


        Log.v("image", baseUrl + movie.movieImageUrl.toString())
        Log.v("image", baseUrl + movie.movieLandscapeUrl.toString())
        Log.v("orientation", "in the adapter " + orientation.toString())
        if (orientation == 1){
            Glide.with(holder.mView)
                .load(baseUrl + movie.movieImageUrl)
                // Uses placeholder image from https://www.svgrepo.com/svg/199954/loading-loader
                .placeholder(R.drawable.loading)
                .centerInside()
                .into(holder.mMovieImage)
            holder.mMovieImage2.visibility = View.GONE
            holder.mMovieImage.visibility = View.VISIBLE
        }
        else {
            Glide.with(holder.mView)
                .load(baseUrl + movie.movieLandscapeUrl)
                // Uses placeholder image from https://www.svgrepo.com/svg/199954/loading-loader
                .placeholder(R.drawable.loading)
                .centerInside()
                .into(holder.mMovieImage2)
            holder.mMovieImage2.visibility = View.VISIBLE
            holder.mMovieImage.visibility = View.GONE
        }

        holder.mView.setOnClickListener {
            holder.mItem?.let { movie ->
                mListener?.onItemClick(movie)
            }
        }
    }

    /**
     * Remember: RecyclerView adapters require a getItemCount() method.
     */
    override fun getItemCount(): Int {
        return movies.size
    }

}