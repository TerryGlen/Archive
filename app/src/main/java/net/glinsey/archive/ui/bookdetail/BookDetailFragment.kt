package net.glinsey.archive.ui.bookdetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import coil.load
import net.glinsey.archive.R
import net.glinsey.archive.databinding.BookDetailFragmentBinding
import net.glinsey.archive.ui.booklist.BookListViewModel

class BookDetailFragment : Fragment() {


    private lateinit var binding: BookDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  BookDetailFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel : BookListViewModel by activityViewModels()
        viewModel.currentVolume.observe(viewLifecycleOwner){
            it?.let{
                binding.authors.text = it.volumeInfo.authors.toString()
                binding.description.text = it.volumeInfo.description
                binding.publishDate.text = it.volumeInfo.publishedDate
                binding.title.text = it.volumeInfo.title
                binding.imageView.load(it.volumeInfo.imageLinks.thumbnailSecure())
            }

        }

    }


}