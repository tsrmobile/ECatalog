package th.co.thiensurat.ecatalog.api;


import th.co.thiensurat.ecatalog.api.base.BaseService;

/**
 * Created by teerayut.k on 7/17/2017.
 */

public class Service extends BaseService<ApiService> {

    public static Service newInstance( String baseUrl ) {
        Service service = new Service();
        service.setBaseUrl( baseUrl );
        return service;
    }

    public Service() {

    }

    @Override
    protected Class<ApiService> getApiClassType() {
        return ApiService.class;
    }
}
