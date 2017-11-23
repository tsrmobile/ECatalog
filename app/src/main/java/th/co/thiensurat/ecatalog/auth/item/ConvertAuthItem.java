package th.co.thiensurat.ecatalog.auth.item;

import java.util.ArrayList;
import java.util.List;

import th.co.thiensurat.ecatalog.api.result.AuthItemResult;
import th.co.thiensurat.ecatalog.api.result.AuthItemResultGroup;

/**
 * Created by teerayut.k on 10/18/2017.
 */

public class ConvertAuthItem {

    public static AuthenItemGroup createAuthItemGroupFromResult(AuthItemResultGroup result ){
        AuthenItemGroup group = new AuthenItemGroup();
        group.setStatus( result.getStatus() );
        group.setMessage( result.getMessage() );
        group.setData( ConvertAuthItem.createListDataItemsFromResult( result.getData() ) );
        return group;
    }

    public static List<AuthenItem> createListDataItemsFromResult(List<AuthItemResult> result){
        List<AuthenItem> items = new ArrayList<>();
        for( AuthItemResult dataItemResult : result ){
            AuthenItem item = new AuthenItem()
                    .setAgentid(dataItemResult.getAgentid())
                    .setPassword(dataItemResult.getPassword())
                    .setRefno(dataItemResult.getRefno())
                    .setRefempid(dataItemResult.getRefempid())
                    .setIdcard(dataItemResult.getIdcard())
                    .setTitle(dataItemResult.getTitle())
                    .setFirstname(dataItemResult.getFirstname())
                    .setLastname(dataItemResult.getLastname())
                    .setAddress(dataItemResult.getAddress())
                    .setProvince(dataItemResult.getProvince())
                    .setDistrict(dataItemResult.getDistrict())
                    .setSubdistrict(dataItemResult.getSubdistrict())
                    .setZipcode(dataItemResult.getZipcode())
                    .setEmail(dataItemResult.getEmail())
                    .setPhone(dataItemResult.getPhone())
                    .setMobile(dataItemResult.getMobile())
                    .setLine(dataItemResult.getLine())
                    .setFacebook(dataItemResult.getFacebook());
            items.add( item );
        }
        return items;
    }

    public static AuthenItem createAuthItemFromGroup(AuthenItemGroup group) {
        AuthenItem authenItem = null;
        for (AuthenItem item : group.getData() ) {
            authenItem = new AuthenItem()
                    .setAgentid(item.getAgentid())
                    .setPassword(item.getPassword())
                    .setRefno(item.getRefno())
                    .setRefempid(item.getRefempid())
                    .setIdcard(item.getIdcard())
                    .setTitle(item.getTitle())
                    .setFirstname(item.getFirstname())
                    .setLastname(item.getLastname())
                    .setAddress(item.getAddress())
                    .setProvince(item.getProvince())
                    .setDistrict(item.getDistrict())
                    .setSubdistrict(item.getSubdistrict())
                    .setZipcode(item.getZipcode())
                    .setEmail(item.getEmail())
                    .setPhone(item.getPhone())
                    .setMobile(item.getMobile())
                    .setLine(item.getLine())
                    .setFacebook(item.getFacebook());
        }
        return authenItem;
    }
}
