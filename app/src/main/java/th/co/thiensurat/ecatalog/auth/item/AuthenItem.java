package th.co.thiensurat.ecatalog.auth.item;

import android.os.Parcel;
import android.os.Parcelable;

import th.co.thiensurat.ecatalog.base.adapter.BaseItem;

/**
 * Created by teerayut.k on 10/17/2017.
 */

public class AuthenItem extends BaseItem implements Parcelable {

    private String agentid;
    private String password;
    private String refno;
    private String refempid;
    private String idcard;
    private String title;
    private String firstname;
    private String lastname;
    private String address;
    private String province;
    private String district;
    private String subdistrict;
    private String zipcode;
    private String email;
    private String phone;
    private String mobile;
    private String line;
    private String facebook;

    public AuthenItem() {

    }

    public String getAgentid() {
        return agentid;
    }

    public AuthenItem setAgentid(String agentid) {
        this.agentid = agentid;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AuthenItem setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRefno() {
        return refno;
    }

    public AuthenItem setRefno(String refno) {
        this.refno = refno;
        return this;
    }

    public String getRefempid() {
        return refempid;
    }

    public AuthenItem setRefempid(String refempid) {
        this.refempid = refempid;
        return this;
    }

    public String getIdcard() {
        return idcard;
    }

    public AuthenItem setIdcard(String idcard) {
        this.idcard = idcard;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public AuthenItem setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public AuthenItem setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public AuthenItem setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public AuthenItem setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getProvince() {
        return province;
    }

    public AuthenItem setProvince(String province) {
        this.province = province;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public AuthenItem setDistrict(String district) {
        this.district = district;
        return this;
    }

    public String getSubdistrict() {
        return subdistrict;
    }

    public AuthenItem setSubdistrict(String subdistrict) {
        this.subdistrict = subdistrict;
        return this;
    }

    public String getZipcode() {
        return zipcode;
    }

    public AuthenItem setZipcode(String zipcode) {
        this.zipcode = zipcode;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AuthenItem setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public AuthenItem setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public AuthenItem setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getLine() {
        return line;
    }

    public AuthenItem setLine(String line) {
        this.line = line;
        return this;
    }

    public String getFacebook() {
        return facebook;
    }

    public AuthenItem setFacebook(String facebook) {
        this.facebook = facebook;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(agentid);
        dest.writeString(password);
        dest.writeString(refno);
        dest.writeString(refempid);
        dest.writeString(idcard);
        dest.writeString(title);
        dest.writeString(firstname);
        dest.writeString(lastname);
        dest.writeString(address);
        dest.writeString(province);
        dest.writeString(district);
        dest.writeString(subdistrict);
        dest.writeString(zipcode);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(mobile);
        dest.writeString(line);
        dest.writeString(facebook);
    }

    protected AuthenItem(Parcel in) {
        super(in);
        agentid     = in.readString();
        password    = in.readString();
        refno       = in.readString();
        refempid    = in.readString();
        idcard      = in.readString();
        title       = in.readString();
        firstname   = in.readString();
        lastname    = in.readString();
        address     = in.readString();
        province    = in.readString();
        district    = in.readString();
        subdistrict = in.readString();
        zipcode     = in.readString();
        email       = in.readString();
        phone       = in.readString();
        line        = in.readString();
        facebook    = in.readString();
    }

    @Override
    public BaseItem clone() throws CloneNotSupportedException {
        AuthenItem item = new AuthenItem()
                .setAgentid(agentid)
                .setPassword(password)
                .setRefno(refno)
                .setRefempid(refempid)
                .setIdcard(idcard)
                .setTitle(title)
                .setFirstname(firstname)
                .setLastname(lastname)
                .setAddress(address)
                .setProvince(province)
                .setDistrict(district)
                .setSubdistrict(subdistrict)
                .setZipcode(zipcode)
                .setEmail(email)
                .setPhone(phone)
                .setMobile(mobile)
                .setLine(line)
                .setFacebook(facebook);
        return item;
    }

    public static final Creator<AuthenItem> CREATOR = new Creator<AuthenItem>() {
        @Override
        public AuthenItem createFromParcel(Parcel in) {
            return new AuthenItem(in);
        }

        @Override
        public AuthenItem[] newArray(int size) {
            return new AuthenItem[size];
        }
    };
}
