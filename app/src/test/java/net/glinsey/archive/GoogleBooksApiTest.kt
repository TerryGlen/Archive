package net.glinsey.archive

import kotlinx.coroutines.test.runTest
import net.glinsey.archive.remote.NetworkUtil
import org.junit.Test

class GoogleBooksApiTest {

    val service = NetworkUtil.booksApi
    @Test
    fun getBooksWithSingleQuery() = runTest{
        val response = service.getBooks("cat")
        assert(response.totalItems > 0)

    }

    @Test
    fun getBooksWithQueries() = runTest {
        val response = service.getBooks(listOf("cat","bird").joinToString("+"))
        assert(response.totalItems > 0)
    }
}