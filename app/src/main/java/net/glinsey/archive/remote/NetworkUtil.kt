package net.glinsey.archive.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NetworkUtil {
    private val retrofit by lazy{ Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://www.googleapis.com/books/v1/")
        .build()

    }

    val booksApi: GoogleBooksApi by lazy{ retrofit.create(GoogleBooksApi::class.java)

    }
}