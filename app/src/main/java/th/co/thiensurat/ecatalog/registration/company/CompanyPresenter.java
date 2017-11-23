package th.co.thiensurat.ecatalog.registration.company;

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

public class CompanyPresenter extends BaseMvpPresenter<CompanyInterface.View> implements CompanyInterface.Presenter {

    private ServiceManager serviceManager;

    private List<DataItem> dataItemList;
    private DataItemGroup dataItemGroup;

    public static CompanyInterface.Presenter create() {
        return new CompanyPresenter();
    }

    public CompanyPresenter() {
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
    public void requestDataItem(final String key, String code) {
        getView().onLoad();
        serviceManager.getDataItem(key, code, new ServiceManager.ServiceManagerCallback<DataItemResultGroup>() {
            @Override
            public void onSuccess(DataItemResultGroup result) {
                if (result.getStatus().equals("SUCCESS")) {
                    getView().onDismiss();
                    DataItemGroup dataGroup = ConvertDataItem.createDataItemGroupFromResult(result);
                    dataItemGroup = dataGroup;

                    dataItemList = ConvertDataItem.createListDataItemsFromResult(result.getData());
                    if (key.equals("province")) {
                        getView().setProvinceToSpinner(dataItemList);
                        onSetDataItemGroup(dataItemGroup);
                    } else if (key.equals("district")) {
                        getView().setDistrictToSpinner(dataItemList);
                    } else if (key.equals("subdistrict")){
                        getView().setSubDistrictToSpinner(dataItemList);
                    }

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
    public void onSetDataItemGroup(DataItemGroup itemGroup) {
        this.dataItemGroup = itemGroup;
    }

    @Override
    public DataItemGroup onGetDataItemGroup() {
        return dataItemGroup;
    }

    @Override
    public void onRestoreDataItemToAdapter(DataItemGroup dataGroup) {
        getView().setProvinceToSpinner(dataGroup.getData());
    }

    @Override
    public void compRegister(List<RequestRegistration.generalBody> generalbody) {
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
                Log.e("Comp register", t.getLocalizedMessage());
            }
        });
    }
}
