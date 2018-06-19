package vinova.henry.com.hotfilm.models.trailer

import android.os.Parcel
import android.os.Parcelable

data class Trailer(var id: String? = null,
                   var iso_639_1: String? = null,
                   var iso_3166_1: String? = null,
                   var key: String? = null,
                   var name: String? = null,
                   var site: String? = null,
                   var size: Int? = null,
                   var type: String? = null) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(iso_639_1)
        parcel.writeString(iso_3166_1)
        parcel.writeString(key)
        parcel.writeString(name)
        parcel.writeString(site)
        parcel.writeValue(size)
        parcel.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Trailer> {
        override fun createFromParcel(parcel: Parcel): Trailer {
            return Trailer(parcel)
        }

        override fun newArray(size: Int): Array<Trailer?> {
            return arrayOfNulls(size)
        }
    }
}
