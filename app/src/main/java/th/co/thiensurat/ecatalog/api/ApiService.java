package th.co.thiensurat.ecatalog.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import th.co.thiensurat.ecatalog.api.request.RequestAuth;
import th.co.thiensurat.ecatalog.api.request.RequestRegistration;
import th.co.thiensurat.ecatalog.api.request.RequestOrder;
import th.co.thiensurat.ecatalog.api.result.AllItemResultGroup;
import th.co.thiensurat.ecatalog.api.result.AuthItemResultGroup;
import th.co.thiensurat.ecatalog.api.result.BannerItemResultGroup;
import th.co.thiensurat.ecatalog.api.result.DataItemResultGroup;
import th.co.thiensurat.ecatalog.api.result.Result;
import th.co.thiensurat.ecatalog.api.result.ProductItemResultGroup;
import th.co.thiensurat.ecatalog.api.result.TitleItemResultGroup;

import static th.co.thiensurat.ecatalog.api.ApiURL.ADD_ORDER_URL;
import static th.co.thiensurat.ecatalog.api.ApiURL.ALL_REPORT_URL;
import static th.co.thiensurat.ecatalog.api.ApiURL.AUTH_URL;
import static th.co.thiensurat.ecatalog.api.ApiURL.BANNER_URL;
import static th.co.thiensurat.ecatalog.api.ApiURL.COUNTRY_URL;
import static th.co.thiensurat.ecatalog.api.ApiURL.DATA_URL;
import static th.co.thiensurat.ecatalog.api.ApiURL.PRODUCT_URL;
import static th.co.thiensurat.ecatalog.api.ApiURL.SIGNUP_URL;

/**
 * Created by teerayut.k on 7/17/2017.
 */

public interface ApiService {

    @GET ( PRODUCT_URL )
    Call<ProductItemResultGroup> getProduct(@Query("data") String data);

    @GET( DATA_URL )
    Call<DataItemResultGroup> getDataItem(@Query("data") String data, @Query("code") String code);

    @GET( DATA_URL )
    Call<TitleItemResultGroup> getTitleName(@Query("data") String data);

    @POST( AUTH_URL )
    Call<AuthItemResultGroup> getAuthen(@Body RequestAuth auth);

    @POST( ADD_ORDER_URL )
    Call<Result> addOrder(@Body RequestOrder order);

    @GET( BANNER_URL )
    Call<BannerItemResultGroup> getBannerSocial(@Query("data") String data);

    @GET( DATA_URL )
    Call<DataItemResultGroup> getType(@Query("data") String data);

    @POST( SIGNUP_URL )
    Call<Result> addAgent(@Body RequestRegistration data);

    @GET( COUNTRY_URL )
    Call<DataItemResultGroup> getCountry(@Query("data") String data);

    @GET( ALL_REPORT_URL )
    Call<AllItemResultGroup> getAllReport(@Query("data") String data, @Query("id") String id);
}
