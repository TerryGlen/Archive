package net.glinsey.archive.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import net.glinsey.archive.R
import net.glinsey.archive.databinding.SearchFragmentBinding
import net.glinsey.archive.ui.BookViewModel

class SearchFragment : Fragment() {


    private lateinit var binding: SearchFragmentBinding
    private val viewModel: BookViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SearchFragmentBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchView.setOnQueryTextListener( object: androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let{
                    navigateToBookList(query)
                }
                return true

            }

            override fun onQueryTextChange(query: String?): Boolean {
                return true
            }

        }
        )


    }


    fun navigateToBookList(query: String){
        viewModel.search(query)
        findNavController().navigate(R.id.action_searchFragment_to_bookListFragment)
    }


}