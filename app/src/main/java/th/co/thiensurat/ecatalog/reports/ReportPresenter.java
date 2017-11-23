package th.co.thiensurat.ecatalog.reports;

import th.co.thiensurat.ecatalog.base.BaseMvpPresenter;

/**
 * Created by teerayut.k on 11/7/2017.
 */

public class ReportPresenter extends BaseMvpPresenter<ReportInterface.View> implements ReportInterface.Presenter {

    public static ReportInterface.Presenter create() {
        return new ReportPresenter();
    }
}
