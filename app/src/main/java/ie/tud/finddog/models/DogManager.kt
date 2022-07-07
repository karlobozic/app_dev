//package ie.tud.finddog.models
//
//import androidx.lifecycle.MutableLiveData
//import com.google.firebase.auth.FirebaseUser
//import timber.log.Timber
//
//var lastId = 0L
//
//internal fun getId(): Long {
//    return lastId++
//}
//
//object DogManager : DogStore {
//
//    val dogs = ArrayList<DogModel>()
//
//    override fun findAll(dogsList: MutableLiveData<List<DogModel>>) {
//        return dogs
//    }
//
//    override fun findById(id:Long) : DogModel? {
//        val foundDog: DogModel? = dogs.find { it.id == id }
//        return foundDog
//    }
//
//    override fun create(firebaseUser: MutableLiveData<FirebaseUser>, dog: DogModel) {
//        dog.uid = getId()
//        dogs.add(dog)
//        logAll()
//    }
//
//    fun logAll() {
//        Timber.v("** Dog List **")
//        dogs.forEach { Timber.v("Dog ${it}") }
//    }
//
//}