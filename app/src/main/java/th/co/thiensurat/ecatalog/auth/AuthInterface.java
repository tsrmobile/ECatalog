package th.co.thiensurat.ecatalog.auth;

import java.util.List;

import th.co.thiensurat.ecatalog.api.result.ItemAuth;
import th.co.thiensurat.ecatalog.auth.item.AuthenItemGroup;
import th.co.thiensurat.ecatalog.base.BaseMvpInterface;

/**
 * Created by teerayut.k on 10/16/2017.
 */

public class AuthInterface {

    public interface View extends BaseMvpInterface.View {
        void onLoad();
        void onDismiss();
        void onFail(String fail);
        void onSuccess();
    }

    public interface Presenter extends BaseMvpInterface.Presenter<AuthInterface.View> {
        void auth(List<ItemAuth> itemAuths);
        void senTokenToServer(String id, String token);
    }
}
