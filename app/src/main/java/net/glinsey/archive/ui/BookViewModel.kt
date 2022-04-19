package net.glinsey.archive.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.glinsey.archive.repo.GoogleBooksRepo
import net.glinsey.model.Volume

class BookViewModel : ViewModel() {
    private val repository = GoogleBooksRepo()

    val volumeList : LiveData<List<Volume>> = repository.volumes

    private val _currentVolume = MutableLiveData<Volume?>()

    var currentVolume : LiveData<Volume?> = _currentVolume
        private set



    fun updateCurrentVolume(position: Int){

         _currentVolume.value = volumeList.value?.getOrNull(position)


    }

    fun search(query: String){
        viewModelScope.launch {
           repository.fetchVolumes(query)
        }
    }

    fun loadNext() {
        viewModelScope.launch {
            repository.loadNext()
        }
    }
}