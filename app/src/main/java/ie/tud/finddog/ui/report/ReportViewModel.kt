package ie.tud.finddog.ui.report

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import ie.tud.finddog.firebase.FirebaseDBManager
//import ie.tud.finddog.models.DogManager
import ie.tud.finddog.models.DogModel
import timber.log.Timber
import java.lang.Exception

class ReportViewModel : ViewModel() {


    private val dogsList =
        MutableLiveData<List<DogModel>>()

    val observableDogList: LiveData<List<DogModel>>
        get() = dogsList

    var liveFirebaseUser = MutableLiveData<FirebaseUser>()

    init {
        load()
    }

    fun load() {
        try {
            //DonationManager.findAll(liveFirebaseUser.value?.email!!, donationsList)
            FirebaseDBManager.findAll(liveFirebaseUser.value?.uid!!,
                dogsList)
            Timber.i("Report Load Success : ${dogsList.value.toString()}")
        }
        catch (e: Exception) {
            Timber.i("Report Load Error : $e.message")
        }
    }

    fun delete(userid: String, id: String) {
        try {
            //DonationManager.delete(userid,id)
            FirebaseDBManager.delete(userid,id)
            Timber.i("Report Delete Success")
        }
        catch (e: Exception) {
            Timber.i("Report Delete Error : $e.message")
        }
    }
}