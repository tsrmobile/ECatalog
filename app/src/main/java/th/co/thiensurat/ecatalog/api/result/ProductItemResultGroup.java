package th.co.thiensurat.ecatalog.api.result;

import java.util.List;

import th.co.thiensurat.ecatalog.catalog.item.ProductItem;

/**
 * Created by teerayut.k on 10/16/2017.
 */

public class ProductItemResultGroup {

    private String status;
    private String message;
    private List<ProductItemResult> data;

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

    public List<ProductItemResult> getData() {
        return data;
    }

    public void setData(List<ProductItemResult> data) {
        this.data = data;
    }
}
