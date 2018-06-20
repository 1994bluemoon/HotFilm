package vinova.henry.com.hotfilm.feature.trailer

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_you_tube.*
import vinova.henry.com.hotfilm.R
import vinova.henry.com.hotfilm.YOUTUBE_API_KEY
import vinova.henry.com.hotfilm.models.trailer.Trailer

class YouTubeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener, YouTubePlayer.OnFullscreenListener {

    private var youTubePlayer: YouTubePlayer? = null
    private var videoKey: String? = null
    private var trailerAdapter: TrailerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_you_tube)

        trailerAdapter = TrailerAdapter(onTrailerClicked)
        rvTrailer.adapter = trailerAdapter
        rvTrailer.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        intent.let {
            val trailers = it.extras.get("trailers") as ArrayList<Trailer>
            trailerAdapter?.setTrailers(trailers = trailers)
            val pos = it.extras.get("position") as Int
            videoKey = trailers[pos].key
            tvTitle.text = trailers[pos].name
        }

        youTubePlayerView.initialize(YOUTUBE_API_KEY, this)
        imBack.setOnClickListener { super.onBackPressed() }
    }

    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
        this.youTubePlayer = p1
        p1?.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT)
        youTubePlayer?.setOnFullscreenListener(this)
        youTubePlayer?.cueVideo(videoKey)
        youTubePlayer?.play()
    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == 1) {
            youTubePlayerView.initialize(YOUTUBE_API_KEY, this)
        }
    }

    override fun onFullscreen(p0: Boolean) {
        if (p0) {
            clToolBar.visibility = View.GONE
        } else {
            clToolBar.visibility = View.VISIBLE
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        if (newConfig?.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            clToolBar.visibility = View.GONE
        } else if (newConfig?.orientation == Configuration.ORIENTATION_PORTRAIT){
            clToolBar.visibility = View.VISIBLE
        }
    }

    private var onTrailerClicked = fun(videoKey: String?, videoName: String?) {
        this.videoKey = videoKey
        tvTitle.text = videoName
        youTubePlayer?.cueVideo(videoKey)
    }
}