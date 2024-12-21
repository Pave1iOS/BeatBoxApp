package com.example.beatboxapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.beatboxapp.databinding.ListItemSoundBinding

class SoundAdapter(private val layoutInflater: LayoutInflater): RecyclerView.Adapter<SoundHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundHolder {

        val binding = DataBindingUtil.inflate<ListItemSoundBinding>(
            layoutInflater,
            R.layout.list_item_sound,
            parent,
            false
        )

        return SoundHolder(binding)
    }

    override fun getItemCount(): Int = 0

    override fun onBindViewHolder(holder: SoundHolder, position: Int) { }

}