package com.example.beatboxapp

import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.beatboxapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private val beatBoxViewModel by lazy {
        ViewModelProvider(this)[BeatBoxViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        beatBoxViewModel.initialize(assets)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        setupUI(binding)
    }

    private fun setupUI(binding: ActivityMainBinding) {

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = SoundAdapter(layoutInflater, beatBoxViewModel.beatBox)
        }

        binding.playSpeedTv.text = getString(
            R.string.playback_speed_text,
            playbackSpeedLevels(binding.playSpeedSb.progress)
        )

        playbackSpeed(binding.playSpeedSb, binding.playSpeedTv)
    }

    private fun playbackSpeedLevels(level: Int): String {
        return when (level) {
            0 -> getString(R.string.slow)
            1 -> getString(R.string.normal)
            2 -> getString(R.string.fast)
            else -> throw RuntimeException("speed is error (method speedLevels)")
        }
    }

    private fun playbackSpeed(seekBar: SeekBar, textView: TextView) {

        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

                beatBoxViewModel.beatBox.playbackSpeed = p1
                textView.text = getString(R.string.playback_speed_text, playbackSpeedLevels(p1))
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {
                beatBoxViewModel.beatBox.playLoadSound {
                    val rootView = findViewById<View>(android.R.id.content)

                    Snackbar.make(
                        rootView,
                        getString(R.string.warning_play_sound),
                        Snackbar.LENGTH_SHORT
                    )
                        .setBackgroundTint(R.color.black)
                        .show()
                }
            }
        })
    }
}