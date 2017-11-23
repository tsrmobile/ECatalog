package th.co.thiensurat.ecatalog.reports.cancel;

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

public class CancelPresenter extends BaseMvpPresenter<CancelInterface.View> implements CancelInterface.Presenter {

    private AllItemGroup cancelItemGroup;
    private List<AllItem> cancelItemList;
    private ServiceManager serviceManager;

    public static CancelInterface.Presenter create() {
        return new CancelPresenter();
    }

    public CancelPresenter() {
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
    public void requestCancelReport(String data, String id) {
        serviceManager.loadReport(data, id, new ServiceManager.ServiceManagerCallback<AllItemResultGroup>() {
            @Override
            public void onSuccess(AllItemResultGroup result) {
                if (result.getStatus().equals("SUCCESS")) {
                    AllItemGroup group = ConvertItemReport.createAllItemGroupFromResult(result);
                    cancelItemGroup = group;
                    cancelItemList = ConvertItemReport.createListAllItemFromResult(result.getData());
                    getView().setCancelItemToAdapter(cancelItemList);
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
    public void setCancelItemGroup(AllItemGroup cancelItemGroup) {
        this.cancelItemGroup = cancelItemGroup;
    }

    @Override
    public AllItemGroup getCancelItemGroup() {
        return cancelItemGroup;
    }

    @Override
    public void setCancelItemToAdapter(AllItemGroup cancelGroup) {
        getView().setCancelItemToAdapter(cancelGroup.getData());
    }
}
