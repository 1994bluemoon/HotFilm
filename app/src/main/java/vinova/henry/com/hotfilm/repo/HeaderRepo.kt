package vinova.henry.com.hotfilm.repo

import android.arch.lifecycle.MutableLiveData
import vinova.henry.com.hotfilm.*
import vinova.henry.com.hotfilm.models.Header

class HeaderRepo {
    private val headerBackgrounds = intArrayOf(R.drawable.card_1_background,
            R.drawable.card_2_background,
            R.drawable.card_3_background,
            R.drawable.card_4_background,
            R.drawable.card_1_background,
            R.drawable.card_2_background,
            R.drawable.card_3_background,
            R.drawable.card_4_background,
            R.drawable.card_1_background,
            R.drawable.card_2_background,
            R.drawable.card_3_background,
            R.drawable.card_4_background,
            R.drawable.card_1_background,
            R.drawable.card_2_background,
            R.drawable.card_3_background,
            R.drawable.card_4_background,
            R.drawable.card_1_background,
            R.drawable.card_2_background).toTypedArray()

    private val headerGradients = intArrayOf(R.drawable.card_1_gradient,
            R.drawable.card_2_gradient,
            R.drawable.card_3_gradient,
            R.drawable.card_4_gradient,
            R.drawable.card_1_gradient,
            R.drawable.card_2_gradient,
            R.drawable.card_3_gradient,
            R.drawable.card_4_gradient,
            R.drawable.card_1_gradient,
            R.drawable.card_2_gradient,
            R.drawable.card_3_gradient,
            R.drawable.card_4_gradient,
            R.drawable.card_1_gradient,
            R.drawable.card_2_gradient,
            R.drawable.card_3_gradient,
            R.drawable.card_4_gradient,
            R.drawable.card_1_gradient,
            R.drawable.card_2_gradient).toTypedArray()

    private val headerTitles: Array<String?> = arrayOf(GENRE_ACTION.name,
            GENRE_ADVENTURE.name,
            GENRE_ANIMATION.name,
            GENRE_CRIME.name,
            GENRE_DOCUMENTARY.name,
            GENRE_DRAMA.name,
            GENRE_FAMILY.name,
            GENRE_FANTASY.name,
            GENRE_HISTORY.name,
            GENRE_HORROR.name,
            GENRE_MUSIC.name,
            GENRE_MYSTERY.name,
            GENRE_ROMANCE.name,
            GENRE_SCIENCE_FICTION.name,
            GENRE_THRILLER.name,
            GENRE_TV_MOVIE.name,
            GENRE_WAR.name,
            GENRE_WESTERN.name)

   /* val headerDataSet = object : HeaderDataSet {
        override fun getItemData(pos: Int) = HeaderDataSet.ItemData(
                    gradient = headerGradients[pos],
                    background = headerBackgrounds[pos],
                    title = headerTitles[pos])
    }*/

    fun getHeaders() : MutableLiveData<List<Header>>?{
        var headers = MutableLiveData<List<Header>>()
        var headerlist = ArrayList<Header>()
        for (i in 0..headerTitles.size - 1){
            headerlist.add(Header(headerGradients[i], headerBackgrounds[i], headerTitles[i]))
        }
        headers.value = headerlist
        return headers
    }

}