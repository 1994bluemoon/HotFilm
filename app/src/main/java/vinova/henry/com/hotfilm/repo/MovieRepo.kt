package vinova.henry.com.hotfilm.repo

import android.arch.lifecycle.MutableLiveData
import android.hardware.camera2.CameraCaptureSession
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vinova.henry.com.hotfilm.API_KEY
import vinova.henry.com.hotfilm.models.MovieResponse
import vinova.henry.com.hotfilm.models.Movie
import vinova.henry.com.hotfilm.server.ServiceGenerator

class MovieRepo{

    fun getDescoverMovie(page: Int): MutableLiveData<List<Movie>>?{
        val movies: MutableLiveData<List<Movie>>? = MutableLiveData()
        ServiceGenerator.theMovieDBService.getDiscoverMovie(API_KEY, "en-US", "popularity.desc", page).enqueue(object : Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {

                movies?.value = response?.body()?.results
            }
        })
        return movies
    }

    fun getActionMovie(page: Int) : MutableLiveData<List<Movie>>?{
        val movies: MutableLiveData<List<Movie>>? = MutableLiveData()
        ServiceGenerator.theMovieDBService.getActionMovie(API_KEY, "en-US", "created_at.asc", page).enqueue(object : Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {
                movies?.value = response?.body()?.results
            }

        })
        return movies
    }
    fun getAdventureMovie(page: Int) : MutableLiveData<List<Movie>>?{
        val movies: MutableLiveData<List<Movie>>? = MutableLiveData()
        ServiceGenerator.theMovieDBService.getAdventureMovie(API_KEY, "en-US", "created_at.asc", page).enqueue(object : Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {
                movies?.value = response?.body()?.results
            }

        })
        return movies
    }
    fun getAnimationMovie(page: Int) : MutableLiveData<List<Movie>>?{
        val movies: MutableLiveData<List<Movie>>? = MutableLiveData()
        ServiceGenerator.theMovieDBService.getAnimationMovie(API_KEY, "en-US", "created_at.asc", page).enqueue(object : Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {
                movies?.value = response?.body()?.results
            }

        })
        return movies
    }
    fun getDocumentaryMovie(page: Int) : MutableLiveData<List<Movie>>?{
        val movies: MutableLiveData<List<Movie>>? = MutableLiveData()
        ServiceGenerator.theMovieDBService.getDocumentaryMovie(API_KEY, "en-US", "created_at.asc", page).enqueue(object : Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {
                movies?.value = response?.body()?.results
            }

        })
        return movies
    }

    fun getFamilyMovie(page: Int) : MutableLiveData<List<Movie>>?{
        val movies: MutableLiveData<List<Movie>>? = MutableLiveData()
        ServiceGenerator.theMovieDBService.getFamilyMovie(API_KEY, "en-US", "created_at.asc", page).enqueue(object : Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {
                movies?.value = response?.body()?.results
            }

        })
        return movies
    }
    fun getCrimeMovie(page: Int) : MutableLiveData<List<Movie>>?{
        val movies: MutableLiveData<List<Movie>>? = MutableLiveData()
        ServiceGenerator.theMovieDBService.getCrimeMovie(API_KEY, "en-US", "created_at.asc", page).enqueue(object : Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {
                movies?.value = response?.body()?.results
            }

        })
        return movies
    }
    fun getDramaMovie(page: Int) : MutableLiveData<List<Movie>>?{
        val movies: MutableLiveData<List<Movie>>? = MutableLiveData()
        ServiceGenerator.theMovieDBService.getDramaMovie(API_KEY, "en-US", "created_at.asc", page).enqueue(object : Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {
                movies?.value = response?.body()?.results
            }

        })
        return movies
    }
    fun getFantasyMovie(page: Int) : MutableLiveData<List<Movie>>?{
        val movies: MutableLiveData<List<Movie>>? = MutableLiveData()
        ServiceGenerator.theMovieDBService.getFantasyMovie(API_KEY, "en-US", "created_at.asc", page).enqueue(object : Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {
                movies?.value = response?.body()?.results
            }

        })
        return movies
    }
    fun getHistoryMovie(page: Int) : MutableLiveData<List<Movie>>?{
        val movies: MutableLiveData<List<Movie>>? = MutableLiveData()
        ServiceGenerator.theMovieDBService.getHistoryMovie(API_KEY, "en-US", "created_at.asc", page).enqueue(object : Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {
                movies?.value = response?.body()?.results
            }

        })
        return movies
    }
    fun getHorrorMovie(page: Int) : MutableLiveData<List<Movie>>?{
        val movies: MutableLiveData<List<Movie>>? = MutableLiveData()
        ServiceGenerator.theMovieDBService.getHorrorMovie(API_KEY, "en-US", "created_at.asc", page).enqueue(object : Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {
                movies?.value = response?.body()?.results
            }

        })
        return movies
    }
    fun getMusicMovie(page: Int) : MutableLiveData<List<Movie>>?{
        val movies: MutableLiveData<List<Movie>>? = MutableLiveData()
        ServiceGenerator.theMovieDBService.getMusicMovie(API_KEY, "en-US", "created_at.asc", page).enqueue(object : Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {
                movies?.value = response?.body()?.results
            }

        })
        return movies
    }
    fun getMysteryMovie(page: Int) : MutableLiveData<List<Movie>>?{
        val movies: MutableLiveData<List<Movie>>? = MutableLiveData()
        ServiceGenerator.theMovieDBService.getMysteryMovie(API_KEY, "en-US", "created_at.asc", page).enqueue(object : Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {
                movies?.value = response?.body()?.results
            }

        })
        return movies
    }
    fun getRomanceMovie(page: Int) : MutableLiveData<List<Movie>>?{
        val movies: MutableLiveData<List<Movie>>? = MutableLiveData()
        ServiceGenerator.theMovieDBService.getRomanceMovie(API_KEY, "en-US", "created_at.asc", page).enqueue(object : Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {
                movies?.value = response?.body()?.results
            }

        })
        return movies
    }
    fun getScienceMovie(page: Int) : MutableLiveData<List<Movie>>?{
        val movies: MutableLiveData<List<Movie>>? = MutableLiveData()
        ServiceGenerator.theMovieDBService.getScienceMovie(API_KEY, "en-US", "created_at.asc", page).enqueue(object : Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {
                movies?.value = response?.body()?.results
            }

        })
        return movies
    }
    fun getTVMovie(page: Int) : MutableLiveData<List<Movie>>?{
        val movies: MutableLiveData<List<Movie>>? = MutableLiveData()
        ServiceGenerator.theMovieDBService.getTVMovie(API_KEY, "en-US", "created_at.asc", page).enqueue(object : Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {
                movies?.value = response?.body()?.results
            }

        })
        return movies
    }
    fun getThrillerMovie(page: Int) : MutableLiveData<List<Movie>>?{
        val movies: MutableLiveData<List<Movie>>? = MutableLiveData()
        ServiceGenerator.theMovieDBService.getThrillerMovie(API_KEY, "en-US", "created_at.asc", page).enqueue(object : Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {
                movies?.value = response?.body()?.results
            }

        })
        return movies
    }

    fun getWarMovie(page: Int) : MutableLiveData<List<Movie>>?{
        val movies: MutableLiveData<List<Movie>>? = MutableLiveData()
        ServiceGenerator.theMovieDBService.getWarMovie(API_KEY, "en-US", "created_at.asc", page).enqueue(object : Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {
                movies?.value = response?.body()?.results
            }

        })
        return movies
    }
    fun getWesternMovie(page: Int) : MutableLiveData<List<Movie>>?{
        val movies: MutableLiveData<List<Movie>>? = MutableLiveData()
        ServiceGenerator.theMovieDBService.getWesternMovie(API_KEY, "en-US", "created_at.asc", page).enqueue(object : Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {
                movies?.value = response?.body()?.results
            }

        })
        return movies
    }
}