package net.glinsey.archive.ui.booklist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.glinsey.archive.repo.GoogleBooksRepo
import net.glinsey.model.Volume

class BookListViewModel : ViewModel() {
    private val repository = GoogleBooksRepo()
    private val _currentQuery = MutableLiveData<String>()
    var currentQuery = _currentQuery
    private set

    private val _volumesList = MutableLiveData<List<Volume>>()

    var volumesList = _volumesList
    private set

    init{
        viewModelScope.launch {
            _volumesList.value = repository.fetchVolumes("cat")
        }
    }
}