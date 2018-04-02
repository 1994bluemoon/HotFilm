package vinova.henry.com.hotfilm.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TrailerResult {
    @SerializedName("id")
    @Expose
    val id: Int? = null
    @SerializedName("results")
    @Expose
    val trailerList: List<Trailer>? = null

}
