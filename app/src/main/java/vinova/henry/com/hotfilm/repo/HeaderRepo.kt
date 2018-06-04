package vinova.henry.com.hotfilm.repo

import vinova.henry.com.hotfilm.*
import vinova.henry.com.hotfilm.models.HeaderDataSet

class HeaderRepo {
    private val headerBackgrounds = intArrayOf(R.drawable.card_1_background, R.drawable.card_2_background, R.drawable.card_3_background, R.drawable.card_4_background).toTypedArray()
    private val headerGradients = intArrayOf(R.drawable.card_1_gradient, R.drawable.card_2_gradient, R.drawable.card_3_gradient, R.drawable.card_4_gradient).toTypedArray()
    private val headerTitles: Array<String?> = arrayOf(GENRE_ACTION.name, GENRE_ADVENTURE.name, GENRE_ANIMATION.name, GENRE_CRIME.name)

    val headerDataSet = object : HeaderDataSet {
        override fun getItemData(pos: Int) = HeaderDataSet.ItemData(
                    gradient = headerGradients[pos % headerGradients.size],
                    background = headerBackgrounds[pos % headerBackgrounds.size],
                    title = headerTitles[pos % headerTitles.size])
    }

}