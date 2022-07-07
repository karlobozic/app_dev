package ie.tud.finddog.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ie.tud.finddog.models.DogManager
import ie.tud.finddog.models.DogModel

class DogDetailViewModel : ViewModel() {
    private val dog = MutableLiveData<DogModel>()

    val observableDog: LiveData<DogModel>
        get() = dog

    fun getDog(id: Long) {
        dog.value = DogManager.findById(id)
    }
}