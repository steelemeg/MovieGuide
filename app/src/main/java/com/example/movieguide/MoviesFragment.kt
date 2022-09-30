package com.example.movieguide
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.createSavedStateHandle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.android.material.internal.ContextUtils.getActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONArray



// TODO
private const val API_KEY = BuildConfig.API_KEY
private var models : List<Movie>? = null

/*
 * The class for the only fragment in the app, which contains the progress bar,
 * recyclerView, and performs the network calls to the NY Times API.
 */
class MoviesFragment : Fragment(), OnListFragmentInteractionListener {

    /*
     * Constructing the view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
        // Movies per row
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        updateAdapter(progressBar, recyclerView)
        return view
    }

    /*
     * Updates the RecyclerView adapter with new data.  This is where the
     * networking magic happens!
     */
    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView){
        progressBar.show()

        // Create and set up an AsyncHTTPClient() here
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = API_KEY
        Log.v("state", "branch " + models?.size.toString())
        if (models?.size == null) {
            Log.v("state", "no existing data")


            // Using the client, perform the HTTP request
            val start = System.currentTimeMillis()
            client[
                    // TODO debugging slow response times
                    "https://api.themoviedb.org/3/movie/now_playing", //?api_key=$API_KEY",
                    //"https://api.themoviedb.org/3/movie/upcoming",
                    //"https://api.themoviedb.org/3/movie/top_rated",
                    params,
                    object : JsonHttpResponseHandler() {
                        /*
                         * The onSuccess function gets called when
                         * HTTP response status is "200 OK"
                         */
                        override fun onSuccess(
                            statusCode: Int,
                            headers: Headers,
                            json: JsonHttpResponseHandler.JSON
                        ) {
                            // The wait for a response is over
                            progressBar.hide()
                            Log.v("timer", (System.currentTimeMillis() - start).toString())

                            // Get the results out of the response, and then get the movies
                            val resultsJSON: JSONArray = json.jsonObject.get("results") as JSONArray
                            val moviesRawJSON: String = resultsJSON.toString()

                            // Create a model using gson
                            val gson = Gson()
                            val arrayMovieType = object : TypeToken<List<Movie>>() {}.type
                            models = gson.fromJson(moviesRawJSON, arrayMovieType)
                            //var models : List<Movie> = gson.fromJson(moviesRawJSON, arrayMovieType)
                            recyclerView.adapter =
                                models?.let { MoviesRecyclerViewAdapter(it, this@MoviesFragment) }

                        }

                        /*
                         * The onFailure function gets called when
                         * HTTP response status is "4XX" (eg. 401, 403, 404)
                         */
                        override fun onFailure(
                            statusCode: Int,
                            headers: Headers?,
                            errorResponse: String,
                            t: Throwable?
                        ) {
                            // The wait for a response is over
                            progressBar.hide()

                            // If the error is not null, log it!
                            t?.message?.let {
                                Log.e("json", errorResponse)
                            }
                        }
                    }]
        }
        else {
            Log.v("state", "existing data!! " + models?.size.toString())
            recyclerView.adapter =
                models?.let {
                    MoviesRecyclerViewAdapter(it, this@MoviesFragment)
                }
            progressBar.hide()
        }
    }

    /*
     * What happens when a particular book is clicked.
     */
    override fun onItemClick(item: Movie) {
        Toast.makeText(context, "Movie Selected: " + item.title, Toast.LENGTH_LONG).show()
    }

}
