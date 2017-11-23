package th.co.thiensurat.ecatalog.api.result;

/**
 * Created by teerayut.k on 10/17/2017.
 */

public class TitleItemResult {

    private String dataId;
    private String dataCode;
    private String dataName;

    public TitleItemResult() {

    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getDataCode() {
        return dataCode;
    }

    public void setDataCode(String dataCode) {
        this.dataCode = dataCode;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }
}
