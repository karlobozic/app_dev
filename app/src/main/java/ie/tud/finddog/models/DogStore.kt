package ie.tud.finddog.models

import androidx.lifecycle.MutableLiveData

interface DogStore {
    fun findAll() : List<DogModel>
    fun findById(id: Long) : DogModel?
    fun create(dog: DogModel)
//    fun delete(email:String,id: String)


//    fun findAll(donationsList:
//                MutableLiveData<List<DogModel>>
//    )
//    fun findAll(email:String,
//                donationsList:
//                MutableLiveData<List<DogModel>>
//    )
//    fun findById(email:String, id: String,
//                 donation: MutableLiveData<DogModel>
//    )
//    fun create(donation: DogModel)
//    fun delete(email:String,id: String)
//    fun update(email:String,id: String,donation: DogModel)
}