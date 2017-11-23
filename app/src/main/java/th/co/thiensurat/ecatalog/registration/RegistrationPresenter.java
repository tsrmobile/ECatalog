package th.co.thiensurat.ecatalog.registration;

import th.co.thiensurat.ecatalog.base.BaseMvpPresenter;

/**
 * Created by teerayut.k on 11/5/2017.
 */

public class RegistrationPresenter extends BaseMvpPresenter<RegistrationInterface.View> implements RegistrationInterface.Presenter {

    public static RegistrationInterface.Presenter create() {
        return new RegistrationPresenter();
    }
}
