package net.glinsey.archive.utils

import android.widget.ImageView
import coil.load
import net.glinsey.archive.R
import net.glinsey.model.ImageLinks


fun loadImageLink(view: ImageView, imageLinks: ImageLinks?, small: Boolean = true){
    if(imageLinks == null){
        view.load(R.drawable.ic_book_placeholder)
    }else{
        val imageUrl = if(small) imageLinks.smallThumbnailSecure() else imageLinks.thumbnailSecure()
        view.load(imageUrl)
    }

}