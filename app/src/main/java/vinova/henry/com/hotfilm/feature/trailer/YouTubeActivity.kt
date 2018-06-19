package vinova.henry.com.hotfilm.feature.trailer

import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_you_tube.*
import vinova.henry.com.hotfilm.R
import vinova.henry.com.hotfilm.YOUTUBE_API_KEY
import vinova.henry.com.hotfilm.models.trailer.Trailer

class YouTubeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    private var youTubePlayer: YouTubePlayer? = null
    private var videoKey: String? = null
    private var trailerAdapter: TrailerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_you_tube)

        trailerAdapter = TrailerAdapter (fun (videoKey){
            this.videoKey = videoKey
            youTubePlayer?.cueVideo(videoKey)
        })
        rvTrailer.adapter = trailerAdapter
        rvTrailer.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        intent.let {
            val trailers = it.extras.get("trailers") as ArrayList<Trailer>
            trailerAdapter?.setTrailers(trailers = trailers)
            val pos = it.extras.get("position") as Int
            videoKey = trailers[pos].key
        }

        youTubePlayerView.initialize(YOUTUBE_API_KEY, this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == 1) {
            youTubePlayerView.initialize(YOUTUBE_API_KEY, this)
        }
    }

    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
        this.youTubePlayer = p1
        // Specify that we want to handle fullscreen behavior ourselves.
        p1?.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT)
        /*if (p2) {
            p1?.cueVideo(videoKey)
        }*/
        p1?.cueVideo(videoKey)
        p1?.play()
    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {

    }

    fun doLayout(){
        val playerParams: ConstraintLayout.LayoutParams = youTubePlayerView.layoutParams as ConstraintLayout.LayoutParams

        playerParams.width = LinearLayout.LayoutParams.MATCH_PARENT
        playerParams.height = LinearLayout.LayoutParams.MATCH_PARENT
    }

}










/*youtubePlayer!!.initialize(YOUTUBE_API_KEY, object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(provider: YouTubePlayer.Provider, youTubePlayer: YouTubePlayer, b: Boolean) {
                youTubePlayer.loadVideo(trailer.key)
                youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
                youTubePlayer.setFullscreen(true)
                youTubePlayer.setShowFullscreenButton(false)
                youTubePlayer.setPlayerStateChangeListener(object : YouTubePlayer.PlayerStateChangeListener {
                    override fun onLoading() {

                    }

                    override fun onLoaded(s: String) {

                    }

                    override fun onAdStarted() {

                    }

                    override fun onVideoStarted() {

                    }

                    override fun onVideoEnded() {
                        super@YouTubeActivity.onBackPressed()
                    }

                    override fun onError(errorReason: YouTubePlayer.ErrorReason) {

                    }
                })
                youTubePlayer.setPlaybackEventListener(object : YouTubePlayer.PlaybackEventListener {
                    override fun onPlaying() {

                    }

                    override fun onPaused() {

                    }

                    override fun onStopped() {

                    }

                    override fun onBuffering(b: Boolean) {

                    }

                    override fun onSeekTo(i: Int) {

                    }
                })
            }

            override fun onInitializationFailure(provider: YouTubePlayer.Provider, youTubeInitializationResult: YouTubeInitializationResult) {

            }
        })*/