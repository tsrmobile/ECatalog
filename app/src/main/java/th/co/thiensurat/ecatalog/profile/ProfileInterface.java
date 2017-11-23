package th.co.thiensurat.ecatalog.profile;

import th.co.thiensurat.ecatalog.base.BaseMvpInterface;

/**
 * Created by teerayut.k on 10/18/2017.
 */

public class ProfileInterface {

    public interface View extends BaseMvpInterface.View {
        void onLoad();
        void onDismiss();
        void onFail(String fail);
        void onSuccess(String success);
    }

    public interface Presenter extends BaseMvpInterface.Presenter<ProfileInterface.View> {
        void updateProfile(String id);
        void getProfile(String id);
    }
}
