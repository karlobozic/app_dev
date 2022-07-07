package ie.tud.finddog.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DogModel(
    var id: Long = 0,
    val name: String = "N/A",
    val area: String = "N/A",
    val date: String = "N/A",
    val breed: String = "N/A",
    val gender: String = "N/A",
    val email: String = "joe@bloggs.com") : Parcelable