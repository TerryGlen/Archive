package net.glinsey.archive.repo

import net.glinsey.archive.remote.NetworkUtil
import net.glinsey.model.Volume
import net.glinsey.model.VolumeResponse
import java.lang.Exception
import kotlin.math.max

class GoogleBooksRepo {
    private val service = NetworkUtil.booksApi

    // TODO: Handle Errors
    suspend fun fetchVolumes(vararg queries: String, startIndex: Int = 0, maxResults: Int = 20) : List<Volume>{
        val queryString = queries.joinToString("+")
        return service.getBooks(queryString, startIndex, maxResults).items

    }


}