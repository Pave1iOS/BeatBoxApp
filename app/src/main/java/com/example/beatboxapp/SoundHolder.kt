package com.example.beatboxapp

import androidx.recyclerview.widget.RecyclerView
import com.example.beatboxapp.databinding.ListItemSoundBinding

class SoundHolder(
    private val binding: ListItemSoundBinding,
    beatBox: BeatBox,
    smile: Smile
): RecyclerView.ViewHolder(binding.root) {

    init {
        binding.viewModel = SoundViewModel(beatBox, smile)
    }

    fun bind(sound: Sound) {
        binding.apply {
            viewModel?.sound = sound
            executePendingBindings()
        }
    }

}