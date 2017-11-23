package th.co.thiensurat.ecatalog.catalog.item;

import java.util.ArrayList;
import java.util.List;

import th.co.thiensurat.ecatalog.api.result.DataItemResult;
import th.co.thiensurat.ecatalog.api.result.DataItemResultGroup;

/**
 * Created by teerayut.k on 10/17/2017.
 */

public class ConvertDataItem {

    public static DataItemGroup createDataItemGroupFromResult(DataItemResultGroup result ){
        DataItemGroup group = new DataItemGroup();
        group.setStatus( result.getStatus() );
        group.setMessage( result.getMessage() );
        group.setData( ConvertDataItem.createListDataItemsFromResult( result.getData() ) );
        return group;
    }

    public static List<DataItem> createListDataItemsFromResult(List<DataItemResult> result){
        List<DataItem> items = new ArrayList<>();
        DataItem itemHeader = new DataItem()
                .setDataId("")
                .setDataCode("header")
                .setDataName("");
        items.add( itemHeader );
        for( DataItemResult dataItemResult : result ){
            DataItem item = new DataItem()
                    .setDataId(dataItemResult.getDataId())
                    .setDataCode(dataItemResult.getDataCode())
                    .setDataName(dataItemResult.getDataName());
            items.add( item );
        }
        return items;
    }
}
