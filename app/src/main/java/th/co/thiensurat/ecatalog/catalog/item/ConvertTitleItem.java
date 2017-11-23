package th.co.thiensurat.ecatalog.catalog.item;

import java.util.ArrayList;
import java.util.List;

import th.co.thiensurat.ecatalog.api.result.DataItemResult;
import th.co.thiensurat.ecatalog.api.result.DataItemResultGroup;
import th.co.thiensurat.ecatalog.api.result.TitleItemResult;
import th.co.thiensurat.ecatalog.api.result.TitleItemResultGroup;

/**
 * Created by teerayut.k on 10/17/2017.
 */

public class ConvertTitleItem {

    public static TitleItemGroup createTitleItemGroupFromResult(TitleItemResultGroup result){
        TitleItemGroup group = new TitleItemGroup();
        group.setStatus( result.getStatus() );
        group.setMessage( result.getMessage() );
        group.setData( ConvertTitleItem.createListTitleItemsFromResult( result.getData() ) );
        return group;
    }

    public static List<TitleItem> createListTitleItemsFromResult(List<TitleItemResult> result){
        List<TitleItem> items = new ArrayList<>();
        TitleItem itemHeader = new TitleItem()
                .setDataId("")
                .setDataCode("header")
                .setDataName("");
        items.add( itemHeader );
        for( TitleItemResult dataItemResult : result ){
            TitleItem item = new TitleItem()
                    .setDataId(dataItemResult.getDataId())
                    .setDataCode(dataItemResult.getDataCode())
                    .setDataName(dataItemResult.getDataName());
            items.add( item );
        }
        return items;
    }
}
