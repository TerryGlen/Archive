package net.glinsey.archive.ui.booklist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import net.glinsey.archive.R
import net.glinsey.archive.databinding.FragmentBookListBinding
import net.glinsey.archive.ui.booklist.placeholder.PlaceholderContent

/**
 * A fragment representing a list of Items.
 */
class BookListFragment : Fragment() {

    lateinit var binding: FragmentBookListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel : BookListViewModel by activityViewModels()
        val adapter = VolumeListAdapter{
            viewModel.updateCurrentVolume(it)
            navigateToBookDetails()
        }
        binding.list.adapter = adapter

        viewModel.volumesList.observe(viewLifecycleOwner){ volumes ->
            volumes?.let{
                adapter.submitList(it)
            }
        }

    }

    private fun navigateToBookDetails(){
        findNavController().navigate(R.id.action_bookListFragment_to_bookDetailFragment)

    }


}