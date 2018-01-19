package th.co.thiensurat.ecatalog.registration;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.ViewPager;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ShareActionProvider;

import java.io.UnsupportedEncodingException;

import butterknife.BindView;
import butterknife.ButterKnife;
import th.co.thiensurat.ecatalog.MainActivity;
import th.co.thiensurat.ecatalog.R;
import th.co.thiensurat.ecatalog.base.BaseMvpFragment;
import th.co.thiensurat.ecatalog.registration.adapter.RegistrationViewAdapter;
import th.co.thiensurat.ecatalog.utils.Constance;
import th.co.thiensurat.ecatalog.utils.MyApplication;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends BaseMvpFragment<RegistrationInterface.Presenter> implements RegistrationInterface.View {

    private RegistrationViewAdapter adapter;

    public RegistrationFragment() {
        // Required empty public constructor
    }

    public static RegistrationFragment getInstance() {
        return new RegistrationFragment();
    }

    @Override
    public RegistrationInterface.Presenter createPresenter() {
        return RegistrationPresenter.create();
    }

    @Override
    public int getLayoutView() {
        return R.layout.fragment_registration;
    }

    @BindView(R.id.pager) ViewPager viewPager;
    @BindView(R.id.tab_layout) TabLayout tabLayout;
    @Override
    public void bindView(View view) {
        ButterKnife.bind(this, view);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public void setupInstance() {
        adapter = new RegistrationViewAdapter(getFragmentManager());
    }

    @Override
    public void setupView() {
        ((MainActivity)getActivity()).setTitle("สมัครตัวแทนขาย");
        setTabLayout();
        setHasOptionsMenu(true);
    }

    @Override
    public void initialize() {

    }

    private void setTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText("บุคคลธรรมดา"));
        tabLayout.addTab(tabLayout.newTab().setText("นิติบุคลล"));
        tabLayout.addTab(tabLayout.newTab().setText("ชาวต่างชาติ"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_share) {
            String empid = MyApplication.getInstance().getPrefManager().getPreferrence(Constance.KEY_AGENTID);
            byte[] data = new byte[0];
            try {
                data = empid.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            String base64 = Base64.encodeToString(data, Base64.NO_WRAP);

            StringBuilder sb = new StringBuilder();
            sb.delete(0, sb.length());
            sb.append("http://app.thiensurat.co.th/RegisterAgent/Register?promotion=&creator=");
            sb.append(base64 + "&ordertype=Mw==&reftype=F");
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "สมัครตัวแทนขาย");
            sharingIntent.putExtra(Intent.EXTRA_TEXT, sb.toString());
            sharingIntent.setType("text/plain");
            getActivity().startActivityForResult(Intent.createChooser(sharingIntent,  "TOSS Application"), Constance.REQUEST_SHARE_LINK);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_share, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
