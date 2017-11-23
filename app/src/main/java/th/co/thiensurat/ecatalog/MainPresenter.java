package th.co.thiensurat.ecatalog;

import android.util.Log;

import com.hwangjr.rxbus.RxBus;

import java.util.ArrayList;
import java.util.List;

import th.co.thiensurat.ecatalog.api.ServiceManager;
import th.co.thiensurat.ecatalog.api.result.ProductItemResultGroup;
import th.co.thiensurat.ecatalog.base.BaseMvpPresenter;
import th.co.thiensurat.ecatalog.catalog.item.ConvertProductItem;
import th.co.thiensurat.ecatalog.catalog.item.ProductItem;
import th.co.thiensurat.ecatalog.catalog.item.ProductItemGroup;

/**
 * Created by teerayut.k on 10/16/2017.
 */

public class MainPresenter extends BaseMvpPresenter<MainInterface.View> implements MainInterface.Presenter {

    private ServiceManager serviceManager;
    private static ProductItemGroup productItemGroup;
    private static List<ProductItem> productItemList = new ArrayList<ProductItem>();

    public static MainInterface.Presenter create() {
        return new MainPresenter();
    }

    public MainPresenter() {
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

    /*@Override
    public void requestProduct() {
        getView().onLoad();
        serviceManager.getProduct("product", new ServiceManager.ServiceManagerCallback<ProductItemResultGroup>() {
            @Override
            public void onSuccess(ProductItemResultGroup result) {
                if (result.getMessage().equals("SUCCESS")) {
                    getView().onDismiss();
                    ProductItemGroup productGroup = ConvertProductItem.createProductGroupFromResult(result);
                    productItemGroup = productGroup;
                    onSetProductGroup(productItemGroup);
                    productItemList = ConvertProductItem.createListProductFromResult(result.getData());
                    getView().setProductToAdapter(productItemList);
                } else if (result.getMessage().equals("FAIL")) {
                    getView().onDismiss();
                    getView().onFail(result.getData().get(0).getProductCode());
                } else if (result.getMessage().equals("ERROR")) {
                    getView().onDismiss();
                    getView().onFail(result.getData().get(0).getProductCode());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Load Product", t.getMessage());
                productItemList.clear();
                getView().onDismiss();
            }
        });
    }

    @Override
    public void onSetProductGroup(ProductItemGroup ItemGroup) {
        this.productItemGroup = ItemGroup;
    }

    @Override
    public ProductItemGroup onGetProductGroup() {
        return productItemGroup;
    }

    @Override
    public void onRestoreProductToAdapter(ProductItemGroup ItemGroup) {
        this.productItemList = ItemGroup.getData();
        getView().setProductToAdapter(productItemList);
    }*/
}
