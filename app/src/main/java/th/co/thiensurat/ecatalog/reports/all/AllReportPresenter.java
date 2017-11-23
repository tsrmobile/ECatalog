package th.co.thiensurat.ecatalog.reports.all;

import com.hwangjr.rxbus.RxBus;

import java.util.List;

import th.co.thiensurat.ecatalog.api.ServiceManager;
import th.co.thiensurat.ecatalog.api.result.AllItemResultGroup;
import th.co.thiensurat.ecatalog.base.BaseMvpPresenter;
import th.co.thiensurat.ecatalog.reports.item.AllItem;
import th.co.thiensurat.ecatalog.reports.item.AllItemGroup;
import th.co.thiensurat.ecatalog.reports.item.ConvertItemReport;

/**
 * Created by teerayut.k on 11/7/2017.
 */

public class AllReportPresenter extends BaseMvpPresenter<AllReportInterface.View> implements AllReportInterface.Presenter {

    private ServiceManager serviceManager;
    private AllItemGroup allItemGroup;
    private List<AllItem> allItemList;

    public static AllReportInterface.Presenter create() {
        return new AllReportPresenter();
    }

    public AllReportPresenter() {
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
    public void requestReport(String data, String id) {
        serviceManager.loadReport(data, id, new ServiceManager.ServiceManagerCallback<AllItemResultGroup>() {
            @Override
            public void onSuccess(AllItemResultGroup result) {
                if (result.getStatus().equals("SUCCESS")) {
                    AllItemGroup group = ConvertItemReport.createAllItemGroupFromResult(result);
                    allItemGroup = group;
                    setAllITemGroup(allItemGroup);
                    allItemList = ConvertItemReport.createListAllItemFromResult(result.getData());
                    getView().setAllItemToAdapter(allItemList);
                } else if (result.getStatus().equals("FAIL")) {
                    getView().onFail(result.getMessage().toString());
                } else if (result.getStatus().equals("ERROR")) {
                    getView().onFail(result.getMessage().toString());
                }
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });
    }

    @Override
    public void setAllITemGroup(AllItemGroup iTemGroup) {
        this.allItemGroup = iTemGroup;
    }

    @Override
    public AllItemGroup getAllItemGroup() {
        return allItemGroup;
    }

    @Override
    public void setItemToAdapter(AllItemGroup allGroup) {
        getView().setAllItemToAdapter(allGroup.getData());
    }
}
