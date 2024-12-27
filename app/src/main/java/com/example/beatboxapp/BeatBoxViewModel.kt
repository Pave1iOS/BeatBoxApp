package com.example.beatboxapp

import android.content.res.AssetManager
import androidx.lifecycle.ViewModel

private const val TAG = "BeatBoxViewModel"

class BeatBoxViewModel: ViewModel() {

    lateinit var beatBox: BeatBox
        private set

    fun initialize(assets: AssetManager) {
        beatBox = BeatBox(assets)
    }

    override fun onCleared() {
        super.onCleared()
        beatBox.release()
    }
}