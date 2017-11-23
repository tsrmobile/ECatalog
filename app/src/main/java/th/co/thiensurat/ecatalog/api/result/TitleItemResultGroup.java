package th.co.thiensurat.ecatalog.api.result;

import java.util.List;

/**
 * Created by teerayut.k on 10/17/2017.
 */

public class TitleItemResultGroup {

    private String status;
    private String message;
    private List<TitleItemResult> data;

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

    public List<TitleItemResult> getData() {
        return data;
    }

    public void setData(List<TitleItemResult> data) {
        this.data = data;
    }
}
