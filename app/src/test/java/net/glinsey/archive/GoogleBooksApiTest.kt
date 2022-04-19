package net.glinsey.archive

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import net.glinsey.archive.remote.NetworkUtil
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GoogleBooksApiTest {

    private val service = NetworkUtil.booksApi
    @Test
    fun getBooksWithSingleQuery() = runTest{
        val response = service.getBooks("cat")
        assertTrue(response.items.isNotEmpty())

    }

    @Test
    fun getBooksFirst20Results() = runTest{
        val response = service.getBooks("cat", maxResults = 20)
        assertTrue(response.items.count() == 20)

    }




}