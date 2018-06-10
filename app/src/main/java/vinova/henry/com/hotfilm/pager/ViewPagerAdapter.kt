package vinova.henry.com.hotfilm.pager

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.support.v4.view.PagerAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.LinearLayout
import android.widget.Toast
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
        val linearLayoutManager = LinearLayoutManager(view.context, LinearLayout.VERTICAL, false)
        initRecyclerView(view as RecyclerView, position, linearLayoutManager)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, key: Any) {
        container.removeView(key as View)
    }

    override fun getPageTitle(position: Int): CharSequence = position.toString()



    private fun initRecyclerView(recyclerView: RecyclerView, position: Int, linearLayoutManager: LinearLayoutManager) {
        var isLoading: Boolean = false
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView?.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                if (lastVisibleItem == totalItemCount - 10 && !isLoading){
                    iMovieEvent?.onLoadMoreListener(totalItemCount/20)
                    isLoading = true
                }
            }
        })

        when (position){
            0 -> {
                val adapter = PageAdapter(iMovieEvent)
                recyclerView.adapter = adapter
                homeViewModel.actionMovies.observe(homeLifecycle, Observer {
                    adapter.setMovies(it?.results)
                    isLoading = false
                }) }
            1 -> {
                val adapter = PageAdapter(iMovieEvent)
                recyclerView.adapter = adapter
                homeViewModel.adventureMovies.observe(homeLifecycle, Observer {
                    adapter.setMovies(it?.results)
                    isLoading = false  }) }
            2 -> {
                val adapter = PageAdapter(iMovieEvent)
                recyclerView.adapter = adapter
                homeViewModel.animationMovies.observe(homeLifecycle, Observer {
                    adapter.setMovies(it?.results)
                    isLoading = false
                }) }
            3 -> {
                val adapter = PageAdapter(iMovieEvent)
                recyclerView.adapter = adapter
                homeViewModel.crimeMovies.observe(homeLifecycle, Observer {
                    adapter.setMovies(it?.results)
                    isLoading = false
                }) }
            4 -> {
                val adapter = PageAdapter(iMovieEvent)
                recyclerView.adapter = adapter
                homeViewModel.documentaryMovies.observe(homeLifecycle, Observer {
                    adapter.setMovies(it?.results)
                    isLoading = false
                }) }
            5 -> {
                val adapter = PageAdapter(iMovieEvent)
                recyclerView.adapter = adapter
                homeViewModel.dramaMovies.observe(homeLifecycle, Observer {
                    adapter.setMovies(it?.results)
                    isLoading = false
                }) }
            6 -> {
                val adapter = PageAdapter(iMovieEvent)
                recyclerView.adapter = adapter
                homeViewModel.familyMovies.observe(homeLifecycle, Observer {
                    adapter.setMovies(it?.results)
                    isLoading = false
                }) }
            7 -> {
                val adapter = PageAdapter(iMovieEvent)
                recyclerView.adapter = adapter
                homeViewModel.fantasyMovies.observe(homeLifecycle, Observer {
                    adapter.setMovies(it?.results)
                    isLoading = false
                }) }
            8 -> {
                val adapter = PageAdapter(iMovieEvent)
                recyclerView.adapter = adapter
                homeViewModel.historyMovies.observe(homeLifecycle, Observer {
                    adapter.setMovies(it?.results)
                    isLoading = false
                }) }
            9 -> {
                val adapter = PageAdapter(iMovieEvent)
                recyclerView.adapter = adapter
                homeViewModel.horrorMovies.observe(homeLifecycle, Observer {
                    adapter.setMovies(it?.results)
                    isLoading = false
                }) }
            10 -> {
                val adapter = PageAdapter(iMovieEvent)
                recyclerView.adapter = adapter
                homeViewModel.musicMovies.observe(homeLifecycle, Observer {
                    adapter.setMovies(it?.results)
                    isLoading = false
                }) }
            11 -> {
                val adapter = PageAdapter(iMovieEvent)
                recyclerView.adapter = adapter
                homeViewModel.mysteryMovies.observe(homeLifecycle, Observer {
                    adapter.setMovies(it?.results)
                    isLoading = false
                }) }
            12 -> {
                val adapter = PageAdapter(iMovieEvent)
                recyclerView.adapter = adapter
                homeViewModel.romanceMovies.observe(homeLifecycle, Observer {
                    adapter.setMovies(it?.results)
                    isLoading = false
                }) }
            13 -> {
                val adapter = PageAdapter(iMovieEvent)
                recyclerView.adapter = adapter
                homeViewModel.scienceFictionMovies.observe(homeLifecycle, Observer {
                    adapter.setMovies(it?.results)
                    isLoading = false
                }) }
            14 -> {
                val adapter = PageAdapter(iMovieEvent)
                recyclerView.adapter = adapter
                homeViewModel.thrillerMovies.observe(homeLifecycle, Observer {
                    adapter.setMovies(it?.results)
                    isLoading = false
                }) }
            15 -> {
                val adapter = PageAdapter(iMovieEvent)
                recyclerView.adapter = adapter
                homeViewModel.tvMovies.observe(homeLifecycle, Observer {
                    adapter.setMovies(it?.results)
                    isLoading = false
                }) }
            16 -> {
                val adapter = PageAdapter(iMovieEvent)
                recyclerView.adapter = adapter
                homeViewModel.warMovies.observe(homeLifecycle, Observer {
                    adapter.setMovies(it?.results)
                    isLoading = false
                }) }
            17 -> {
                val adapter = PageAdapter(iMovieEvent)
                recyclerView.adapter = adapter
                homeViewModel.westernMovies.observe(homeLifecycle, Observer {
                    adapter.setMovies(it?.results)
                    isLoading = false
                }) }
        }
    }

}

/*var actionPage = 1; var adventurePage = 1; var animationPage = 1; var crimePage = 1; var documentaryPage = 1; var dramaPage = 1; var familyPage = 1; var fantasyPage = 1; var historyPage = 1; var horrorPage = 1; var musicPage = 1; var mysteryPage = 1; var scifiPage = 1; var tvMoviePage = 1; var thrillerPage = 1; var warPage = 1; var westernPage = 1; var romancePage = 1

       recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
           override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
               super.onScrolled(recyclerView, dx, dy)
               val totalItemCount = linearLayoutManager.getItemCount();
               val lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

               if (lastVisibleItem == totalItemCount - 10 && !isLoading){
                   when (position){
                       0 -> {
                           actionPage += 1
                           homeViewModel.setPageAction(actionPage)
                           isLoading = true
                       }
                       1 -> {
                           adventurePage += 1
                           homeViewModel.setPageAdventure(adventurePage)
                           isLoading = true
                       }
                       2 -> {
                           animationPage += 1
                           homeViewModel.setPageAnimation(animationPage)
                           isLoading = true
                       }
                       3 -> {
                           crimePage += 1
                           homeViewModel.setPageCrime(crimePage)
                           isLoading = true
                       }
                       4 -> {
                           documentaryPage += 1
                           homeViewModel.setPageDocumentary(documentaryPage)
                           isLoading = true
                       }
                       5 -> {
                           dramaPage += 1
                           homeViewModel.setPageDrama(dramaPage)
                           isLoading = true
                       }
                       6 -> {
                           familyPage += 1
                           homeViewModel.setPageFamily(familyPage)
                           isLoading = true
                       }
                       7 -> {
                           fantasyPage += 1
                           homeViewModel.setPageFantasy(fantasyPage)
                           isLoading = true
                       }
                       8 -> {
                           historyPage += 1
                           homeViewModel.setPageHistory(historyPage)
                           isLoading = true
                       }
                       9 -> {
                           horrorPage += 1
                           homeViewModel.setPageHorror(horrorPage)
                           isLoading = true
                       }
                       10 -> {
                           musicPage += 1
                           homeViewModel.setPageMusic(musicPage)
                           isLoading = true
                       }
                       11 -> {
                           mysteryPage += 1
                           homeViewModel.setPageMystery(mysteryPage)
                           isLoading = true
                       }
                       12 -> {
                           romancePage += 1
                           homeViewModel.setPageRomance(romancePage)
                           isLoading = true
                       }
                       13 -> {
                           scifiPage += 1
                           homeViewModel.setPageScienceFiction(scifiPage)
                           isLoading = true
                       }
                       14 -> {
                           thrillerPage += 1
                           homeViewModel.setPageThriller(thrillerPage)
                           isLoading = true
                       }
                       15 -> {
                           tvMoviePage += 1
                           homeViewModel.setPageTVMovie(tvMoviePage)
                           isLoading = true
                       }
                       16 -> {
                           warPage += 1
                           homeViewModel.setPageWar(warPage)
                           isLoading = true
                       }
                       17 -> {
                           westernPage += 1
                           homeViewModel.setPageWestern(westernPage)
                           isLoading = true
                       }

                   }
               }
           }
       })*/
