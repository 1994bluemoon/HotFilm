package vinova.henry.com.hotfilm.models

data class MovieResponse(var page: Int? = null,
                         var total_results: Int? = null,
                         var total_pages: Int? = null,
                         var results: List<Movie>? = null)