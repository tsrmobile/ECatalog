package th.co.thiensurat.ecatalog.api;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import th.co.thiensurat.ecatalog.api.request.RequestAuth;
import th.co.thiensurat.ecatalog.api.request.RequestRegistration;
import th.co.thiensurat.ecatalog.api.request.RequestOrder;
import th.co.thiensurat.ecatalog.api.result.AllItemResultGroup;
import th.co.thiensurat.ecatalog.api.result.AuthItemResultGroup;
import th.co.thiensurat.ecatalog.api.result.BannerItemResultGroup;
import th.co.thiensurat.ecatalog.api.result.DataItemResultGroup;
import th.co.thiensurat.ecatalog.api.result.ItemAuth;
import th.co.thiensurat.ecatalog.api.result.Result;
import th.co.thiensurat.ecatalog.api.result.ProductItemResultGroup;
import th.co.thiensurat.ecatalog.api.result.TitleItemResultGroup;

import static th.co.thiensurat.ecatalog.api.ApiURL.BASE_URL;

/**
 * Created by teerayut.k on 7/17/2017.
 */

public class ServiceManager {

    private static ServiceManager instance;
    private static ApiService api;
    private Call<AuthItemResultGroup> requestAuthenCall;
    private Call<Result> insertOrderCall;
    private Call<Result> creatAgent;

    public interface ServiceManagerCallback<T>{
        void onSuccess(T result);

        void onFailure(Throwable t);
    }

    public static ServiceManager getInstance(){
        if( instance == null ){
            instance = new ServiceManager();
        }
        return instance;
    }

    private ServiceManager(){
    }

    public static void setApi( ApiService mockApi ){
        api = mockApi;
    }

    /*************************************************Load Product***************************************************/
    public Call<ProductItemResultGroup> product(String data) {
        return Service.newInstance( BASE_URL )
                .getApi( api )
                .getProduct(data);
    }

    public void getProduct(String data, final ServiceManagerCallback<ProductItemResultGroup> callback) {
        product(data).enqueue(new Callback<ProductItemResultGroup>() {
            @Override
            public void onResponse(Call<ProductItemResultGroup> call, Response<ProductItemResultGroup> response) {
                Log.e("request product", response + "");
                if( callback != null ){
                    callback.onSuccess( response.body() );
                }
            }

            @Override
            public void onFailure(Call<ProductItemResultGroup> call, Throwable t) {
                if( callback != null ){
                    callback.onFailure( t );
                }
            }
        });
    }
    /*************************************************End********************************************************/

    /*************************************************Load Province***************************************************/
    public Call<DataItemResultGroup> province(String data, String code) {
        return Service.newInstance( BASE_URL)
                .getApi( api )
                .getDataItem(data, code);
    }

    public void getDataItem(String data, String code, final ServiceManagerCallback<DataItemResultGroup> callback) {
        province(data, code).enqueue(new Callback<DataItemResultGroup>() {
            @Override
            public void onResponse(Call<DataItemResultGroup> call, Response<DataItemResultGroup> response) {
                Log.e("request province", response + "");
                if( callback != null ){
                    callback.onSuccess( response.body() );
                }
            }

            @Override
            public void onFailure(Call<DataItemResultGroup> call, Throwable t) {
                if( callback != null ){
                    callback.onFailure( t );
                }
            }
        });
    }
    /*************************************************End********************************************************/

    /*************************************************Load title name***************************************************/
    public Call<TitleItemResultGroup> title(String data) {
        return Service.newInstance( BASE_URL )
                .getApi( api )
                .getTitleName(data);
    }

    public void getTitle(String data, final ServiceManagerCallback<TitleItemResultGroup> callback) {
        title(data).enqueue(new Callback<TitleItemResultGroup>() {
            @Override
            public void onResponse(Call<TitleItemResultGroup> call, Response<TitleItemResultGroup> response) {
                Log.e("request title name", response + "");
                if( callback != null ){
                    callback.onSuccess( response.body() );
                }
            }

            @Override
            public void onFailure(Call<TitleItemResultGroup> call, Throwable t) {
                if( callback != null ){
                    callback.onFailure( t );
                }
            }
        });
    }
    /*************************************************End********************************************************/

    /*************************************************Authentication*************************************************/
    public Call<AuthItemResultGroup> auth(RequestAuth body) {
        return Service.newInstance( BASE_URL )
                .getApi( api )
                .getAuthen(body);
    }

    public void getAuth(List<ItemAuth> items, final ServiceManagerCallback<AuthItemResultGroup> callback) {
        List<RequestAuth.authenBody> bodyList = new ArrayList<>();
        for (ItemAuth item : items) {
            bodyList.add( new RequestAuth.authenBody()
                    .setUsername( item.getUsername() )
                    .setPassword( item.getPassword() )
            );
        }
        RequestAuth body = new RequestAuth();
        body.setBody(bodyList);

        requestAuthenCall = auth( body );
        requestAuthenCall.enqueue(new Callback<AuthItemResultGroup>() {
            @Override
            public void onResponse(Call<AuthItemResultGroup> call, Response<AuthItemResultGroup> response) {
                Log.e("request Authen", response + "");
                if( callback != null ){
                    callback.onSuccess( response.body() );
                }
            }

            @Override
            public void onFailure(Call<AuthItemResultGroup> call, Throwable t) {
                if( callback != null ){
                    callback.onFailure( t );
                }
            }
        });
    }
    /*************************************************End********************************************************/

    /*************************************************Add new order*************************************************/
    public Call<Result> order(RequestOrder order) {
        return Service.newInstance( BASE_URL )
                .getApi( api )
                .addOrder( order );
    }

    public void addOrder(List<RequestOrder.orderBody> orders, final ServiceManagerCallback<Result> callback) {
        RequestOrder body = new RequestOrder();
        body.setBody(orders);

        insertOrderCall = order(body);
        insertOrderCall.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Log.e("insert order", response + "");
                if( callback != null ){
                    callback.onSuccess( response.body() );
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.e("insert order error", t.getMessage() + "");
                if( callback != null ){
                    callback.onFailure( t );
                }
            }
        });
    }
    /*************************************************End********************************************************/

    /*************************************************Load Banner**************************************************/
    public Call<BannerItemResultGroup> banner(String data) {
        return Service.newInstance( BASE_URL )
                .getApi( api )
                .getBannerSocial( data );
    }

    public void getBanner(String data, final ServiceManagerCallback<BannerItemResultGroup> callback) {
        banner(data).enqueue(new Callback<BannerItemResultGroup>() {
            @Override
            public void onResponse(Call<BannerItemResultGroup> call, Response<BannerItemResultGroup> response) {
                Log.e("request banner", response + "");
                if( callback != null ){
                    callback.onSuccess( response.body() );
                }
            }

            @Override
            public void onFailure(Call<BannerItemResultGroup> call, Throwable t) {
                if( callback != null ){
                    callback.onFailure( t );
                }
            }
        });
    }
    /*************************************************End********************************************************/

    /*************************************************Load Customer type************************************************/
    public Call<DataItemResultGroup> type(String data) {
        return Service.newInstance( BASE_URL )
                .getApi( api )
                .getType( data );
    }

    public void getCustomerType(String data, final ServiceManagerCallback<DataItemResultGroup> callback) {
        type(data).enqueue(new Callback<DataItemResultGroup>() {
            @Override
            public void onResponse(Call<DataItemResultGroup> call, Response<DataItemResultGroup> response) {
                Log.e("request type", response + "");
                if( callback != null ){
                    callback.onSuccess( response.body() );
                }
            }

            @Override
            public void onFailure(Call<DataItemResultGroup> call, Throwable t) {
                if( callback != null ){
                    callback.onFailure( t );
                }
            }
        });
    }
    /*************************************************End********************************************************/

    /*************************************************Register********************************************************/
    public Call<Result> signUp(RequestRegistration body) {
        return Service.newInstance( BASE_URL )
                .getApi( api )
                .addAgent( body );
    }

    public void createAgent(List<RequestRegistration.generalBody> generalbody, final ServiceManagerCallback<Result> callback) {
        RequestRegistration body = new RequestRegistration();
        body.setBody(generalbody);

        creatAgent = signUp(body);
        creatAgent.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Log.e("create agent", response + "");
                if( callback != null ){
                    callback.onSuccess( response.body() );
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                if( callback != null ){
                    Log.e("Create agent failed", t.getMessage() + ", " + call.request());
                    callback.onFailure( t );
                }
            }
        });

    }
    /*************************************************End********************************************************/

    /*************************************************Load Country****************************************************/
    public Call<DataItemResultGroup> country(String data) {
        return Service.newInstance( BASE_URL )
                .getApi( api )
                .getCountry( data );
    }

    public void loadCountry(String data, final ServiceManagerCallback<DataItemResultGroup> callback) {
        country(data).enqueue(new Callback<DataItemResultGroup>() {
            @Override
            public void onResponse(Call<DataItemResultGroup> call, Response<DataItemResultGroup> response) {
                Log.e("request country", response + "");
                if( callback != null ){
                    callback.onSuccess( response.body() );
                }
            }

            @Override
            public void onFailure(Call<DataItemResultGroup> call, Throwable t) {
                if( callback != null ){
                    callback.onFailure( t );
                }
            }
        });
    }
    /*************************************************End********************************************************/

    /*************************************************Load report**************************************************/
    public Call<AllItemResultGroup> report(String data, String id) {
        return Service.newInstance( BASE_URL )
                .getApi( api )
                .getAllReport(data, id);
    }

    public void loadReport(String data, String id, final ServiceManagerCallback<AllItemResultGroup> callback) {
        report(data, id).enqueue(new Callback<AllItemResultGroup>() {
            @Override
            public void onResponse(Call<AllItemResultGroup> call, Response<AllItemResultGroup> response) {
                Log.e("request report", response + "");
                if( callback != null ){
                    callback.onSuccess( response.body() );
                }
            }

            @Override
            public void onFailure(Call<AllItemResultGroup> call, Throwable t) {
                Log.e("Report", t.getLocalizedMessage());
                if( callback != null ){
                    callback.onFailure( t );
                }
            }
        });
    }
    /*************************************************End********************************************************/
}
