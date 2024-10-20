package com.example.fail.network.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.fail.R
import com.example.fail.network.model.Character
import com.example.fail.databinding.CharacterItemBinding


class AppAdapter(
    private val onItemClick: (Character) -> Unit
) : ListAdapter<Character, AppAdapter.CharacterViewHolder>(CharacterDiffCallback) {

    inner class CharacterViewHolder(val binding: CharacterItemBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            binding.tvName.text = character.name
            binding.tvDeadOr.text = character.status
            binding.tvRase.text = character.species
            binding.tvLoc2.text = character.location.toString()
            binding.tvSeein2.text = character.created

            Glide.with(binding.ivImage.context)
                .load(character.image)
                .placeholder(R.drawable.anime)
                .into(binding.ivImage)

            itemView.setOnClickListener {
                onItemClick(character)
            }

            when (character.status) {
                "Alive" -> binding.statusIndicator.setBackgroundColor(Color.GREEN)
                "Dead" -> binding.statusIndicator.setBackgroundColor(Color.BLACK)
                "unknown" -> binding.statusIndicator.setBackgroundColor(Color.GRAY)
                else -> binding.statusIndicator.setBackgroundColor(Color.TRANSPARENT)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
    }
}
val CharacterDiffCallback = object: DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}