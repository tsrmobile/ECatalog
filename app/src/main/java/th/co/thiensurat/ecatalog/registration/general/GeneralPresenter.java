package th.co.thiensurat.ecatalog.registration.general;

import android.util.Log;

import com.hwangjr.rxbus.RxBus;

import java.util.ArrayList;
import java.util.List;

import th.co.thiensurat.ecatalog.api.ServiceManager;
import th.co.thiensurat.ecatalog.api.request.RequestRegistration;
import th.co.thiensurat.ecatalog.api.result.DataItemResultGroup;
import th.co.thiensurat.ecatalog.api.result.Result;
import th.co.thiensurat.ecatalog.api.result.TitleItemResultGroup;
import th.co.thiensurat.ecatalog.base.BaseMvpPresenter;
import th.co.thiensurat.ecatalog.catalog.item.ConvertDataItem;
import th.co.thiensurat.ecatalog.catalog.item.ConvertTitleItem;
import th.co.thiensurat.ecatalog.catalog.item.DataItem;
import th.co.thiensurat.ecatalog.catalog.item.DataItemGroup;
import th.co.thiensurat.ecatalog.catalog.item.TitleItem;
import th.co.thiensurat.ecatalog.catalog.item.TitleItemGroup;

/**
 * Created by teerayut.k on 10/20/2017.
 */

public class GeneralPresenter extends BaseMvpPresenter<GeneralInterface.View> implements GeneralInterface.Presenter {

    private DataItemGroup dataItemGroup;
    private static TitleItemGroup dataTitleGroup;
    private static List<TitleItem> dataTitleList = new ArrayList<TitleItem>();
    private List<DataItem> dataItemList;
    private ServiceManager serviceManager;

    public static GeneralInterface.Presenter create() {
        return new GeneralPresenter();
    }

    public GeneralPresenter() {
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
    public void requestTitleName() {
        serviceManager.getTitle("titlename", new ServiceManager.ServiceManagerCallback<TitleItemResultGroup>() {
            @Override
            public void onSuccess(TitleItemResultGroup result) {
                if (result.getStatus().equals("SUCCESS")) {
                    TitleItemGroup titleItemGroup = ConvertTitleItem.createTitleItemGroupFromResult(result);
                    dataTitleGroup = titleItemGroup;
                    setDataTitleGroup(dataTitleGroup);

                    dataTitleList = ConvertTitleItem.createListTitleItemsFromResult(result.getData());
                    getView().setTitleName(dataTitleList);
                } else if (result.getStatus().equals("FAIL")) {
                    getView().onFail(result.getMessage().toString());
                } else if (result.getStatus().equals("ERROR")) {
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
    public void setDataTitleGroup(TitleItemGroup dataGroup) {
        this.dataTitleGroup = dataGroup;
    }

    @Override
    public TitleItemGroup getDataTitleGroup() {
        return dataTitleGroup;
    }

    @Override
    public void setDataTitleToAdapter(TitleItemGroup itemGroup) {
        this.dataTitleList = itemGroup.getData();
        getView().setTitleName(dataTitleList);
    }

    @Override
    public void requestDataItem(final String key, String code) {
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
                } else if (result.getMessage().equals("ERROR")) {
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
    public void creatFreeAgent(List<RequestRegistration.generalBody> generalbody) {
        getView().onLoad();
        serviceManager.createAgent(generalbody, new ServiceManager.ServiceManagerCallback<Result>() {
            @Override
            public void onSuccess(Result result) {
                Log.e("Result", result.getMessage() + ", " + result.getStatus());
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
