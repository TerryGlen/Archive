package net.glinsey.archive.ui.booklist

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import coil.load
import net.glinsey.archive.databinding.FragmentBookItemBinding
import net.glinsey.archive.databinding.LoadNextFragmentBinding
import net.glinsey.archive.utils.loadImageLink

import net.glinsey.model.Volume


class VolumeListAdapter(
    private val onItemClick :(Int)-> Unit = {},
    private val onFooterClick: () -> Unit = {}
) : ListAdapter<DataItem, RecyclerView.ViewHolder>(VolumeItemDiffUtils()) {
    private val ITEM_VIEW_TYPE_FOOTER = 0
    private val ITEM_VIEW_TYPE_ITEM = 1

    class ViewHolder(
        val binding : FragmentBookItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val binding = FragmentBookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ViewHolder(binding)
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
             ITEM_VIEW_TYPE_FOOTER -> LoadNextViewHolder.from(parent)
             ITEM_VIEW_TYPE_ITEM -> ViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }

    }


    class LoadNextViewHolder(val binding: LoadNextFragmentBinding): RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): LoadNextViewHolder {
                val binding = LoadNextFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return LoadNextViewHolder(binding)
            }
        }
    }




    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ViewHolder -> {
               val dataItem  = getItem(position) as DataItem.VolumeItem
                val volume = dataItem.volume
                with(holder){
                    // TODO: Add placeholder
                        loadImageLink(binding.imageView, volume.volumeInfo.imageLinks, true)
                        binding.title.text = volume.volumeInfo.title
                        binding.authors.text = volume.volumeInfo.authors?.joinToString(", ") ?: ""
                        binding.publishDate.text = volume.volumeInfo.publishedDate

                        itemView.setOnClickListener {
                            onItemClick.invoke(position)
                        }
                    }
                }
            is LoadNextViewHolder ->{
               with(holder){
                   binding.loadButton.setOnClickListener {
                       onFooterClick.invoke()
                   }
               }
            }



        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)){
            is DataItem.Footer -> ITEM_VIEW_TYPE_FOOTER
            is DataItem.VolumeItem -> ITEM_VIEW_TYPE_ITEM
        }
    }


}

class VolumeItemDiffUtils : DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem == newItem
    }
}

sealed class DataItem {
    abstract val id: String
    data class VolumeItem(val volume: Volume): DataItem()      {
        override val id = volume.id
    }

    object Footer: DataItem() {
        override val id = "ARCHIVEFOOTER"
    }
}