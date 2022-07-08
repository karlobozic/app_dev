package ie.tud.finddog.models

import android.os.Parcelable
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.parcelize.Parcelize

@IgnoreExtraProperties
@Parcelize
data class DogModel(
    var uid: String? = "",
    var name: String = "N/A",
    var date: String = "N/A",
    var breed: String = "N/A",
    var gender: String = "N/A",
    var profilepic: String = "",
    var email: String? = "joe@bloggs.com",
    var latitude: Double = 0.0,
    var longitude: Double = 0.0)
    : Parcelable
{
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "uid" to uid,
            "name" to name,
            "date" to date,
            "breed" to breed,
            "gender" to gender,
            "profilepic" to profilepic,
            "email" to email,
            "latitude" to latitude,
            "longitude" to longitude
        )
    }
}