package com.example.beatboxapp

import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.beatboxapp.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var beatBox: BeatBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        beatBox = BeatBox(assets)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        setupUI(binding)
    }

    override fun onDestroy() {
        super.onDestroy()
        beatBox.release()
    }

    private fun setupUI(binding: ActivityMainBinding) {

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = SoundAdapter(layoutInflater, beatBox.sounds, beatBox)
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

                beatBox.playbackSpeed = p1
                textView.text = getString(R.string.playback_speed_text, playbackSpeedLevels(p1))

                Log.d(TAG, "progress is: ${beatBox.playbackSpeed}")
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
    }

}