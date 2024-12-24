package com.example.beatboxapp

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

private const val TAG = "SoundViewModel"

class SoundViewModel(private val beatBox: BeatBox): BaseObservable() {

    var sound: Sound? = null
        set(value) {
            field = value
            notifyChange()
        }

    @get: Bindable
    val title: String?
        get() = sound?.name

    fun onButtonClicked() {
        sound?.let {
            Log.d(TAG, "click sound: ${it.name}")
            beatBox.play(it)
        }
    }
}
