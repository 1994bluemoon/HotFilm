package vinova.henry.com.hotfilm.feature.home;


import android.content.ContentValues;
import android.database.Cursor;

import java.sql.SQLException;
import java.util.List;

import vinova.henry.com.hotfilm.models.Movie;

interface IHomeContract {
    interface IView{
        void showSuccess() throws SQLException;
        void showFail();
        void showLoadDataOfflineSuccess();
    }

    interface IPresenter{
        void getUserFromServer(int page);
        /*void saveDataOffline(List<Movie> movies);
        void loadDataOffline();*/
        ContentValues getContentValue(Movie movie);
        Movie portMovie(Cursor cursor);
    }
}
