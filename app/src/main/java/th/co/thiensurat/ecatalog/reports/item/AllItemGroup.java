package th.co.thiensurat.ecatalog.reports.item;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by teerayut.k on 11/8/2017.
 */

public class AllItemGroup implements Parcelable {

    private String status;
    private String message;
    private List<AllItem> data;

    public AllItemGroup() {

    }

    protected AllItemGroup(Parcel in) {
        status = in.readString();
        message = in.readString();
        data = in.createTypedArrayList(AllItem.CREATOR);
    }

    public static final Creator<AllItemGroup> CREATOR = new Creator<AllItemGroup>() {
        @Override
        public AllItemGroup createFromParcel(Parcel in) {
            return new AllItemGroup(in);
        }

        @Override
        public AllItemGroup[] newArray(int size) {
            return new AllItemGroup[size];
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

    public List<AllItem> getData() {
        return data;
    }

    public void setData(List<AllItem> data) {
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
