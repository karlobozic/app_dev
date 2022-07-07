package ie.tud.finddog.ui.dog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import ie.tud.finddog.firebase.FirebaseDBManager
import ie.tud.finddog.firebase.FirebaseImageManager
//import ie.tud.finddog.models.DogManager
import ie.tud.finddog.models.DogModel

class DogViewModel : ViewModel() {

    private val status = MutableLiveData<Boolean>()

    val observableStatus: LiveData<Boolean>
        get() = status

    fun addDog(firebaseUser: MutableLiveData<FirebaseUser>,
               dog: DogModel) {
        status.value = try {
            dog.profilepic = FirebaseImageManager.imageUri.value.toString()
            //DonationManager.create(donation)
            FirebaseDBManager.create(firebaseUser,dog)
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }
}