package com.example.movieguide

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
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
        val supportFragmentManager = supportFragmentManager
        Log.v("orientation", "onCreate")
        if (savedInstanceState == null){
            mFragment = MoviesFragment()
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(id.content, mFragment!!, TAG_MY_FRAGMENT).commit()
            Log.v("orientation", "new transaction")
        }
        else{
            mFragment = getSupportFragmentManager().findFragmentByTag(TAG_MY_FRAGMENT) as MoviesFragment?
            Log.v("orientation", "sticking with existing fragment")
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        // Checks the orientation of the screen
        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show()
            Log.v("orientation", "landscape")
            mFragment = getSupportFragmentManager().findFragmentByTag(TAG_MY_FRAGMENT) as MoviesFragment?
            portrait = false
        } else if (newConfig.orientation === Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show()
            Log.v("orientation", "portrait")
            mFragment = getSupportFragmentManager().findFragmentByTag(TAG_MY_FRAGMENT) as MoviesFragment?
            portrait = true
        }
    }
}