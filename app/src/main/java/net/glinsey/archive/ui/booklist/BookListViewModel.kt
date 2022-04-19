package net.glinsey.archive.ui.booklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.glinsey.archive.repo.GoogleBooksRepo
import net.glinsey.model.Volume

class BookListViewModel : ViewModel() {
    private val repository = GoogleBooksRepo()
    private val _currentQuery = MutableLiveData<String>()
    var currentQuery : LiveData<String> = _currentQuery
    private set

    private val _volumesList = MutableLiveData<List<Volume>>()
    var volumesList : LiveData<List<Volume>> = _volumesList
    private set

    private val _currentVolume = MutableLiveData<Volume?>()

    var currentVolume : LiveData<Volume?> = _currentVolume
        private set


    init{
        viewModelScope.launch {
            _volumesList.value = repository.fetchVolumes("cat")
        }
    }

    fun updateCurrentVolume(position: Int){
        _currentVolume.value = _volumesList.value?.getOrNull(position)

    }
}