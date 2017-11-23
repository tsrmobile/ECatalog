package th.co.thiensurat.ecatalog.api.result;

import java.util.List;

import th.co.thiensurat.ecatalog.share.item.BannerItem;

/**
 * Created by teerayut.k on 10/20/2017.
 */

public class BannerItemResultGroup {

    private String status;
    private String message;
    private List<BannerItemResult> data;

    public BannerItemResultGroup() {

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

    public List<BannerItemResult> getData() {
        return data;
    }

    public void setData(List<BannerItemResult> data) {
        this.data = data;
    }
}
