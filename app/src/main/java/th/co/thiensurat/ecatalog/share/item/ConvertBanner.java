package th.co.thiensurat.ecatalog.share.item;

import java.util.ArrayList;
import java.util.List;

import th.co.thiensurat.ecatalog.api.result.BannerItemResult;
import th.co.thiensurat.ecatalog.api.result.BannerItemResultGroup;

/**
 * Created by teerayut.k on 10/20/2017.
 */

public class ConvertBanner {

    public static BannerItemGroup createBannerGroupFromResult( BannerItemResultGroup result ){
        BannerItemGroup group = new BannerItemGroup();
        group.setStatus( result.getStatus() );
        group.setMessage( result.getMessage() );
        group.setData( ConvertBanner.createListBannerFromResult( result.getData() ));
        return group;
    }


    public static List<BannerItem> createListBannerFromResult(List<BannerItemResult> result){
        List<BannerItem> items = new ArrayList<>();
        for( BannerItemResult listItemResult : result ){
            BannerItem item = new BannerItem()
                    .setBannerID(listItemResult.getBannerID())
                    .setBannerHeader(listItemResult.getBannerHeader())
                    .setBannerDetail(listItemResult.getBannerDetail())
                    .setBannerImage(listItemResult.getBannerImage());
            items.add( item );
        }
        return items;
    }
}
