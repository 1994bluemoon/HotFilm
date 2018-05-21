package vinova.henry.com.hotfilm.navigationtoolbar

import vinova.henry.com.hotfilm.navigationtoolbar.HeaderLayoutManager.ScrollState
import vinova.henry.com.hotfilm.navigationtoolbar.HeaderLayoutManager.ScrollState.IDLE
import java.lang.ref.WeakReference

class SimpleSnapHelper : HeaderLayoutManager.ScrollStateListener {

    private var toolBarRef: WeakReference<NavigationToolBarLayout>? = null

    override fun onScrollStateChanged(state: ScrollState) {
        if (state != IDLE) {
            return
        }

        toolBarRef?.get()?.also { toolbar ->
            toolbar.smoothScrollToPosition(toolbar.getAnchorPos())
        }
    }

    fun attach(toolbar: NavigationToolBarLayout) {
        toolBarRef = WeakReference(toolbar)
        toolbar.addScrollStateListener(this)
    }

    fun detach(toolbar: NavigationToolBarLayout) {
        toolBarRef = null
        toolbar.removeScrollStateListener(this)
    }

}