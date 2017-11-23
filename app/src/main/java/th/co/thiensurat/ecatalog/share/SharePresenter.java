package th.co.thiensurat.ecatalog.share;

import com.hwangjr.rxbus.RxBus;

import java.util.ArrayList;
import java.util.List;

import th.co.thiensurat.ecatalog.api.ServiceManager;
import th.co.thiensurat.ecatalog.api.result.BannerItemResultGroup;
import th.co.thiensurat.ecatalog.base.BaseMvpPresenter;
import th.co.thiensurat.ecatalog.share.item.BannerItem;
import th.co.thiensurat.ecatalog.share.item.BannerItemGroup;
import th.co.thiensurat.ecatalog.share.item.ConvertBanner;

/**
 * Created by teerayut.k on 10/20/2017.
 */

public class SharePresenter extends BaseMvpPresenter<ShareInterface.View> implements ShareInterface.Presenter {

    private BannerItemGroup bannerItemGroup;
    private ServiceManager serviceManager;
    private List<BannerItem> bannerItemList = new ArrayList<BannerItem>();

    public static ShareInterface.Presenter create() {
        return new SharePresenter();
    }

    public SharePresenter() {
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
    public void requestBanner() {
        getView().onLoad();
        serviceManager.getBanner("banner", new ServiceManager.ServiceManagerCallback<BannerItemResultGroup>() {
            @Override
            public void onSuccess(BannerItemResultGroup result) {
                if (result.getStatus().equals("SUCCESS")) {
                    getView().onDismiss();
                    bannerItemList = ConvertBanner.createListBannerFromResult(result.getData());
                    getView().setBannerToAdapter(bannerItemList);
                } else if (result.getStatus().equals("FAIL")) {
                    getView().onDismiss();
                    getView().onFail(result.getMessage());
                } else if (result.getStatus().equals("ERROR")) {
                    getView().onDismiss();
                    getView().onFail(result.getMessage());
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
