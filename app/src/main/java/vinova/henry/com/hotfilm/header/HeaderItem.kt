package vinova.henry.com.hotfilm.header

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import vinova.henry.com.hotfilm.R
import vinova.henry.com.hotfilm.models.Header
import vinova.henry.com.hotfilm.models.HeaderDataSet
import vinova.henry.com.hotfilm.navigationtoolbar.HeaderLayout

class HeaderItem(view: View) : HeaderLayout.ViewHolder(view) {

    private val gradient = view.findViewById<View>(R.id.gradient)
    private val background = view.findViewById<ImageView>(R.id.image)
    internal val backgroundLayout = view.findViewById<View>(R.id.backgroud_layout)

    internal var overlayTitle: TextView? = null
    internal var overlayLine: View? = null

    fun setContent(content: Header?, title: TextView?, line: View?) {
        gradient.setBackgroundResource(content?.gradient ?: 0)
        Glide.with(background).load(content?.background).into(background)

        overlayTitle = title?.also {
            it.setTag(position)
            it.setText(content?.title)
            it.setVisibility(View.VISIBLE)
        }

        overlayLine = line
        overlayLine?.also {
            it.setTag(position)
            it.setVisibility(View.VISIBLE)
        }
    }

    fun clearContent() {
        overlayTitle?.also {
            it.setVisibility(View.GONE)
            it.setTag(null)
        }

        overlayLine?.also {
            it.setTag(null)
            it.setVisibility(View.GONE)
        }

        overlayTitle = null
        overlayLine = null
    }

}