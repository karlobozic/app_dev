package ie.tud.finddog.firebase

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import ie.tud.finddog.models.DogModel
import ie.tud.finddog.models.DogStore
import timber.log.Timber

object FirebaseDBManager : DogStore {

    var database: DatabaseReference = FirebaseDatabase.getInstance().reference

    override fun findAll(dogList: MutableLiveData<List<DogModel>>) {
        TODO("Not yet implemented")
    }

    override fun findAll(userid: String, dogsList: MutableLiveData<List<DogModel>>) {

        database.child("user-dogs").child(userid)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Timber.i("Firebase Donation error : ${error.message}")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    val localList = ArrayList<DogModel>()
                    val children = snapshot.children
                    children.forEach {
                        val dog = it.getValue(DogModel::class.java)
                        localList.add(dog!!)
                    }
                    database.child("user-dogs").child(userid)
                        .removeEventListener(this)

                    dogsList.value = localList
                }
            })
    }

    override fun findById(userid: String, dogid: String, dog: MutableLiveData<DogModel>) {

        database.child("user-dogs").child(userid)
            .child(dogid).get().addOnSuccessListener {
                dog.value = it.getValue(DogModel::class.java)
                Timber.i("firebase Got value ${it.value}")
            }.addOnFailureListener{
                Timber.e("firebase Error getting data $it")
            }
    }

    override fun create(firebaseUser: MutableLiveData<FirebaseUser>, dog: DogModel) {
        Timber.i("Firebase DB Reference : $database")

        val uid = firebaseUser.value!!.uid
        val key = database.child("dogs").push().key
        if (key == null) {
            Timber.i("Firebase Error : Key Empty")
            return
        }
        dog.uid = key
        val dogValues = dog.toMap()

        val childAdd = HashMap<String, Any>()
        childAdd["/dogs/$key"] = dogValues
        childAdd["/user-dogs/$uid/$key"] = dogValues

        database.updateChildren(childAdd)
    }

    override fun delete(userid: String, dogid: String) {

        val childDelete : MutableMap<String, Any?> = HashMap()
        childDelete["/dogs/$dogid"] = null
        childDelete["/user-dogs/$userid/$dogid"] = null

        database.updateChildren(childDelete)
    }

    override fun update(userid: String, dogid: String, dog: DogModel) {

        val dogValues = dog.toMap()

        val childUpdate : MutableMap<String, Any?> = HashMap()
        childUpdate["dogs/$dogid"] = dogValues
        childUpdate["user-dogs/$userid/$dogid"] = dogValues

        database.updateChildren(childUpdate)
    }
}