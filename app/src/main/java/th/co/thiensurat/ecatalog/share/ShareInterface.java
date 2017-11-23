package th.co.thiensurat.ecatalog.share;

import java.util.List;

import th.co.thiensurat.ecatalog.base.BaseMvpInterface;
import th.co.thiensurat.ecatalog.share.item.BannerItem;

/**
 * Created by teerayut.k on 10/20/2017.
 */

public class ShareInterface {

    public interface View extends BaseMvpInterface.View {
        void onLoad();
        void onDismiss();
        void onFail(String fail);
        void onSuccess(String success);
        void setBannerToAdapter(List<BannerItem> bannerItemList);
    }

    public interface Presenter extends BaseMvpInterface.Presenter<ShareInterface.View> {
        void requestBanner();
    }
}
