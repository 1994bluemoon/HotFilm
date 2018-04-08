package vinova.henry.com.hotfilm.feature.home

import android.content.ContentValues
import android.database.Cursor

import java.sql.SQLException

import vinova.henry.com.hotfilm.models.Movie

internal interface IHomeContract {
    interface IView {
        fun showSuccess()
        fun showFail()
        fun showLoadDataOfflineSuccess()
    }

    interface IPresenter {
        fun getUserFromServer(page: Int)
        fun getContentValue(movie: Movie): ContentValues
        fun portMovie(cursor: Cursor): Movie
    }
}
