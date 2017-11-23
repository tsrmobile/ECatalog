package th.co.thiensurat.ecatalog.registration.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import th.co.thiensurat.ecatalog.registration.company.CompanyFragment;
import th.co.thiensurat.ecatalog.registration.foreigner.ForeignerFragment;
import th.co.thiensurat.ecatalog.registration.general.GeneralFragment;

/**
 * Created by teerayut.k on 11/5/2017.
 */

public class RegistrationViewAdapter extends FragmentStatePagerAdapter {

    public RegistrationViewAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return GeneralFragment.getInstance();
            case 1:
                return CompanyFragment.getInstance();
            case 2:
                return ForeignerFragment.getInstance();
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
