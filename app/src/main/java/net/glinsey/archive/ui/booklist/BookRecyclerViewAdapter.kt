package net.glinsey.archive.ui.booklist

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import net.glinsey.archive.databinding.FragmentBookItemBinding

import net.glinsey.model.Volume
import kotlin.math.absoluteValue


class VolumeListAdapter(private val onItemClick :(Int)-> Unit = {}) : ListAdapter<Volume, VolumeListAdapter.ViewHolder>(VolumeDiffUtils()) {
    inner class ViewHolder(val binding : FragmentBookItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentBookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            val volume = getItem(position)
            binding.title.text = volume.volumeInfo.title
            binding.authors.text = volume.volumeInfo.authors.toString()
            binding.publishDate.text = volume.volumeInfo.publishedDate
            itemView.setOnClickListener {
                onItemClick.invoke(position)
            }
        }
    }


}

class VolumeDiffUtils : DiffUtil.ItemCallback<Volume>() {
    override fun areItemsTheSame(oldItem: Volume, newItem: Volume): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Volume, newItem: Volume): Boolean {
        return oldItem == newItem
    }
}