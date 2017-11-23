package th.co.thiensurat.ecatalog.share.item;

import android.os.Parcel;
import android.os.Parcelable;

import th.co.thiensurat.ecatalog.base.adapter.BaseItem;

/**
 * Created by teerayut.k on 10/20/2017.
 */

public class BannerItem extends BaseItem implements Parcelable {

    private String bannerID;
    private String bannerHeader;
    private String bannerDetail;
    private String bannerImage;

    public BannerItem() {

    }

    public String getBannerID() {
        return bannerID;
    }

    public BannerItem setBannerID(String bannerID) {
        this.bannerID = bannerID;
        return this;
    }

    public String getBannerHeader() {
        return bannerHeader;
    }

    public BannerItem setBannerHeader(String bannerHeader) {
        this.bannerHeader = bannerHeader;
        return this;
    }

    public String getBannerDetail() {
        return bannerDetail;
    }

    public BannerItem setBannerDetail(String bannerDetail) {
        this.bannerDetail = bannerDetail;
        return this;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public BannerItem setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
        return this;
    }

    protected BannerItem(Parcel in) {
        super(in);
        bannerID = in.readString();
        bannerHeader = in.readString();
        bannerDetail = in.readString();
        bannerImage = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(bannerID);
        dest.writeString(bannerHeader);
        dest.writeString(bannerDetail);
        dest.writeString(bannerImage);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public BaseItem clone() throws CloneNotSupportedException {
        BannerItem bannerItem = new BannerItem()
                .setBannerID(bannerID)
                .setBannerHeader(bannerHeader)
                .setBannerDetail(bannerDetail)
                .setBannerImage(bannerImage);
        return bannerItem;
    }

    public static final Creator<BannerItem> CREATOR = new Creator<BannerItem>() {
        @Override
        public BannerItem createFromParcel(Parcel in) {
            return new BannerItem(in);
        }

        @Override
        public BannerItem[] newArray(int size) {
            return new BannerItem[size];
        }
    };
}
