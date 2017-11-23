package th.co.thiensurat.ecatalog.api.request;

import java.util.List;

/**
 * Created by teerayut.k on 11/5/2017.
 */

public class RequestRegistration {

    private List<generalBody> body;

    public List<generalBody> getBody() {
        return body;
    }

    public RequestRegistration setBody(List<generalBody> body) {
        this.body = body;
        return this;
    }

    public static class generalBody {
        private String idcard;
        private String type;
        private String titleid;
        private String firstname;
        private String lastname;
        private String birthdate;
        private String address;
        private String provinceid;
        private String districtid;
        private String subdistrictid;
        private String zipcode;
        private String phone;
        private String mobile;
        private String email;
        private String lineid;
        private String facebook;
        private String promtpayaccount;
        private String contactname;
        private String contactphone;
        private String country;
        private String empid;

        public String getIdcard() {
            return idcard;
        }

        public generalBody setIdcard(String idcard) {
            this.idcard = idcard;
            return this;
        }

        public String getType() {
            return type;
        }

        public generalBody setType(String type) {
            this.type = type;
            return this;
        }

        public String getTitleid() {
            return titleid;
        }

        public generalBody setTitleid(String titleid) {
            this.titleid = titleid;
            return this;
        }

        public String getFirstname() {
            return firstname;
        }

        public generalBody setFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public String getLastname() {
            return lastname;
        }

        public generalBody setLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public String getBirthdate() {
            return birthdate;
        }

        public generalBody setBirthdate(String birthdate) {
            this.birthdate = birthdate;
            return this;
        }

        public String getAddress() {
            return address;
        }

        public generalBody setAddress(String address) {
            this.address = address;
            return this;
        }

        public String getProvinceid() {
            return provinceid;
        }

        public generalBody setProvinceid(String provinceid) {
            this.provinceid = provinceid;
            return this;
        }

        public String getDistrictid() {
            return districtid;
        }

        public generalBody setDistrictid(String districtid) {
            this.districtid = districtid;
            return this;
        }

        public String getSubdistrictid() {
            return subdistrictid;
        }

        public generalBody setSubdistrictid(String subdistrictid) {
            this.subdistrictid = subdistrictid;
            return this;
        }

        public String getZipcode() {
            return zipcode;
        }

        public generalBody setZipcode(String zipcode) {
            this.zipcode = zipcode;
            return this;
        }

        public String getPhone() {
            return phone;
        }

        public generalBody setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public String getMobile() {
            return mobile;
        }

        public generalBody setMobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        public String getEmail() {
            return email;
        }

        public generalBody setEmail(String email) {
            this.email = email;
            return this;
        }

        public String getLineid() {
            return lineid;
        }

        public generalBody setLineid(String lineid) {
            this.lineid = lineid;
            return this;
        }

        public String getFacebook() {
            return facebook;
        }

        public generalBody setFacebook(String facebook) {
            this.facebook = facebook;
            return this;
        }

        public String getPromtpayaccount() {
            return promtpayaccount;
        }

        public generalBody setPromtpayaccount(String promtpayaccount) {
            this.promtpayaccount = promtpayaccount;
            return this;
        }

        public String getContactname() {
            return contactname;
        }

        public generalBody setContactname(String contactname) {
            this.contactname = contactname;
            return this;
        }

        public String getContactphone() {
            return contactphone;
        }

        public generalBody setContactphone(String contactphone) {
            this.contactphone = contactphone;
            return this;
        }

        public String getCountry() {
            return country;
        }

        public generalBody setCountry(String country) {
            this.country = country;
            return this;
        }

        public String getEmpid() {
            return empid;
        }

        public generalBody setEmpid(String empid) {
            this.empid = empid;
            return this;
        }
    }
}
