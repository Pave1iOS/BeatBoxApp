package com.example.beatboxapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.beatboxapp.databinding.ListItemSoundBinding

class SoundAdapter(
    private val layoutInflater: LayoutInflater,
    private val beatBox: BeatBox
): RecyclerView.Adapter<SoundHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundHolder {

        val binding = DataBindingUtil.inflate<ListItemSoundBinding>(
            layoutInflater,
            R.layout.list_item_sound,
            parent,
            false
        )

        return SoundHolder(binding, beatBox)
    }

    override fun getItemCount(): Int = beatBox.sounds.size

    override fun onBindViewHolder(holder: SoundHolder, position: Int) {
        val sound = beatBox.sounds[position]
        holder.bind(sound)
    }
}
