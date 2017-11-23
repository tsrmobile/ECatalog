package th.co.thiensurat.ecatalog.catalog;

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

public class CatalogPresenter extends BaseMvpPresenter<CatalogInterface.View> implements CatalogInterface.Presenter {

    private ServiceManager serviceManager;
    private ProductItemGroup productItemGroup;
    private List<ProductItem> productItemList = new ArrayList<ProductItem>();

    public static CatalogInterface.Presenter create() {
        return new CatalogPresenter();
    }

    public CatalogPresenter() {
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
    public void requestProduct() {
        getView().onLoad();
        serviceManager.getProduct("product", new ServiceManager.ServiceManagerCallback<ProductItemResultGroup>() {
            @Override
            public void onSuccess(ProductItemResultGroup result) {
                if (result.getStatus().equals("SUCCESS")) {
                    getView().onDismiss();
                    ProductItemGroup productGroup = ConvertProductItem.createProductGroupFromResult(result);
                    productItemGroup = productGroup;
                    onSetProductGroup(productItemGroup);
                    productItemList = ConvertProductItem.createListProductFromResult(result.getData());
                    getView().setProductToAdapter(productItemList);
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
    }
}
