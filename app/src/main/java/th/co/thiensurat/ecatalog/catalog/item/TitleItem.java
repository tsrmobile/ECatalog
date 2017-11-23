package th.co.thiensurat.ecatalog.catalog.item;

import android.os.Parcel;
import android.os.Parcelable;

import th.co.thiensurat.ecatalog.base.adapter.BaseItem;

/**
 * Created by teerayut.k on 10/17/2017.
 */

public class TitleItem extends BaseItem implements Parcelable {

    private String dataId;
    private String dataCode;
    private String dataName;

    public TitleItem() {

    }

    public String getDataId() {
        return dataId;
    }

    public TitleItem setDataId(String dataId) {
        this.dataId = dataId;
        return this;
    }

    public String getDataCode() {
        return dataCode;
    }

    public TitleItem setDataCode(String dataCode) {
        this.dataCode = dataCode;
        return this;
    }

    public String getDataName() {
        return dataName;
    }

    public TitleItem setDataName(String dataName) {
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

    protected TitleItem(Parcel in) {
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

    public static final Creator<TitleItem> CREATOR = new Creator<TitleItem>() {
        @Override
        public TitleItem createFromParcel(Parcel in) {
            return new TitleItem(in);
        }

        @Override
        public TitleItem[] newArray(int size) {
            return new TitleItem[size];
        }
    };
}
