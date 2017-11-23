package th.co.thiensurat.ecatalog.share.item;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by teerayut.k on 10/20/2017.
 */

public class BannerItemGroup implements Parcelable {

    private String status;
    private String message;
    private List<BannerItem> data;

    public BannerItemGroup() {

    }

    protected BannerItemGroup(Parcel in) {
        status = in.readString();
        message = in.readString();
        data = in.createTypedArrayList(BannerItem.CREATOR);
    }

    public static final Creator<BannerItemGroup> CREATOR = new Creator<BannerItemGroup>() {
        @Override
        public BannerItemGroup createFromParcel(Parcel in) {
            return new BannerItemGroup(in);
        }

        @Override
        public BannerItemGroup[] newArray(int size) {
            return new BannerItemGroup[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<BannerItem> getData() {
        return data;
    }

    public void setData(List<BannerItem> data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(status);
        parcel.writeString(message);
        parcel.writeTypedList(data);
    }
}
