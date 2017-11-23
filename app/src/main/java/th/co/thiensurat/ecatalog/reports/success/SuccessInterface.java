package th.co.thiensurat.ecatalog.reports.success;

import java.util.List;

import th.co.thiensurat.ecatalog.base.BaseMvpInterface;
import th.co.thiensurat.ecatalog.reports.item.AllItem;
import th.co.thiensurat.ecatalog.reports.item.AllItemGroup;

/**
 * Created by teerayut.k on 11/7/2017.
 */

public class SuccessInterface {

    public interface View extends BaseMvpInterface.View {
        void onLoad();
        void onDismiss();
        void onFail(String fail);
        void onSuccess(String success);
        void setCompleteItemToAdapter(List<AllItem> allItemList);
    }

    public interface Presenter extends BaseMvpInterface.Presenter<SuccessInterface.View> {
        void requestCompleteReport(String data, String id);
        void setCompleteITemGroup(AllItemGroup iTemGroup);
        AllItemGroup getCompleteItemGroup();
        void setCompleteItemToAdapter(AllItemGroup allGroup);
    }
}
