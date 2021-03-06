package th.co.thiensurat.ecatalog.catalog.item;

import android.os.Parcel;
import android.os.Parcelable;

import th.co.thiensurat.ecatalog.base.adapter.BaseItem;


/**
 * Created by teerayut.k on 8/17/2017.
 */

public class DataItem extends BaseItem implements Parcelable {

    private String dataId;
    private String dataCode;
    private String dataName;

    public DataItem() {

    }

    public String getDataId() {
        return dataId;
    }

    public DataItem setDataId(String dataId) {
        this.dataId = dataId;
        return this;
    }

    public String getDataCode() {
        return dataCode;
    }

    public DataItem setDataCode(String dataCode) {
        this.dataCode = dataCode;
        return this;
    }

    public String getDataName() {
        return dataName;
    }

    public DataItem setDataName(String dataName) {
        this.dataName = dataName;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(dataId);
        dest.writeString(dataCode);
        dest.writeString(dataName);
    }

    protected DataItem(Parcel in) {
        super(in);
        dataId = in.readString();
        dataCode = in.readString();
        dataName = in.readString();
    }

    @Override
    public BaseItem clone() throws CloneNotSupportedException {
        DataItem item = new DataItem()
                .setDataId(dataId)
                .setDataCode(dataCode)
                .setDataName(dataName);
        return item;
    }

    public static final Creator<DataItem> CREATOR = new Creator<DataItem>() {
        @Override
        public DataItem createFromParcel(Parcel in) {
            return new DataItem(in);
        }

        @Override
        public DataItem[] newArray(int size) {
            return new DataItem[size];
        }
    };
}
