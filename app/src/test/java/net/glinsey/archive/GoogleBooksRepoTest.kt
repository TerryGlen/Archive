package net.glinsey.archive

import kotlinx.coroutines.test.runTest
import net.glinsey.archive.repo.GoogleBooksRepo
import org.junit.Assert
import org.junit.Test

class GoogleBooksRepoTest {
    val repo = GoogleBooksRepo()

    @Test
    fun getBooksWithSingleQuery() = runTest{
        val volumes = repo.fetchVolumes("cat")
        Assert.assertTrue(volumes.isNotEmpty())

    }

    @Test
    fun getBooksWithMultipleQueries() = runTest{
        val volumes = repo.fetchVolumes("cat", "bird")
        Assert.assertTrue(volumes.isNotEmpty())

    }
    @Test
    fun getBooksFirst20Results() = runTest{
        val maxResults = 25
        val volumes = repo.fetchVolumes("cat", maxResults = maxResults)
        Assert.assertEquals(maxResults, volumes.count())

    }
}