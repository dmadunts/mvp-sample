package com.dmadunts.samples.mvpsample.view.allcreatures

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dmadunts.samples.mvpsample.databinding.ListItemCreatureBinding
import com.dmadunts.samples.mvpsample.model.Creature

class CreatureAdapter(private val creatures: MutableList<Creature>) :
    RecyclerView.Adapter<CreatureAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListItemCreatureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(creatures[position])
    }

    override fun getItemCount() = creatures.size

    fun updateCreatures(creatures: List<Creature>) {
        this.creatures.clear()
        this.creatures.addAll(creatures)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ListItemCreatureBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var creature: Creature

        fun bind(creature: Creature) {
            this.creature = creature
            binding.avatarListItem.setImageResource(creature.drawable)
            binding.hitPoints.text = creature.hitPoints.toString()
            binding.name.text = creature.name
        }
    }
}