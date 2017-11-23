package th.co.thiensurat.ecatalog.api.result;

import java.util.List;

/**
 * Created by teerayut.k on 11/8/2017.
 */

public class AllItemResultGroup {

    private String status;
    private String message;
    private List<AllItemResult> data;

    public AllItemResultGroup() {

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

    public List<AllItemResult> getData() {
        return data;
    }

    public void setData(List<AllItemResult> data) {
        this.data = data;
    }
}
