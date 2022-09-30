package com.example.movieguide

import com.google.gson.annotations.SerializedName

/**
 * The Model for storing a single book from the NY Times API
 *
 * SerializedName tags MUST match the JSON response for the
 * object to correctly parse with the gson library.
 */
class Movie {

    @JvmField
    @SerializedName("title")
    var title: String? = null

    // Image for portrait view
    @JvmField
    @SerializedName("poster_path")
    var movieImageUrl: String? = null
    // Image for landscape view
    @JvmField
    @SerializedName("backdrop_path")
    var movieLandscapeUrl: String? = null

    @JvmField
    @SerializedName("overview")
    var description: String? = null

}