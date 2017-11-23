package th.co.thiensurat.ecatalog.reports.all;

import java.util.List;

import th.co.thiensurat.ecatalog.base.BaseMvpInterface;
import th.co.thiensurat.ecatalog.reports.item.AllItem;
import th.co.thiensurat.ecatalog.reports.item.AllItemGroup;

/**
 * Created by teerayut.k on 11/7/2017.
 */

public class AllReportInterface {

    public interface View extends BaseMvpInterface.View {
        void onLoad();
        void onDismiss();
        void onFail(String fail);
        void onSuccess(String success);
        void setAllItemToAdapter(final List<AllItem> allItems);
    }

    public interface Presenter extends BaseMvpInterface.Presenter<AllReportInterface.View> {
        void requestReport(String data, String id);
        void setAllITemGroup(AllItemGroup iTemGroup);
        AllItemGroup getAllItemGroup();
        void setItemToAdapter(AllItemGroup allGroup);
    }
}
