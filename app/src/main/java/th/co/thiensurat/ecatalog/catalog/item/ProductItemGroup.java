package th.co.thiensurat.ecatalog.catalog.item;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by teerayut.k on 10/16/2017.
 */

public class ProductItemGroup implements Parcelable {

    private String status;
    private String message;
    private List<ProductItem> data;

    public ProductItemGroup() {

    }

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

    public List<ProductItem> getData() {
        return data;
    }

    public void setData(List<ProductItem> data) {
        this.data = data;
    }

    protected ProductItemGroup(Parcel in) {
        status = in.readString();
        message = in.readString();
        data = in.createTypedArrayList(ProductItem.CREATOR);
    }

    public static final Creator<ProductItemGroup> CREATOR = new Creator<ProductItemGroup>() {
        @Override
        public ProductItemGroup createFromParcel(Parcel in) {
            return new ProductItemGroup(in);
        }

        @Override
        public ProductItemGroup[] newArray(int size) {
            return new ProductItemGroup[size];
        }
    };

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
