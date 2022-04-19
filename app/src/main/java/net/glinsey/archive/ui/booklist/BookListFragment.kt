package net.glinsey.archive.ui.booklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import net.glinsey.archive.R
import net.glinsey.archive.databinding.FragmentBookListBinding
import net.glinsey.archive.ui.BookViewModel

/**
 * A fragment representing a list of Items.
 */
class BookListFragment : Fragment() {

    private lateinit var binding: FragmentBookListBinding

    private val viewModel: BookViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = VolumeListAdapter(
            onItemClick = { navigateToBookDetails(it)
        },
            onFooterClick = {viewModel.loadNext()}
        )

        binding.list.adapter = adapter

        viewModel.volumeList.observe(viewLifecycleOwner){ volumes ->
            volumes?.let{
               if(it.isNotEmpty()){
                   val items = it.map{DataItem.VolumeItem(it)} + listOf(DataItem.Footer)
                   adapter.submitList(items)
               }

            }
        }

    }


    private fun navigateToBookDetails(position: Int){
        viewModel.updateCurrentVolume(position)
        findNavController().navigate(R.id.action_bookListFragment_to_bookDetailFragment)

    }



}