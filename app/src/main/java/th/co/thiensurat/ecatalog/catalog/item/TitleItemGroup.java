package th.co.thiensurat.ecatalog.catalog.item;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by teerayut.k on 10/17/2017.
 */

public class TitleItemGroup implements Parcelable {

    private String status;
    private String message;
    private List<TitleItem> data;

    public TitleItemGroup() {

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

    public List<TitleItem> getData() {
        return data;
    }

    public void setData(List<TitleItem> data) {
        this.data = data;
    }

    protected TitleItemGroup(Parcel in) {
        status = in.readString();
        message = in.readString();
        data = in.createTypedArrayList(TitleItem.CREATOR);
    }

    public static final Creator<TitleItemGroup> CREATOR = new Creator<TitleItemGroup>() {
        @Override
        public TitleItemGroup createFromParcel(Parcel in) {
            return new TitleItemGroup(in);
        }

        @Override
        public TitleItemGroup[] newArray(int size) {
            return new TitleItemGroup[size];
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
