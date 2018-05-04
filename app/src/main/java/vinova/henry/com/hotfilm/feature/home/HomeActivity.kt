package vinova.henry.com.hotfilm.feature.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import vinova.henry.com.hotfilm.R
import vinova.henry.com.hotfilm.datahelper.IMovieSchema

class HomeActivity : AppCompatActivity(), IMovieSchema {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}
