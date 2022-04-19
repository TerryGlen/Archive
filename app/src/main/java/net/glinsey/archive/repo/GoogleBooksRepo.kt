package net.glinsey.archive.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.*
import net.glinsey.archive.remote.NetworkUtil
import net.glinsey.model.Volume
import net.glinsey.model.VolumeResponse


class GoogleBooksRepo {
    private val service = NetworkUtil.booksApi

    private var _currentQuery = ""
    private var _currentIndex = 0

    private val _volumes : MutableLiveData<List<Volume>> = MutableLiveData(listOf())
    val volumes : LiveData<List<Volume>> = _volumes

    // TODO: Handle Errors
    suspend fun fetchVolumes(vararg queries: String, startIndex: Int = _currentIndex, maxResults: Int = 20){
        _currentQuery = queries.joinToString("+")

        _volumes.value =
            _volumes.value?.plus(service.getBooks(_currentQuery, startIndex, maxResults).items)

        _currentIndex += 20
    }

    suspend fun loadNext(){
        fetchVolumes(_currentQuery, startIndex =  _currentIndex)
    }








}