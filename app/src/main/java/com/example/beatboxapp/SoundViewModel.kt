package com.example.beatboxapp

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

private const val TAG = "SoundViewModel"

class SoundViewModel(
    private val beatBox: BeatBox,
    private val smile: Smile
): BaseObservable() {

    var sound: Sound? = null
        set(value) {
            field = value
            notifyChange()
        }

    @get: Bindable
    val title: String?
        get() = sound?.let { smile.changeDefaultTitleToSmile(it.name) }

    fun onButtonClicked() {
        sound?.let {
            beatBox.play(it)
        }
    }
}
