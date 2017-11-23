package th.co.thiensurat.ecatalog.registration.foreigner;

import android.util.Log;

import com.hwangjr.rxbus.RxBus;

import java.util.List;

import th.co.thiensurat.ecatalog.api.ServiceManager;
import th.co.thiensurat.ecatalog.api.request.RequestRegistration;
import th.co.thiensurat.ecatalog.api.result.DataItemResultGroup;
import th.co.thiensurat.ecatalog.api.result.Result;
import th.co.thiensurat.ecatalog.base.BaseMvpPresenter;
import th.co.thiensurat.ecatalog.catalog.item.ConvertDataItem;
import th.co.thiensurat.ecatalog.catalog.item.DataItem;
import th.co.thiensurat.ecatalog.catalog.item.DataItemGroup;

/**
 * Created by teerayut.k on 11/5/2017.
 */

public class ForeignerPresenter extends BaseMvpPresenter<ForeignerInterface.View> implements ForeignerInterface.Presenter {

    private ServiceManager serviceManager;
    private DataItemGroup dataCountryGroup;
    private List<DataItem> dataItemList;

    public static ForeignerInterface.Presenter create() {
        return new ForeignerPresenter();
    }

    public ForeignerPresenter() {
        serviceManager = ServiceManager.getInstance();
    }

    public void setManager( ServiceManager manager ){
        serviceManager = manager;
    }

    @Override
    public void onViewCreate() {
        RxBus.get().register( this );
    }

    @Override
    public void onViewDestroy() {
        RxBus.get().unregister( this );
    }

    @Override
    public void requestCountry() {
        getView().onLoad();
        serviceManager.loadCountry("country", new ServiceManager.ServiceManagerCallback<DataItemResultGroup>() {
            @Override
            public void onSuccess(DataItemResultGroup result) {
                if (result.getStatus().equals("SUCCESS")) {
                    getView().onDismiss();
                    DataItemGroup dataCountry = ConvertDataItem.createDataItemGroupFromResult(result);
                    dataCountryGroup = dataCountry;
                    setDataCountryGroup(dataCountryGroup);

                    dataItemList = ConvertDataItem.createListDataItemsFromResult(result.getData());
                    getView().setDataCountryToAdapter(dataItemList);
                } else if (result.getStatus().equals("FAIL")) {
                    getView().onDismiss();
                    getView().onFail(result.getMessage().toString());
                } else if (result.getStatus().equals("ERROR")) {
                    getView().onDismiss();
                    getView().onFail(result.getMessage().toString());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                getView().onDismiss();
            }
        });
    }

    @Override
    public void setDataCountryGroup(DataItemGroup dataGroup) {
        this.dataCountryGroup = dataGroup;
    }

    @Override
    public DataItemGroup getDataCountryGroup() {
        return dataCountryGroup;
    }

    @Override
    public void setDataCountryToAdapter(DataItemGroup itemGroup) {
        getView().setDataCountryToAdapter(itemGroup.getData());
    }

    @Override
    public void creatForeigner(List<RequestRegistration.generalBody> generalbody) {
        getView().onLoad();
        serviceManager.createAgent(generalbody, new ServiceManager.ServiceManagerCallback<Result>() {
            @Override
            public void onSuccess(Result result) {
                if (result.getStatus().equals("SUCCESS")) {
                    getView().onDismiss();
                    getView().onSuccess(result.getMessage().toString());
                } else if (result.getStatus().equals("FAIL")) {
                    getView().onDismiss();
                    getView().onFail(result.getMessage().toString());
                } else if (result.getStatus().equals("ERROR")) {
                    getView().onDismiss();
                    getView().onFail(result.getMessage().toString());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                getView().onDismiss();
            }
        });
    }
}
