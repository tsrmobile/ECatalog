package th.co.thiensurat.ecatalog.reports;

import th.co.thiensurat.ecatalog.base.BaseMvpInterface;

/**
 * Created by teerayut.k on 11/7/2017.
 */

public class ReportInterface {

    public interface View extends BaseMvpInterface.View {

    }

    public interface Presenter extends BaseMvpInterface.Presenter<ReportInterface.View> {

    }
}
