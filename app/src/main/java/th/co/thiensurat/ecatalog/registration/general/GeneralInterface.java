package th.co.thiensurat.ecatalog.registration.general;

import java.util.List;

import th.co.thiensurat.ecatalog.api.request.RequestRegistration;
import th.co.thiensurat.ecatalog.base.BaseMvpInterface;
import th.co.thiensurat.ecatalog.catalog.item.DataItem;
import th.co.thiensurat.ecatalog.catalog.item.DataItemGroup;
import th.co.thiensurat.ecatalog.catalog.item.TitleItem;
import th.co.thiensurat.ecatalog.catalog.item.TitleItemGroup;

/**
 * Created by teerayut.k on 10/20/2017.
 */

public class GeneralInterface {

    public interface View extends BaseMvpInterface.View {
        void onLoad();
        void onDismiss();
        void onFail(String fail);
        void onSuccess(String success);
        void setTitleName(final List<TitleItem> dataItemList);
        void setProvinceToSpinner(List<DataItem> dataItems);
        void setDistrictToSpinner(List<DataItem> dataItems);
        void setSubDistrictToSpinner(List<DataItem> dataItems);
    }

    public interface Presenter extends BaseMvpInterface.Presenter<GeneralInterface.View> {
        void requestTitleName();
        void setDataTitleGroup(TitleItemGroup dataGroup);
        TitleItemGroup getDataTitleGroup();
        void setDataTitleToAdapter(TitleItemGroup itemGroup);

        void requestDataItem(String key, String code);
        void onSetDataItemGroup(DataItemGroup itemGroup);
        DataItemGroup onGetDataItemGroup();
        void onRestoreDataItemToAdapter(DataItemGroup dataGroup);

        void creatFreeAgent(List<RequestRegistration.generalBody> generalbody);
    }
}
