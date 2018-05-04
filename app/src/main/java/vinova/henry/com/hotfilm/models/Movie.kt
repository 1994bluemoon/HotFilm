package vinova.henry.com.hotfilm.models

import java.io.Serializable

data class Movie(var vote_count: Int? = null, var id: Int? = null, var vote_average: Double? = null, var title: String? = null, var popularity: Double? = null, var poster_path: String? = null, var original_language: String? = null, var original_title: String? = null, var backdrop_path: String? = null, var overview: String? = null, var release_date: String? = null): Serializable