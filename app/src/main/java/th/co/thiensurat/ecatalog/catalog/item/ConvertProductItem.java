package th.co.thiensurat.ecatalog.catalog.item;

import java.util.ArrayList;
import java.util.List;

import th.co.thiensurat.ecatalog.api.result.ProductItemResult;
import th.co.thiensurat.ecatalog.api.result.ProductItemResultGroup;

/**
 * Created by teerayut.k on 10/16/2017.
 */

public class ConvertProductItem {

    public static ProductItemGroup createProductGroupFromResult( ProductItemResultGroup result ){
        ProductItemGroup group = new ProductItemGroup();
        group.setStatus( result.getStatus() );
        group.setMessage( result.getMessage() );
        group.setData( ConvertProductItem.createListProductFromResult( result.getData() ));
        return group;
    }


    public static List<ProductItem> createListProductFromResult(List<ProductItemResult> result){
        List<ProductItem> items = new ArrayList<>();
        for( ProductItemResult listItemResult : result ){
            ProductItem productItem = new ProductItem()
                    .setProductCode(listItemResult.getProductCode())
                    .setProductName(listItemResult.getProductName())
                    .setProductDetail(listItemResult.getProductDetail())
                    .setProductPrice(listItemResult.getProductPrice())
                    .setProductDiscount(listItemResult.getProductDiscount())
                    .setProductDiscountPercent(listItemResult.getProductDiscountPercent())
                    .setProductSellPrice(listItemResult.getProductSellPrice())
                    .setPromotionCode(listItemResult.getPromotionCode())
                    .setPromotionName(listItemResult.getPromotionName())
                    .setPromotionImage(listItemResult.getPromotionImage())
                    .setPromotionDiscount(listItemResult.getPromotionDiscount())
                    .setPromotionDiscountPercent(listItemResult.getPromotionDiscountPercent())
                    .setPromotionStartDate(listItemResult.getPromotionStartDate())
                    .setPromotionEndDate(listItemResult.getPromotionEndDate())
                    .setProductImage(listItemResult.getProductImage());
            items.add( productItem );
        }
        return items;
    }
}
