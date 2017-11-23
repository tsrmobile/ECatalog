package th.co.thiensurat.ecatalog.profile;

import com.hwangjr.rxbus.RxBus;

import th.co.thiensurat.ecatalog.api.ServiceManager;
import th.co.thiensurat.ecatalog.base.BaseMvpPresenter;

/**
 * Created by teerayut.k on 10/18/2017.
 */

public class ProfilePresenter extends BaseMvpPresenter<ProfileInterface.View> implements ProfileInterface.Presenter {

    private ServiceManager serviceManager;

    public static ProfileInterface.Presenter create() {
        return new ProfilePresenter();
    }

    public ProfilePresenter() {
        serviceManager = ServiceManager.getInstance();
    }

    public void setManager( ServiceManager manager ){
        serviceManager = manager;
    }

    @Override
    public void onViewCreate() {
        RxBus.get().register( this );
    }

    @Override
    public void onViewDestroy() {
        RxBus.get().unregister( this );
    }

    @Override
    public void updateProfile(String id) {

    }

    @Override
    public void getProfile(String id) {

    }
}
