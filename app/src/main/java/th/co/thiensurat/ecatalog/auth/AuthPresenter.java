package th.co.thiensurat.ecatalog.auth;

import android.util.Log;

import com.hwangjr.rxbus.RxBus;

import java.util.List;

import th.co.thiensurat.ecatalog.api.ServiceManager;
import th.co.thiensurat.ecatalog.api.result.AuthItemResultGroup;
import th.co.thiensurat.ecatalog.api.result.ItemAuth;
import th.co.thiensurat.ecatalog.auth.item.AuthenItemGroup;
import th.co.thiensurat.ecatalog.auth.item.ConvertAuthItem;
import th.co.thiensurat.ecatalog.base.BaseMvpPresenter;
import th.co.thiensurat.ecatalog.utils.MyApplication;

/**
 * Created by teerayut.k on 10/16/2017.
 */

public class AuthPresenter extends BaseMvpPresenter<AuthInterface.View> implements AuthInterface.Presenter {

    private ServiceManager serviceManager;

    public static AuthInterface.Presenter create() {
        return new AuthPresenter();
    }

    public AuthPresenter() {
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
    public void auth(List<ItemAuth> itemAuths) {
        getView().onLoad();
        serviceManager.getAuth(itemAuths, new ServiceManager.ServiceManagerCallback<AuthItemResultGroup>() {
            @Override
            public void onSuccess(AuthItemResultGroup result) {
                if (result.getStatus().equals("SUCCESS")) {
                    getView().onDismiss();
                    AuthenItemGroup authenItemGroup = ConvertAuthItem.createAuthItemGroupFromResult(result);
                    MyApplication.getInstance().getPrefManager().setProfile(authenItemGroup);
                    getView().onSuccess();
                } else if (result.getStatus().equals("FAIL")) {
                    getView().onDismiss();
                    getView().onFail(result.getMessage());
                } else if (result.getStatus().equals("ERROR")) {
                    getView().onDismiss();
                    getView().onFail(result.getMessage());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Authen", t.getMessage());
                getView().onDismiss();
            }
        });
    }
}
