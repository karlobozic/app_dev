package ie.tud.finddog.models

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser

interface DogStore {
    fun findAll(dogList:
                MutableLiveData<List<DogModel>>)
    fun findAll(userid:String,
                dogList:
                MutableLiveData<List<DogModel>>)
    fun findById(userid:String, dogList: String,
                 dog: MutableLiveData<DogModel>)
    fun create(firebaseUser: MutableLiveData<FirebaseUser>, dog: DogModel)
    fun delete(userid:String, dogid: String)
    fun update(userid:String, dogid: String, dog: DogModel)
}