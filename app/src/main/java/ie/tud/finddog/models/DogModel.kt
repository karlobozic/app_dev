package ie.tud.finddog.models

import android.os.Parcelable
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.parcelize.Parcelize

@IgnoreExtraProperties
@Parcelize
data class DogModel(
    var uid: String = "",
    var name: String = "N/A",
    var area: String = "",
    var date: String = "",
    var breed: String = "",
    var gender: String = "",
    var email: String? = "joe@bloggs.com")
    : Parcelable
{
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "uid" to uid,
            "name" to name,
            "area" to area,
            "date" to date,
            "breed" to breed,
            "gender" to gender,
            "email" to email
        )
    }
}