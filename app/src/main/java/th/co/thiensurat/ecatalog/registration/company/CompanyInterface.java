package th.co.thiensurat.ecatalog.registration.company;

import java.util.List;

import th.co.thiensurat.ecatalog.api.request.RequestRegistration;
import th.co.thiensurat.ecatalog.base.BaseMvpInterface;
import th.co.thiensurat.ecatalog.catalog.item.DataItem;
import th.co.thiensurat.ecatalog.catalog.item.DataItemGroup;

/**
 * Created by teerayut.k on 11/5/2017.
 */

public class CompanyInterface {

    public interface View extends BaseMvpInterface.View {
        void onLoad();
        void onDismiss();
        void onFail(String fail);
        void onSuccess(String success);
        void setProvinceToSpinner(List<DataItem> dataItems);
        void setDistrictToSpinner(List<DataItem> dataItems);
        void setSubDistrictToSpinner(List<DataItem> dataItems);
    }

    public interface Presenter extends BaseMvpInterface.Presenter<CompanyInterface.View> {
        void requestDataItem(String key, String code);
        void onSetDataItemGroup(DataItemGroup itemGroup);
        DataItemGroup onGetDataItemGroup();
        void onRestoreDataItemToAdapter(DataItemGroup dataGroup);

        void compRegister(List<RequestRegistration.generalBody> generalbody);
    }
}
