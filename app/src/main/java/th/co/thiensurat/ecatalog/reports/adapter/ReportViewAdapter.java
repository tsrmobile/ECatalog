package th.co.thiensurat.ecatalog.reports.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import th.co.thiensurat.ecatalog.reports.all.AllReportFragment;
import th.co.thiensurat.ecatalog.reports.cancel.CancelFragment;
import th.co.thiensurat.ecatalog.reports.success.SuccessFragment;

/**
 * Created by teerayut.k on 11/7/2017.
 */

public class ReportViewAdapter extends FragmentStatePagerAdapter {


    public ReportViewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 :
                return AllReportFragment.getInstance();
            case 1 :
                return SuccessFragment.getInstance();
            case 2 :
                return CancelFragment.getInstance();
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
