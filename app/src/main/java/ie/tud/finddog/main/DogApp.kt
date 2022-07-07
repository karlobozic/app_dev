package ie.tud.finddog.main

import android.app.Application
//import ie.tud.finddog.models.DogMemStore
import ie.tud.finddog.models.DogStore
import timber.log.Timber

class DogApp : Application() {

//    lateinit var dogsStore: DogStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
//        dogsStore = DogMemStore()
        Timber.i("Starting Dog Application")

    }
}