package com.hocruxx.adi.modul3kel4

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*

import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import kotlinx.android.synthetic.main.activity_video_player.*

class VideoPlayer : YouTubeBaseActivity() {
    internal lateinit var uri: Uri
    internal lateinit var myoutubePlayerView: YouTubePlayerView
    internal lateinit var mOnINitializedListener: YouTubePlayer.OnInitializedListener
    internal lateinit var buttonPlay: ImageButton
    internal lateinit var clk: Button
    internal lateinit var videoView: VideoView
    internal lateinit var mediaC: MediaController
    internal lateinit var nama: EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)
        myoutubePlayerView = findViewById<View>(R.id.vidOnline) as YouTubePlayerView
        buttonPlay = findViewById<View>(R.id.play) as ImageButton
        clk = findViewById<View>(R.id.button) as Button
        videoView = findViewById<View>(R.id.videoView) as VideoView
        mediaC = MediaController(this)
        val btnplay = findViewById<View>(R.id.button) as Button
        nama = findViewById<View>(R.id.input) as EditText

        mOnINitializedListener = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(provider: YouTubePlayer.Provider, youTubePlayer: YouTubePlayer, b: Boolean) {
                youTubePlayer.loadPlaylist(input.text.toString())
            }

            override fun onInitializationFailure(provider: YouTubePlayer.Provider, youTubeInitializationResult: YouTubeInitializationResult) {
                Log.d(TAG, "onClick : Gagal Memulai")
            }
        }

        val mediaController = MediaController(this)


        buttonPlay.setOnClickListener {
            videoView.pause()
            Log.d(TAG, "onClick : Memulai Player Youtube")
            myoutubePlayerView.requestFocus()
            myoutubePlayerView.initialize(youtubeApiKey, mOnINitializedListener)
        }

        btnplay.setOnClickListener(View.OnClickListener {
            val videopath = "android.resource://com.hocruxx.adi.modul3kel4/" + R.raw.review
            val uri = Uri.parse(videopath)
            videoView.setVideoURI(uri)
            videoView.setMediaController(mediaC)
            mediaC.setAnchorView(videoView)
            videoView.start()
        })


    }
    companion object {
        private val TAG = "VideoPlayer"
        val youtubeApiKey = "AIzaSyChLw3Mh0ZSkEnfn0Kxq57OGluLTVASYS4"
    }
}
