package com.example.beatboxapp

private const val WAV = ".wav"

class Sound(val assetsPath: String, var soundID: Int? = null) {
    val name = assetsPath.split("/").last().removeSuffix(WAV)
}

