package th.co.thiensurat.ecatalog.catalog.item;

import android.os.Parcel;
import android.os.Parcelable;

import th.co.thiensurat.ecatalog.base.adapter.BaseItem;

/**
 * Created by teerayut.k on 10/16/2017.
 */

public class ProductItem extends BaseItem implements Parcelable {

    private String productCode;
    private String productName;
    private String productDetail;
    private String productPrice;
    private String productDiscount;
    private String productDiscountPercent;
    private String productSellPrice;
    private String promotionCode;
    private String promotionName;
    private String promotionImage;
    private String promotionDiscount;
    private String promotionDiscountPercent;
    private String promotionStartDate;
    private String promotionEndDate;
    private String productImage;

    public ProductItem() {

    }

    public String getProductCode() {
        return productCode;
    }

    public ProductItem setProductCode(String productCode) {
        this.productCode = productCode;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public ProductItem setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public ProductItem setProductDetail(String productDetail) {
        this.productDetail = productDetail;
        return this;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public ProductItem setProductPrice(String productPrice) {
        this.productPrice = productPrice;
        return this;
    }

    public String getProductDiscount() {
        return productDiscount;
    }

    public ProductItem setProductDiscount(String productDiscount) {
        this.productDiscount = productDiscount;
        return this;
    }

    public String getProductDiscountPercent() {
        return productDiscountPercent;
    }

    public ProductItem setProductDiscountPercent(String productDiscountPercent) {
        this.productDiscountPercent = productDiscountPercent;
        return this;
    }

    public String getProductSellPrice() {
        return productSellPrice;
    }

    public ProductItem setProductSellPrice(String productSellPrice) {
        this.productSellPrice = productSellPrice;
        return this;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public ProductItem setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
        return this;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public ProductItem setPromotionName(String promotionName) {
        this.promotionName = promotionName;
        return this;
    }

    public String getPromotionImage() {
        return promotionImage;
    }

    public ProductItem setPromotionImage(String promotionImage) {
        this.promotionImage = promotionImage;
        return this;
    }

    public String getPromotionDiscount() {
        return promotionDiscount;
    }

    public ProductItem setPromotionDiscount(String promotionDiscount) {
        this.promotionDiscount = promotionDiscount;
        return this;
    }

    public String getPromotionDiscountPercent() {
        return promotionDiscountPercent;
    }

    public ProductItem setPromotionDiscountPercent(String promotionDiscountPercent) {
        this.promotionDiscountPercent = promotionDiscountPercent;
        return this;
    }

    public String getPromotionStartDate() {
        return promotionStartDate;
    }

    public ProductItem setPromotionStartDate(String promotionStartDate) {
        this.promotionStartDate = promotionStartDate;
        return this;
    }

    public String getPromotionEndDate() {
        return promotionEndDate;
    }

    public ProductItem setPromotionEndDate(String promotionEndDate) {
        this.promotionEndDate = promotionEndDate;
        return this;
    }

    public String getProductImage() {
        return productImage;
    }

    public ProductItem setProductImage(String productImage) {
        this.productImage = productImage;
        return this;
    }

    protected ProductItem(Parcel in) {
        super(in);
        productCode = in.readString();
        productName = in.readString();
        productDetail = in.readString();
        productPrice = in.readString();
        productDiscount = in.readString();
        productDiscountPercent = in.readString();
        productSellPrice = in.readString();
        promotionCode = in.readString();
        promotionName = in.readString();
        promotionImage = in.readString();
        promotionDiscount = in.readString();
        promotionDiscountPercent = in.readString();
        promotionStartDate = in.readString();
        promotionEndDate = in.readString();
        productImage = in.readString();

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(productCode);
        dest.writeString(productName);
        dest.writeString(productDetail);
        dest.writeString(productPrice);
        dest.writeString(productDiscount);
        dest.writeString(productDiscountPercent);
        dest.writeString(productSellPrice);
        dest.writeString(promotionCode);
        dest.writeString(promotionName);
        dest.writeString(promotionImage);
        dest.writeString(promotionDiscount);
        dest.writeString(promotionDiscountPercent);
        dest.writeString(promotionStartDate);
        dest.writeString(promotionEndDate);
        dest.writeString(productImage);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public BaseItem clone() throws CloneNotSupportedException {
        ProductItem productItem = new ProductItem()
                .setProductCode(productCode)
                .setProductName(productName)
                .setProductDetail(productDetail)
                .setProductPrice(productPrice)
                .setProductDiscount(productDiscount)
                .setProductDiscountPercent(productDiscountPercent)
                .setProductSellPrice(productSellPrice)
                .setPromotionCode(promotionCode)
                .setPromotionName(promotionName)
                .setPromotionImage(promotionImage)
                .setPromotionDiscount(promotionDiscount)
                .setPromotionDiscountPercent(promotionDiscountPercent)
                .setPromotionStartDate(promotionStartDate)
                .setPromotionEndDate(promotionEndDate)
                .setProductImage(productImage);
        return productItem;
    }

    public static final Creator<ProductItem> CREATOR = new Creator<ProductItem>() {
        @Override
        public ProductItem createFromParcel(Parcel in) {
            return new ProductItem(in);
        }

        @Override
        public ProductItem[] newArray(int size) {
            return new ProductItem[size];
        }
    };
}
