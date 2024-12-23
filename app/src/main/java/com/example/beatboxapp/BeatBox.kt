package com.example.beatboxapp

import android.content.res.AssetManager
import android.media.SoundPool
import android.util.Log
import java.io.IOException

private const val TAG = "BeatBox"
private const val SOUND_FOLDER = "sample_sounds"
private const val MAX_SOUNDS = 5

class BeatBox(private val assets: AssetManager) {

    val sounds: List<Sound>
    private val soundPool = SoundPool.Builder()
        .setMaxStreams(MAX_SOUNDS) // количество звуков в один момент
        .build()

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

            try {
                load(sound)
                sounds.add(sound)
            } catch (ioe: IOException) {
                Log.e(TAG, "Cound not load sound $it", ioe)
            }

        }
        return sounds
    }

    fun load(sound: Sound) {
        val assetsFD = assets.openFd(sound.assetsPath)
        val soundID = soundPool.load(assetsFD, 1)
        sound.soundID = soundID
    }

}