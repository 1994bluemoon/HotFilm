package vinova.henry.com.hotfilm.feature.home

import android.animation.ObjectAnimator
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import vinova.henry.com.hotfilm.R
import vinova.henry.com.hotfilm.feature.search.SearchActivity
import vinova.henry.com.hotfilm.header.HeaderAdapter
import vinova.henry.com.hotfilm.header.HeaderItemTransformer
import vinova.henry.com.hotfilm.interf.IMovieEvent
import vinova.henry.com.hotfilm.models.Movie
import vinova.henry.com.hotfilm.navigationtoolbar.HeaderLayout
import vinova.henry.com.hotfilm.navigationtoolbar.HeaderLayoutManager
import vinova.henry.com.hotfilm.navigationtoolbar.NavigationToolBarLayout
import vinova.henry.com.hotfilm.navigationtoolbar.SimpleSnapHelper
import vinova.henry.com.hotfilm.pager.ViewPagerAdapter
import kotlin.math.ceil
import kotlin.math.max

class HomeActivity : AppCompatActivity(), IMovieEvent {

    private val itemCount = 18
    private var isExpanded = true
    private var prevAnchorPosition = 0
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var headerViewModel: HeaderViewModel
    private var curItem: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val header = findViewById<NavigationToolBarLayout>(R.id.navigation_toolbar_layout)
        val viewPager = findViewById<ViewPager>(R.id.pager)

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        headerViewModel = ViewModelProviders.of(this).get(HeaderViewModel::class.java)

        initActionBar()
        initViewPager(header, viewPager)
        initHeader(header, viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_search, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                startActivity(Intent(this@HomeActivity, SearchActivity::class.java))
                this@HomeActivity.finish()
                return false
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onItemRvClicked(movie: Movie?) {
        Toast.makeText(this, movie?.title, Toast.LENGTH_SHORT ).show()
    }

    override fun onLoadMoreListener(currentPage: Int) {
        when (curItem){
            0 -> {
                homeViewModel.setPageAction(currentPage + 1)
            }
            1 -> {
                homeViewModel.setPageAdventure(currentPage + 1)
            }
            2 -> {
                homeViewModel.setPageAnimation(currentPage + 1)
            }
            3 -> {
                homeViewModel.setPageCrime(currentPage + 1)
            }
            4 -> {
                homeViewModel.setPageDocumentary(currentPage + 1)
            }
            5 -> {
                homeViewModel.setPageDrama(currentPage + 1)
            }
            6 -> {
                homeViewModel.setPageFamily(currentPage + 1)
            }
            7 -> {
                homeViewModel.setPageFantasy(currentPage + 1)
            }
            8 -> {
                homeViewModel.setPageHistory(currentPage + 1)
            }
            9 -> {
                homeViewModel.setPageHorror(currentPage + 1)
            }
            10 -> {
                homeViewModel.setPageMusic(currentPage + 1)
            }
            11 -> {
                homeViewModel.setPageMystery(currentPage + 1)
            }
            12 -> {
                homeViewModel.setPageRomance(currentPage + 1)
            }
            13 -> {
                homeViewModel.setPageScienceFiction(currentPage + 1)
            }
            14 -> {
                homeViewModel.setPageThriller(currentPage + 1)
            }
            15 -> {
                homeViewModel.setPageTVMovie(currentPage + 1)
            }
            16 -> {
                homeViewModel.setPageWar(currentPage + 1)
            }
            17 -> {
                homeViewModel.setPageWestern(currentPage + 1)
            }

        }
    }

    private fun initActionBar() {
        val tb = findViewById<NavigationToolBarLayout>(R.id.navigation_toolbar_layout)
        val toolbar = tb.toolBar
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun initViewPager(header: NavigationToolBarLayout, viewPager: ViewPager) {
        viewPager.adapter = ViewPagerAdapter(itemCount, homeViewModel, this, this)
        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                header.smoothScrollToPosition(position)
                curItem = position
            }
        })
    }

    private fun initHeader(header: NavigationToolBarLayout, viewPager: ViewPager) {
        val titleLeftOffset = resources.getDimensionPixelSize(R.dimen.title_left_offset)
        val lineRightOffset = resources.getDimensionPixelSize(R.dimen.line_right_offset)
        val lineBottomOffset = resources.getDimensionPixelSize(R.dimen.line_bottom_offset)
        val lineTitleOffset = resources.getDimensionPixelSize(R.dimen.line_title_offset)

        val headerOverlay = findViewById<FrameLayout>(R.id.header_overlay)
        header.setItemTransformer(HeaderItemTransformer(headerOverlay,
                titleLeftOffset, lineRightOffset, lineBottomOffset, lineTitleOffset))
        /*header.setAdapter(HeaderAdapter(itemCount, headerViewModel, this, headerOverlay))*/
        headerViewModel.headers?.observe(this, Observer {
            header.setAdapter(HeaderAdapter(itemCount, it, headerOverlay))
        })

        header.addItemChangeListener(object : HeaderLayoutManager.ItemChangeListener {
            override fun onItemChangeStarted(position: Int) {
                prevAnchorPosition = position
            }

            override fun onItemChanged(position: Int) {
                viewPager.currentItem = position
            }
        })

        header.addItemClickListener(object : HeaderLayoutManager.ItemClickListener {
            override fun onItemClicked(viewHolder: HeaderLayout.ViewHolder) {
                viewPager.currentItem = viewHolder.position
            }
        })

        SimpleSnapHelper().attach(header)
        initDrawerArrow(header)
        initHeaderDecorator(header)
    }

    private fun initDrawerArrow(header: NavigationToolBarLayout) {
        val drawerArrow = DrawerArrowDrawable(this)
        drawerArrow.color = ContextCompat.getColor(this, android.R.color.white)
        drawerArrow.progress = 1f

        header.addHeaderChangeStateListener(object : HeaderLayoutManager.HeaderChangeStateListener() {
            private fun changeIcon(progress: Float) {
                ObjectAnimator.ofFloat(drawerArrow, "progress", progress).start()
                isExpanded = progress == 1f
                if (isExpanded) {
                    prevAnchorPosition = header.getAnchorPos()
                }
            }

            override fun onMiddle() = changeIcon(0f)
            override fun onExpanded() = changeIcon(1f)
        })

        val toolbar = header.toolBar
        toolbar.navigationIcon = drawerArrow
        toolbar.setNavigationOnClickListener {
            if (!isExpanded) {
                return@setNavigationOnClickListener
            }
            val anchorPos = header.getAnchorPos()
            if (anchorPos == HeaderLayout.INVALID_POSITION) {
                return@setNavigationOnClickListener
            }

            if (anchorPos == prevAnchorPosition) {
                header.collapse()
            } else {
                header.smoothScrollToPosition(prevAnchorPosition)
            }
        }
    }

    private fun initHeaderDecorator(header: NavigationToolBarLayout) {
        val decorator = object :
                HeaderLayoutManager.ItemDecoration,
                HeaderLayoutManager.HeaderChangeListener {

            private val dp5 = resources.getDimensionPixelSize(R.dimen.decor_bottom)

            private var bottomOffset = dp5

            override fun onHeaderChanged(lm: HeaderLayoutManager, header: HeaderLayout, headerBottom: Int) {
                val ratio = max(0f, headerBottom.toFloat() / header.height - 0.5f) / 0.5f
                bottomOffset = ceil(dp5 * ratio).toInt()
            }

            override fun getItemOffsets(outRect: Rect, viewHolder: HeaderLayout.ViewHolder) {
                outRect.bottom = bottomOffset
            }
        }

        header.addItemDecoration(decorator)
        header.addHeaderChangeListener(decorator)
    }
}
