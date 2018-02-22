package th.co.thiensurat.ecatalog.pinview.pinauthen;

import java.util.List;

import th.co.thiensurat.ecatalog.api.result.ItemAuth;
import th.co.thiensurat.ecatalog.base.BaseMvpInterface;
import th.co.thiensurat.ecatalog.base.BaseMvpPresenter;

/**
 * Created by teerayut.k on 2/22/2018.
 */

public class PinAuthenInterface {

    public interface View extends BaseMvpInterface.View {
        void onLoad();
        void onDismiss();
        void onFail(String fail);
        void onSuccess();
    }

    public interface Presenter extends BaseMvpInterface.Presenter<PinAuthenInterface.View> {
        void auth(List<ItemAuth> itemAuths);
    }
}
