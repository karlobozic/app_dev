package ie.tud.finddog.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ie.tud.finddog.firebase.FirebaseDBManager
//import ie.tud.finddog.models.DogManager
import ie.tud.finddog.models.DogModel
import timber.log.Timber
import java.lang.Exception

class DogDetailViewModel : ViewModel() {
    private val dog = MutableLiveData<DogModel>()

    var observableDog: LiveData<DogModel>
        get() = dog
        set(value) {dog.value = value.value}

    fun getDog(userid:String, id: String) {
        try {
            //DonationManager.findById(email, id, donation)
            FirebaseDBManager.findById(userid, id, dog)
            Timber.i("Detail getDog() Success : ${
                dog.value.toString()}")
        }
        catch (e: Exception) {
            Timber.i("Detail getDog() Error : $e.message")
        }
    }

    fun updateDog(userid:String, id: String,dog: DogModel) {
        try {
            //DonationManager.update(email, id, donation)
            FirebaseDBManager.update(userid, id, dog)
            Timber.i("Detail update() Success : $dog")
        }
        catch (e: Exception) {
            Timber.i("Detail update() Error : $e.message")
        }
    }
}