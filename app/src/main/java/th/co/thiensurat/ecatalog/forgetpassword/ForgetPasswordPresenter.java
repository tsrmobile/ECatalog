package th.co.thiensurat.ecatalog.forgetpassword;

import android.util.Log;

import com.google.gson.Gson;
import com.hwangjr.rxbus.RxBus;

import org.json.JSONException;
import org.json.JSONObject;

import th.co.thiensurat.ecatalog.api.ServiceManager;
import th.co.thiensurat.ecatalog.base.BaseMvpPresenter;

/**
 * Created by teerayut.k on 1/15/2018.
 */

public class ForgetPasswordPresenter extends BaseMvpPresenter<ForgetPasswordInterface.View> implements ForgetPasswordInterface.Presenter {

    private ServiceManager serviceManager;

    public static ForgetPasswordInterface.Presenter create() {
        return new ForgetPasswordPresenter();
    }

    public ForgetPasswordPresenter() {
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
    public void requestReset(String email) {
        getView().onLoad();
        serviceManager.resetPassword("reset", email, new ServiceManager.ServiceManagerCallback() {
            @Override
            public void onSuccess(Object result) {
                Gson gson = new Gson();
                try {
                    JSONObject jsonObject = new JSONObject(gson.toJson(result));
                    if ("SUCCESS".equals(jsonObject.getString("status"))) {
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
