package net.glinsey.archive.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import net.glinsey.archive.remote.NetworkUtil
import net.glinsey.model.Volume


class GoogleBooksRepo {
    private val service = NetworkUtil.booksApi

    private var _currentQuery = ""
    private var _currentIndex = 0

    private val _volumes : MutableLiveData<List<Volume>> = MutableLiveData(listOf())
    val volumes : LiveData<List<Volume>> = _volumes

    // TODO: Handle Errors
    suspend fun fetchVolumes(vararg queries: String, startIndex: Int = _currentIndex, maxResults: Int = 20){
        val lastQuery = _currentQuery
        _currentQuery = queries.joinToString("+")
        // If the query hasn't changed, append otherwise create a new list
        if(lastQuery == _currentQuery){
            _volumes.value =
                _volumes.value?.plus(service.getBooks(_currentQuery, startIndex, maxResults).items)
        }else{
            _currentIndex = 0
            _volumes.value = service.getBooks(_currentQuery, startIndex, maxResults).items

        }
        // Update our current index by 20 so that our next query begins where this one ends
        _currentIndex += 20



    }

    suspend fun loadNext(){
        fetchVolumes(_currentQuery, startIndex =  _currentIndex)
    }








}