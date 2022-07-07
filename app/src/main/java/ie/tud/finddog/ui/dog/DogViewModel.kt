package ie.tud.finddog.ui.dog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ie.tud.finddog.models.DogManager
import ie.tud.finddog.models.DogModel

class DogViewModel : ViewModel() {

    private val status = MutableLiveData<Boolean>()

    val observableStatus: LiveData<Boolean>
        get() = status

    fun addDog(dog: DogModel) {
        status.value = try {
            DogManager.create(dog)
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }
}