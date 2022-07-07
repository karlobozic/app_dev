package ie.tud.finddog.ui.map;

class MapsViewModel : ViewModel() {
        lateinit var map : GoogleMap
        var currentLocation = MutableLiveData<Location>()

}