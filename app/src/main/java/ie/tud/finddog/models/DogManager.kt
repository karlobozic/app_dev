package ie.tud.finddog.models

import timber.log.Timber

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

object DogManager : DogStore {

    val dogs = ArrayList<DogModel>()

    override fun findAll(): List<DogModel> {
        return dogs
    }

    override fun findById(id:Long) : DogModel? {
        val foundDog: DogModel? = dogs.find { it.id == id }
        return foundDog
    }

    override fun create(dog: DogModel) {
        dog.id = getId()
        dogs.add(dog)
        logAll()
    }

    fun logAll() {
        Timber.v("** Dog List **")
        dogs.forEach { Timber.v("Dog ${it}") }
    }

}