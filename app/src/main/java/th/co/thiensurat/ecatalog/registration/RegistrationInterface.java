package th.co.thiensurat.ecatalog.registration;

import th.co.thiensurat.ecatalog.base.BaseMvpInterface;

/**
 * Created by teerayut.k on 11/5/2017.
 */

public class RegistrationInterface {

    public interface View extends BaseMvpInterface.View {

    }

    public interface Presenter extends BaseMvpInterface.Presenter<RegistrationInterface.View> {

    }
}
