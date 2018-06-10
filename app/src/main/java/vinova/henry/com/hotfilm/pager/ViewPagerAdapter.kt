package vinova.henry.com.hotfilm.pager

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.support.v4.view.PagerAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import vinova.henry.com.hotfilm.R
import vinova.henry.com.hotfilm.feature.home.HomeViewModel
import vinova.henry.com.hotfilm.interf.IMovieEvent

class ViewPagerAdapter(private val count: Int,
                       private val homeViewModel: HomeViewModel,
                       private val homeLifecycle: LifecycleOwner,
                       private val iMovieEvent: IMovieEvent?) : PagerAdapter() {

    override fun getCount(): Int = count

    override fun isViewFromObject(view: View, key: Any): Boolean = view == key

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(R.layout.pager_item, container, false)
        initRecyclerView(view as RecyclerView, position)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, key: Any) {
        container.removeView(key as View)
    }

    override fun getPageTitle(position: Int): CharSequence = position.toString()

    private fun initRecyclerView(recyclerView: RecyclerView, position: Int) {
        /*val adapter = PageAdapter(random.nextInt(10) + 5, dataSet.getPageData(position))
        recyclerView.adapter = adapter*/

        when (position){
            0 -> { homeViewModel.actionMovies.observe(homeLifecycle, Observer { recyclerView.adapter = PageAdapter(it?.results, iMovieEvent) }) }
            1 -> { homeViewModel.adventureMovies.observe(homeLifecycle, Observer { recyclerView.adapter = PageAdapter(it?.results, iMovieEvent) }) }
            2 -> { homeViewModel.animationMovies.observe(homeLifecycle, Observer { recyclerView.adapter = PageAdapter(it?.results, iMovieEvent) }) }
            3 -> { homeViewModel.crimeMovies.observe(homeLifecycle, Observer { recyclerView.adapter = PageAdapter(it?.results, iMovieEvent) }) }
            4 -> { homeViewModel.documentaryMovies.observe(homeLifecycle, Observer { recyclerView.adapter = PageAdapter(it?.results, iMovieEvent) }) }
            5 -> { homeViewModel.dramaMovies.observe(homeLifecycle, Observer { recyclerView.adapter = PageAdapter(it?.results, iMovieEvent) }) }
            6 -> { homeViewModel.familyMovies.observe(homeLifecycle, Observer { recyclerView.adapter = PageAdapter(it?.results, iMovieEvent) }) }
            7 -> { homeViewModel.fantasyMovies.observe(homeLifecycle, Observer { recyclerView.adapter = PageAdapter(it?.results, iMovieEvent) }) }
            8 -> { homeViewModel.historyMovies.observe(homeLifecycle, Observer { recyclerView.adapter = PageAdapter(it?.results, iMovieEvent) }) }
            9 -> { homeViewModel.horrorMovies.observe(homeLifecycle, Observer { recyclerView.adapter = PageAdapter(it?.results, iMovieEvent) }) }
            10 -> { homeViewModel.musicMovies.observe(homeLifecycle, Observer { recyclerView.adapter = PageAdapter(it?.results, iMovieEvent) }) }
            11 -> { homeViewModel.mysteryMovies.observe(homeLifecycle, Observer { recyclerView.adapter = PageAdapter(it?.results, iMovieEvent) }) }
            12 -> { homeViewModel.romanceMovies.observe(homeLifecycle, Observer { recyclerView.adapter = PageAdapter(it?.results, iMovieEvent) }) }
            13 -> { homeViewModel.scienceFictionMovies.observe(homeLifecycle, Observer { recyclerView.adapter = PageAdapter(it?.results, iMovieEvent) }) }
            14 -> { homeViewModel.thrillerMovies.observe(homeLifecycle, Observer { recyclerView.adapter = PageAdapter(it?.results, iMovieEvent) }) }
            15 -> { homeViewModel.tvMovies.observe(homeLifecycle, Observer { recyclerView.adapter = PageAdapter(it?.results, iMovieEvent) }) }
            16 -> { homeViewModel.warMovies.observe(homeLifecycle, Observer { recyclerView.adapter = PageAdapter(it?.results, iMovieEvent) }) }
            17 -> { homeViewModel.westernMovies.observe(homeLifecycle, Observer { recyclerView.adapter = PageAdapter(it?.results, iMovieEvent) }) }
        }
    }

}
