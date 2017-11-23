package th.co.thiensurat.ecatalog.reports;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import th.co.thiensurat.ecatalog.MainActivity;
import th.co.thiensurat.ecatalog.R;
import th.co.thiensurat.ecatalog.base.BaseMvpFragment;
import th.co.thiensurat.ecatalog.reports.adapter.ReportViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReportFragment extends BaseMvpFragment<ReportInterface.Presenter> implements ReportInterface.View {

    private ReportViewAdapter adapter;

    public ReportFragment() {
        // Required empty public constructor
    }

    public static ReportFragment getInstance() {
        return new ReportFragment();
    }

    @Override
    public ReportInterface.Presenter createPresenter() {
        return ReportPresenter.create();
    }

    @Override
    public int getLayoutView() {
        return R.layout.fragment_report;
    }

    @BindView(R.id.pager) ViewPager viewPager;
    @BindView(R.id.tab_layout) TabLayout tabLayout;
    @Override
    public void bindView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public void setupInstance() {
        adapter = new ReportViewAdapter(getFragmentManager());
    }

    @Override
    public void setupView() {
        ((MainActivity)getActivity()).setTitle("REPORTS");
        setTabLayout();
    }

    @Override
    public void initialize() {

    }

    private void setTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText("ทั้งหมด"));
        tabLayout.addTab(tabLayout.newTab().setText("สำเร็จ"));
        tabLayout.addTab(tabLayout.newTab().setText("ยกเลิก"));
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
}
