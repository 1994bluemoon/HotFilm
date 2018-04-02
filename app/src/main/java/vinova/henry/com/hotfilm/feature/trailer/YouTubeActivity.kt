package vinova.henry.com.hotfilm.feature.trailer

import android.os.Bundle

import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

import butterknife.BindView
import butterknife.ButterKnife
import vinova.henry.com.hotfilm.R
import vinova.henry.com.hotfilm.models.Trailer

class YouTubeActivity : YouTubeBaseActivity() {
    internal lateinit var trailer: Trailer

    @BindView(R.id.youtube_player)
    internal var youtubePlayer: YouTubePlayerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_you_tube)
        ButterKnife.bind(this)

        val ex = intent.extras
        if (ex != null) {
            trailer = ex.get("Trailer") as Trailer
        }

        youtubePlayer!!.initialize(YOUTUBE_API_KEY, object : YouTubePlayer.OnInitializedListener {
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
        })
    }

    companion object {

        private val YOUTUBE_API_KEY = "AIzaSyBPjiGer_AHJYZat5aXgW4VBz7dHdCGxXQ"
    }
}
