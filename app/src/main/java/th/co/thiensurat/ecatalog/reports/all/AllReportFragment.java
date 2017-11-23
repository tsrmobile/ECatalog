package th.co.thiensurat.ecatalog.reports.all;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import th.co.thiensurat.ecatalog.R;
import th.co.thiensurat.ecatalog.base.BaseMvpFragment;
import th.co.thiensurat.ecatalog.reports.adapter.ReportItemAdapter;
import th.co.thiensurat.ecatalog.reports.item.AllItem;
import th.co.thiensurat.ecatalog.reports.item.AllItemGroup;
import th.co.thiensurat.ecatalog.utils.Constance;
import th.co.thiensurat.ecatalog.utils.CustomDialog;
import th.co.thiensurat.ecatalog.utils.MyApplication;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllReportFragment extends BaseMvpFragment<AllReportInterface.Presenter> implements AllReportInterface.View, SwipeRefreshLayout.OnRefreshListener {

    private CustomDialog customDialog;
    private LinearLayoutManager layoutManager;
    private ReportItemAdapter reportItemAdapter;

    public AllReportFragment() {
        // Required empty public constructor
    }

    public static AllReportFragment getInstance() {
        return new AllReportFragment();
    }

    @Override
    public AllReportInterface.Presenter createPresenter() {
        return AllReportPresenter.create();
    }

    @Override
    public int getLayoutView() {
        return R.layout.fragment_all_report;
    }

    @BindView(R.id.recyclerview) RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.layout_fail) RelativeLayout relativeLayoutFail;
    @BindView(R.id.imageview_fail) ImageView imageViewFail;
    @BindView(R.id.textview_fail) TextView textViewFail;
    @Override
    public void bindView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public void setupInstance() {
        layoutManager = new LinearLayoutManager(getActivity());
        customDialog = new CustomDialog(getActivity());
        reportItemAdapter = new ReportItemAdapter(getActivity());
    }

    @Override
    public void setupView() {
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorAccent,
                R.color.Purple);
    }

    @Override
    public void initialize() {
        getPresenter().requestReport("all", MyApplication.getInstance().getPrefManager().getPreferrence(Constance.KEY_AGENTID));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Constance.STATE_ALL_REPORT, getPresenter().getAllItemGroup());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        getPresenter().setAllITemGroup((AllItemGroup) savedInstanceState.getParcelable(Constance.STATE_ALL_REPORT));
    }

    @Override
    public void restoreView(Bundle savedInstanceState) {
        super.restoreView(savedInstanceState);
        getPresenter().setItemToAdapter(getPresenter().getAllItemGroup());
    }

    @Override
    public void onRefresh() {
        getPresenter().requestReport("all", MyApplication.getInstance().getPrefManager().getPreferrence(Constance.KEY_AGENTID));
    }

    @Override
    public void onLoad() {
        customDialog.dialogLoading();
    }

    @Override
    public void onDismiss() {
        customDialog.dialogDimiss();
    }

    @Override
    public void onFail(String fail) {
        relativeLayoutFail.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        textViewFail.setText(fail);
    }

    @Override
    public void onSuccess(String success) {

    }

    @Override
    public void setAllItemToAdapter(List<AllItem> allItems) {
        swipeRefreshLayout.setRefreshing(false);

        relativeLayoutFail.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);

        reportItemAdapter.setReportItem(allItems);
        recyclerView.setAdapter(reportItemAdapter);
        reportItemAdapter.notifyDataSetChanged();
    }
}
