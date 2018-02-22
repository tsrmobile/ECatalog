package th.co.thiensurat.ecatalog.changepassword;

import android.util.Log;

import com.google.gson.Gson;
import com.hwangjr.rxbus.RxBus;

import org.json.JSONException;
import org.json.JSONObject;

import th.co.thiensurat.ecatalog.api.ServiceManager;
import th.co.thiensurat.ecatalog.base.BaseMvpPresenter;
import th.co.thiensurat.ecatalog.utils.Constance;
import th.co.thiensurat.ecatalog.utils.MyApplication;

/**
 * Created by teerayut.k on 1/16/2018.
 */

public class ChangePasswordPresenter extends BaseMvpPresenter<ChangePasswordInterface.View> implements ChangePasswordInterface.Presenter {

    private ServiceManager serviceManager;

    public static ChangePasswordInterface.Presenter create() {
        return new ChangePasswordPresenter();
    }

    public ChangePasswordPresenter() {
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
    public void requestChangePassword(String action, String id, String oldpassword, String password) {
        getView().onLoad();
        serviceManager.changePassword(action, id, oldpassword, password, new ServiceManager.ServiceManagerCallback() {
            @Override
            public void onSuccess(Object result) {
                Gson gson = new Gson();
                try {
                    JSONObject jsonObject = new JSONObject(gson.toJson(result));
                    if ("SUCCESS".equals(jsonObject.getString("status"))) {
                        MyApplication.getInstance().getPrefManager().setPreferrence(Constance.KEY_PASSWORD, jsonObject.getString("password"));
                        getView().onDismiss();
                        getView().onSuccess(jsonObject.getString("message"));
                    } else if ("FAIL".equals(jsonObject.getString("status"))) {
                        getView().onDismiss();
                        getView().onFail(jsonObject.getString("message"));
                    }
                } catch (JSONException e) {
                    Log.e("json obj", e.getLocalizedMessage());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("fail", t.getLocalizedMessage());
            }
        });
    }
}
