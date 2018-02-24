package vinova.henry.com.hotfilm.feature.home;

/**
 * Created by dminh on 2/24/2018.
 */

public class IHomeContract {
    interface IView{
        void showSuccess();
        void showFail();
    }

    interface IPresenter{
        void getUserFromServer(int page);
    }
}
