package vinova.henry.com.hotfilm.models

interface HeaderDataSet {
    data class ItemData(val gradient: Int,
                        val background: Int,
                        val title: String?)

    fun getItemData(pos: Int): ItemData
}

interface PageDataSet {

    data class ItemData(val avatar: Int,
                        val userName: String,
                        val status: String)

    val secondItemImage: Int

    fun getItemData(pos: Int): ItemData
}

interface ViewPagerDataSet {
    fun getPageData(page: Int): PageDataSet
}