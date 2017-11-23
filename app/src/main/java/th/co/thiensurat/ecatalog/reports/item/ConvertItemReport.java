package th.co.thiensurat.ecatalog.reports.item;

import java.util.ArrayList;
import java.util.List;

import th.co.thiensurat.ecatalog.api.result.AllItemResult;
import th.co.thiensurat.ecatalog.api.result.AllItemResultGroup;

/**
 * Created by teerayut.k on 11/8/2017.
 */

public class ConvertItemReport {

    public static AllItemGroup createAllItemGroupFromResult( AllItemResultGroup result ){
        AllItemGroup group = new AllItemGroup();
        group.setStatus( result.getStatus() );
        group.setMessage( result.getMessage() );
        group.setData( ConvertItemReport.createListAllItemFromResult( result.getData() ));
        return group;
    }


    public static List<AllItem> createListAllItemFromResult(List<AllItemResult> result){
        List<AllItem> items = new ArrayList<>();
        for( AllItemResult listItemResult : result ){
            AllItem item = new AllItem()
                    .setOrderid(listItemResult.getOrderid())
                    .setProductname(listItemResult.getProductname())
                    .setTitle(listItemResult.getTitle())
                    .setFirstname(listItemResult.getFirstname())
                    .setLastname(listItemResult.getLastname())
                    .setProvince(listItemResult.getProvince())
                    .setOrderstatus(listItemResult.getOrderstatus())
                    .setStatusdetail(listItemResult.getStatusdetail())
                    .setCheckdate(listItemResult.getCheckdate())
                    .setCanceldate(listItemResult.getCanceldate())
                    .setClosedate(listItemResult.getClosedate());
            items.add( item );
        }
        return items;
    }
}
