package th.co.thiensurat.ecatalog.reports.item;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

import th.co.thiensurat.ecatalog.base.adapter.BaseItem;

/**
 * Created by teerayut.k on 11/8/2017.
 */

public class AllItem extends BaseItem implements Parcelable {

    private String orderid;
    private String productname;
    private String title;
    private String firstname;
    private String lastname;
    private String province;
    private String orderstatus;
    private String statusdetail;
    private String checkdate;
    private String canceldate;
    private String closedate;

    public AllItem() {

    }

    public String getOrderid() {
        return orderid;
    }

    public AllItem setOrderid(String orderid) {
        this.orderid = orderid;
        return this;
    }

    public String getProductname() {
        return productname;
    }

    public AllItem setProductname(String productname) {
        this.productname = productname;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public AllItem setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public AllItem setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public AllItem setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getProvince() {
        return province;
    }

    public AllItem setProvince(String province) {
        this.province = province;
        return this;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public AllItem setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
        return this;
    }

    public String getStatusdetail() {
        return statusdetail;
    }

    public AllItem setStatusdetail(String statusdetail) {
        this.statusdetail = statusdetail;
        return this;
    }

    public String getCheckdate() {
        return checkdate;
    }

    public AllItem setCheckdate(String checkdate) {
        this.checkdate = checkdate;
        return this;
    }

    public String getCanceldate() {
        return canceldate;
    }

    public AllItem setCanceldate(String canceldate) {
        this.canceldate = canceldate;
        return this;
    }

    public String getClosedate() {
        return closedate;
    }

    public AllItem setClosedate(String closedate) {
        this.closedate = closedate;
        return this;
    }

    protected AllItem(Parcel in) {
        super(in);
        orderid         = in.readString();
        productname     = in.readString();
        title           = in.readString();
        firstname       = in.readString();
        lastname        = in.readString();
        province        = in.readString();
        orderstatus     = in.readString();
        statusdetail    = in.readString();
        checkdate       = in.readString();
        canceldate      = in.readString();
        closedate       = in.readString();

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(orderid);
        dest.writeString(productname);
        dest.writeString(title);
        dest.writeString(firstname);
        dest.writeString(lastname);
        dest.writeString(province);
        dest.writeString(orderstatus);
        dest.writeString(statusdetail);
        dest.writeString(checkdate);
        dest.writeString(canceldate);
        dest.writeString(closedate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public BaseItem clone() throws CloneNotSupportedException {
        AllItem item = new AllItem()
                .setOrderid(orderid)
                .setProductname(productname)
                .setTitle(title)
                .setFirstname(firstname)
                .setLastname(lastname)
                .setProvince(province)
                .setOrderstatus(orderstatus)
                .setStatusdetail(statusdetail)
                .setCheckdate(checkdate)
                .setCanceldate(canceldate)
                .setClosedate(closedate);
        return item;
    }

    public static final Creator<AllItem> CREATOR = new Creator<AllItem>() {
        @Override
        public AllItem createFromParcel(Parcel in) {
            return new AllItem(in);
        }

        @Override
        public AllItem[] newArray(int size) {
            return new AllItem[size];
        }
    };
}
