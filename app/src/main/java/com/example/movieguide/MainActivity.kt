package com.example.movieguide

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.movieguide.R.id


/**
 * The MainActivity for the MovieGuide app.
 * Launches a [MoviesFragment].
 */
class MainActivity : AppCompatActivity() {
    public var portrait = true
    private val TAG_MY_FRAGMENT = "myFragment"
    private var mFragment: MoviesFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setContentView(R.layout.activity_adding_fragments)
        val orientation = this.resources.configuration.orientation
        val supportFragmentManager = supportFragmentManager

        mFragment = MoviesFragment()
        // Pass orientation information to the fragment
        val mBundle = Bundle()
        mBundle.putInt("orientation", orientation)
        Log.v("orientation", "fragment " + mBundle.getInt("orientation").toString())
        mFragment!!.arguments = mBundle
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(id.content, mFragment!!, TAG_MY_FRAGMENT).commit()



    }
}