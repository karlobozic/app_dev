package ie.tud.finddog.ui.report

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ie.tud.finddog.models.DogManager
import ie.tud.finddog.models.DogModel

class ReportViewModel : ViewModel() {

    private val dogsList = MutableLiveData<List<DogModel>>()

    val observableDogsList: LiveData<List<DogModel>>
        get() = dogsList

    init {
        load()
    }

    fun load() {
        dogsList.value = DogManager.findAll()
    }
}