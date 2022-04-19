package net.glinsey.archive.remote

import net.glinsey.model.VolumeResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface GoogleBooksApi {

    @GET("volumes")
    suspend fun getBooks(
        @Query("q") query: String,
        @Query("startIndex") startIndex: Int = 0,
        @Query("maxResults") maxResults: Int = 20,
    ) : VolumeResponse


}
