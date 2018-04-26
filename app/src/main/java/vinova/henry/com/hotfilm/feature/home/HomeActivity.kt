package vinova.henry.com.hotfilm.feature.home

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log

import java.util.ArrayList

import butterknife.ButterKnife
import vinova.henry.com.hotfilm.R
import vinova.henry.com.hotfilm.datahelper.IMovieSchema
import vinova.henry.com.hotfilm.models.Movie

class HomeActivity : AppCompatActivity(), IMovieSchema {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}
