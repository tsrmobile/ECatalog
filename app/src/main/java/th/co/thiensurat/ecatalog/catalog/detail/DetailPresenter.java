package th.co.thiensurat.ecatalog.catalog.detail;

import com.hwangjr.rxbus.RxBus;

import java.util.ArrayList;
import java.util.List;

import th.co.thiensurat.ecatalog.api.ServiceManager;
import th.co.thiensurat.ecatalog.api.request.RequestOrder;
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
 * Created by teerayut.k on 10/16/2017.
 */

public class DetailPresenter extends BaseMvpPresenter<DetailInterface.View> implements DetailInterface.Presenter {

    private ServiceManager serviceManager;
    private static DataItemGroup dataItemGroup;
    private static DataItemGroup dataTypeGroup;
    private static TitleItemGroup dataTitleGroup;
    private static List<TitleItem> dataTitleList = new ArrayList<TitleItem>();
    private static List<DataItem> dataItemList = new ArrayList<DataItem>();
    private static List<DataItem> dataTypeList = new ArrayList<DataItem>();

    public static DetailInterface.Presenter create() {
        return new DetailPresenter();
    }

    public DetailPresenter() {
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
    public void requestProvince(String key, String code) {
        getView().onLoad();
        serviceManager.getDataItem(key, code, new ServiceManager.ServiceManagerCallback<DataItemResultGroup>() {
            @Override
            public void onSuccess(DataItemResultGroup result) {
                if (result.getMessage().equals("SUCCESS")) {
                    getView().onDismiss();
                    DataItemGroup dataGroup = ConvertDataItem.createDataItemGroupFromResult(result);
                    dataItemGroup = dataGroup;
                    setDataItemGroup(dataItemGroup);

                    dataItemList = ConvertDataItem.createListDataItemsFromResult(result.getData());
                    getView().setProvince(dataItemList);
                } else if (result.getMessage().equals("FAIL")) {
                    getView().onDismiss();
                    getView().onFail(result.getData().get(0).getDataId());
                } else if (result.getMessage().equals("ERROR")) {
                    getView().onDismiss();
                    getView().onFail(result.getData().get(0).getDataId());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                getView().onDismiss();
            }
        });
    }

    @Override
    public void setDataItemGroup(DataItemGroup dataGroup) {
        this.dataItemGroup = dataGroup;
    }

    @Override
    public DataItemGroup getDataItemGroup() {
        return dataItemGroup;
    }

    @Override
    public void setDataToAdapter(DataItemGroup itemGroup) {
        this.dataItemList = itemGroup.getData();
        getView().setProvince(dataItemList);
    }

    @Override
    public void requestTitleName(String key) {
        serviceManager.getTitle("titlename", new ServiceManager.ServiceManagerCallback<TitleItemResultGroup>() {
            @Override
            public void onSuccess(TitleItemResultGroup result) {
                if (result.getMessage().equals("SUCCESS")) {
                    TitleItemGroup titleItemGroup = ConvertTitleItem.createTitleItemGroupFromResult(result);
                    dataTitleGroup = titleItemGroup;
                    setDataTitleGroup(dataTitleGroup);

                    dataTitleList = ConvertTitleItem.createListTitleItemsFromResult(result.getData());
                    getView().setTitleName(dataTitleList);
                } else if (result.getMessage().equals("FAIL")) {
                    getView().onFail(result.getData().get(0).getDataId());
                } else if (result.getMessage().equals("ERROR")) {
                    getView().onFail(result.getData().get(0).getDataId());
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
    public void addOrder(List<RequestOrder.orderBody> orderList) {
        getView().onLoad();
        serviceManager.addOrder(orderList, new ServiceManager.ServiceManagerCallback<Result>() {
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

    @Override
    public void requestType(String key) {
        serviceManager.getCustomerType(key, new ServiceManager.ServiceManagerCallback<DataItemResultGroup>() {
            @Override
            public void onSuccess(DataItemResultGroup result) {
                if (result.getStatus().equals("SUCCESS")) {
                    DataItemGroup dataGroup = ConvertDataItem.createDataItemGroupFromResult(result);
                    dataTypeGroup = dataGroup;
                    setDataTypeGroup(dataTypeGroup);

                    dataTypeList = ConvertDataItem.createListDataItemsFromResult(result.getData());
                    getView().setCustomertype(dataTypeList);
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
    public void setDataTypeGroup(DataItemGroup dataGroup) {
        this.dataTypeGroup = dataGroup;
    }

    @Override
    public DataItemGroup getDataTypeGroup() {
        return dataTypeGroup;
    }

    @Override
    public void setDataTypeToAdapter(DataItemGroup itemGroup) {
        getView().setCustomertype(itemGroup.getData());
    }
}
