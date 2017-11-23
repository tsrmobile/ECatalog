package th.co.thiensurat.ecatalog.reports.success;

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

public class SuccessPresenter extends BaseMvpPresenter<SuccessInterface.View> implements SuccessInterface.Presenter {

    private AllItemGroup completeAllItem;
    private List<AllItem> allItemList;
    private ServiceManager serviceManager;

    public static SuccessInterface.Presenter create() {
        return new SuccessPresenter();
    }

    public SuccessPresenter() {
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
    public void requestCompleteReport(String data, String id) {
        serviceManager.loadReport(data, id, new ServiceManager.ServiceManagerCallback<AllItemResultGroup>() {
            @Override
            public void onSuccess(AllItemResultGroup result) {
                if (result.getStatus().equals("SUCCESS")) {
                    AllItemGroup group = ConvertItemReport.createAllItemGroupFromResult(result);
                    completeAllItem = group;
                    allItemList = ConvertItemReport.createListAllItemFromResult(result.getData());
                    getView().setCompleteItemToAdapter(allItemList);
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
    public void setCompleteITemGroup(AllItemGroup iTemGroup) {
        this.completeAllItem = iTemGroup;
    }

    @Override
    public AllItemGroup getCompleteItemGroup() {
        return completeAllItem;
    }

    @Override
    public void setCompleteItemToAdapter(AllItemGroup allGroup) {
        getView().setCompleteItemToAdapter(allGroup.getData());
    }
}
