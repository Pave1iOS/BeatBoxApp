package com.example.beatboxapp

import android.content.res.AssetManager
import android.util.Log

private const val TAG = "BeatBox"
private const val SOUND_FOLDER = "sample_sounds"

class BeatBox(private val assets: AssetManager) {

    val sounds: List<Sound>

    init {
        sounds = loadSounds()
    }

    private fun loadSounds(): List<Sound> {

        val soundName: Array<String>

        try {
            soundName = assets.list(SOUND_FOLDER)!!
        } catch (e: Exception) {
            Log.e(TAG, "Could not list assets", e)
            return emptyList()
        }

        val sounds = mutableListOf<Sound>()

        soundName.forEach {
            val assetPath = "$SOUND_FOLDER/$it"
            val sound = Sound(assetPath)
            sounds.add(sound)
        }
        return sounds
    }

}