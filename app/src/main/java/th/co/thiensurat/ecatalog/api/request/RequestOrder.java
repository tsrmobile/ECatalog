package th.co.thiensurat.ecatalog.api.request;

import java.util.List;

/**
 * Created by teerayut.k on 10/19/2017.
 */

public class RequestOrder {

    private List<orderBody> body;

    public List<orderBody> getBody() {
        return body;
    }

    public RequestOrder setBody(List<orderBody> body) {
        this.body = body;
        return this;
    }

    public static class orderBody {

        private String titleId;
        private String firstname;
        private String lastname;
        private String phone;
        private String provinceId;
        private String agentid;
        private String promotioncode;
        private String productcode;
        private String price;
        private String discountprice;
        private String customertype;
        private String qty;

        public String getTitleId() {
            return titleId;
        }

        public orderBody setTitleId(String titleId) {
            this.titleId = titleId;
            return this;
        }

        public String getFirstname() {
            return firstname;
        }

        public orderBody setFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public String getLastname() {
            return lastname;
        }

        public orderBody setLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public String getPhone() {
            return phone;
        }

        public orderBody setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public String getProvinceId() {
            return provinceId;
        }

        public orderBody setProvinceId(String provinceId) {
            this.provinceId = provinceId;
            return this;
        }

        public String getAgentid() {
            return agentid;
        }

        public orderBody setAgentid(String agentid) {
            this.agentid = agentid;
            return this;
        }

        public String getPromotioncode() {
            return promotioncode;
        }

        public orderBody setPromotioncode(String promotioncode) {
            this.promotioncode = promotioncode;
            return this;
        }

        public String getProductcode() {
            return productcode;
        }

        public orderBody setProductcode(String productcode) {
            this.productcode = productcode;
            return this;
        }

        public String getPrice() {
            return price;
        }

        public orderBody setPrice(String price) {
            this.price = price;
            return this;
        }

        public String getDiscountprice() {
            return discountprice;
        }

        public orderBody setDiscountprice(String discountprice) {
            this.discountprice = discountprice;
            return this;
        }

        public String getCustomertype() {
            return customertype;
        }

        public orderBody setCustomertype(String customertype) {
            this.customertype = customertype;
            return this;
        }

        public String getQty() {
            return qty;
        }

        public orderBody setQty(String qty) {
            this.qty = qty;
            return this;
        }
    }
}
