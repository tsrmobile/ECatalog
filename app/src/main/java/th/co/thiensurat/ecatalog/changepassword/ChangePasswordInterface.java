package th.co.thiensurat.ecatalog.changepassword;

import th.co.thiensurat.ecatalog.base.BaseMvpInterface;

/**
 * Created by teerayut.k on 1/16/2018.
 */

public class ChangePasswordInterface {

    public interface View extends BaseMvpInterface.View {
        void onLoad();
        void onDismiss();
        void onFail(String fail);
        void onSuccess(String success);
    }

    public interface Presenter extends BaseMvpInterface.Presenter<ChangePasswordInterface.View> {
        void requestChangePassword(String action, String id, String oldpassword, String password);
    }
}
