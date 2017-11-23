package th.co.thiensurat.ecatalog;

import java.util.List;

import th.co.thiensurat.ecatalog.base.BaseMvpInterface;
import th.co.thiensurat.ecatalog.catalog.item.ProductItem;
import th.co.thiensurat.ecatalog.catalog.item.ProductItemGroup;

/**
 * Created by teerayut.k on 10/16/2017.
 */

public class MainInterface {

    public interface View extends BaseMvpInterface.View {
        /*void onLoad();
        void onDismiss();
        void onFail(String fail);
        void setProductToAdapter(List<ProductItem> productList);*/
    }

    public interface Presenter extends BaseMvpInterface.Presenter<MainInterface.View> {
        /*void requestProduct();
        void onSetProductGroup(ProductItemGroup productItemGroup);
        ProductItemGroup onGetProductGroup();
        void onRestoreProductToAdapter(ProductItemGroup productItemGroup);*/
    }
}
