package th.co.thiensurat.ecatalog.forgetpassword;

import th.co.thiensurat.ecatalog.base.BaseMvpInterface;
import th.co.thiensurat.ecatalog.base.BaseMvpPresenter;

/**
 * Created by teerayut.k on 1/15/2018.
 */

public class ForgetPasswordInterface {

    public interface View extends BaseMvpInterface.View {
        void onLoad();
        void onDismiss();
        void onFail(String fail);
        void onSuccess(String success);
    }

    public interface Presenter extends BaseMvpInterface.Presenter<ForgetPasswordInterface.View> {
        void requestReset(String email);
    }
}
