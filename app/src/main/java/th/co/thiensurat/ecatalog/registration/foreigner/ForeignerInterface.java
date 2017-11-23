package th.co.thiensurat.ecatalog.registration.foreigner;

import java.util.List;

import th.co.thiensurat.ecatalog.api.request.RequestRegistration;
import th.co.thiensurat.ecatalog.base.BaseMvpInterface;
import th.co.thiensurat.ecatalog.catalog.item.DataItem;
import th.co.thiensurat.ecatalog.catalog.item.DataItemGroup;

/**
 * Created by teerayut.k on 11/5/2017.
 */

public class ForeignerInterface {

    public interface View extends BaseMvpInterface.View {
        void onLoad();
        void onDismiss();
        void onFail(String fail);
        void onSuccess(String success);
        void setDataCountryToAdapter(final List<DataItem> dataCountryList);
    }

    public interface Presenter extends BaseMvpInterface.Presenter<ForeignerInterface.View> {
        void requestCountry();
        void setDataCountryGroup(DataItemGroup dataGroup);
        DataItemGroup getDataCountryGroup();
        void setDataCountryToAdapter(DataItemGroup itemGroup);

        void creatForeigner(List<RequestRegistration.generalBody> generalbody);
    }
}
