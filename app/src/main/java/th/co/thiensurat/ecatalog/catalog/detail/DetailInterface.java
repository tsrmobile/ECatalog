package th.co.thiensurat.ecatalog.catalog.detail;

import java.util.List;

import th.co.thiensurat.ecatalog.api.request.RequestOrder;
import th.co.thiensurat.ecatalog.api.result.ItemOrder;
import th.co.thiensurat.ecatalog.base.BaseMvpInterface;
import th.co.thiensurat.ecatalog.catalog.item.DataItem;
import th.co.thiensurat.ecatalog.catalog.item.DataItemGroup;
import th.co.thiensurat.ecatalog.catalog.item.TitleItem;
import th.co.thiensurat.ecatalog.catalog.item.TitleItemGroup;

/**
 * Created by teerayut.k on 10/16/2017.
 */

public class DetailInterface {

    public interface View extends BaseMvpInterface.View {
        void onLoad();
        void onDismiss();
        void onFail(String fail);
        void onSuccess(String success);
        void setProvince(final List<DataItem> dataItemList);
        void setTitleName(final List<TitleItem> dataItemList);
        void setCustomertype(final List<DataItem> dataTypeList);
    }

    public interface Presenter extends BaseMvpInterface.Presenter<DetailInterface.View> {
        void requestProvince(String key, String code);
        void setDataItemGroup(DataItemGroup dataGroup);
        DataItemGroup getDataItemGroup();
        void setDataToAdapter(DataItemGroup itemGroup);

        void requestTitleName(String key);
        void setDataTitleGroup(TitleItemGroup dataGroup);
        TitleItemGroup getDataTitleGroup();
        void setDataTitleToAdapter(TitleItemGroup itemGroup);

        void requestType(String key);
        void setDataTypeGroup(DataItemGroup dataGroup);
        DataItemGroup getDataTypeGroup();
        void setDataTypeToAdapter(DataItemGroup itemGroup);

        void addOrder(List<RequestOrder.orderBody> orderList);
    }
}
