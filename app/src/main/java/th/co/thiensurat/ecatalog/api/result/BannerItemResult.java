package th.co.thiensurat.ecatalog.api.result;

/**
 * Created by teerayut.k on 10/20/2017.
 */

public class BannerItemResult {

    private String bannerID;
    private String bannerHeader;
    private String bannerDetail;
    private String bannerImage;

    public BannerItemResult() {

    }

    public String getBannerID() {
        return bannerID;
    }

    public void setBannerID(String bannerID) {
        this.bannerID = bannerID;
    }

    public String getBannerHeader() {
        return bannerHeader;
    }

    public void setBannerHeader(String bannerHeader) {
        this.bannerHeader = bannerHeader;
    }

    public String getBannerDetail() {
        return bannerDetail;
    }

    public void setBannerDetail(String bannerDetail) {
        this.bannerDetail = bannerDetail;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }
}
