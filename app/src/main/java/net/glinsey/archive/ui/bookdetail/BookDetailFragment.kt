package net.glinsey.archive.ui.bookdetail

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import net.glinsey.archive.R
import net.glinsey.archive.databinding.BookDetailFragmentBinding
import net.glinsey.archive.ui.BookViewModel
import net.glinsey.archive.utils.loadImageLink

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
        val viewModel : BookViewModel by activityViewModels()
        viewModel.currentVolume.observe(viewLifecycleOwner){ volume ->
            volume?.let{

                loadImageLink(binding.imageView, volume.volumeInfo.imageLinks, false)
                binding.authors.text = it.volumeInfo.authors?.joinToString { ", " } ?: ""
                binding.description.text = it.volumeInfo.description
                binding.publishDate.text = it.volumeInfo.publishedDate
                binding.title.text = it.volumeInfo.title
                //binding.readerUrl.text = it.accessInfo.webReaderLink
                binding.readerUrl.setOnClickListener { view ->
                    openCustomTab(it.accessInfo.webReaderLink)
                }

            }

        }

    }

    // Potential Custom Tab Implementation

    private fun openCustomTab(url: String){
        val builder = CustomTabsIntent.Builder()
        val params = CustomTabColorSchemeParams.Builder()
        params.setToolbarColor(ContextCompat.getColor(requireActivity(), R.color.purple_500))
        builder.setDefaultColorSchemeParams(params.build())
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(requireActivity(), Uri.parse(url))

    }



}