package net.glinsey.archive.remote

import net.glinsey.model.VolumesResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface GoogleBooksApi {

    @GET("volumes")
    suspend fun getBooks(
        @Query("q") query: String,
        @Query("startIndex") startIndex: Int = 0,
        @Query("maxResults") maxResults: Int = 20,
    ) : VolumesResponse


}

// Extension function to convert List<String> to a '+' separated String that can be passed to the
// Retrofit service interface

suspend fun GoogleBooksApi.getBooks(queries: List<String>) : VolumesResponse{
    return getBooks(queries.joinToString("+"))
}