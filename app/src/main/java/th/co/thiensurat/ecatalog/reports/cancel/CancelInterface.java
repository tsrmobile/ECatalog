package th.co.thiensurat.ecatalog.reports.cancel;

import java.util.List;

import th.co.thiensurat.ecatalog.base.BaseMvpInterface;
import th.co.thiensurat.ecatalog.reports.item.AllItem;
import th.co.thiensurat.ecatalog.reports.item.AllItemGroup;

/**
 * Created by teerayut.k on 11/7/2017.
 */

public class CancelInterface {

    public interface View extends BaseMvpInterface.View {
        void onLoad();
        void onDismiss();
        void onFail(String fail);
        void onSuccess(String succes);
        void setCancelItemToAdapter(List<AllItem> allItemList);
    }

    public interface Presenter extends BaseMvpInterface.Presenter<CancelInterface.View> {
        void requestCancelReport(String data, String id);
        void setCancelItemGroup(AllItemGroup cancelItemGroup);
        AllItemGroup getCancelItemGroup();
        void setCancelItemToAdapter(AllItemGroup cancelGroup);
    }
}
