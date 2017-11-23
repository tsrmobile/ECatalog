package th.co.thiensurat.ecatalog.reports.cancel;


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
import th.co.thiensurat.ecatalog.utils.MyApplication;

/**
 * A simple {@link Fragment} subclass.
 */
public class CancelFragment extends BaseMvpFragment<CancelInterface.Presenter> implements CancelInterface.View {

    private ReportItemAdapter adapter;
    private LinearLayoutManager layoutManager;

    public CancelFragment() {
        // Required empty public constructor
    }

    public static CancelFragment getInstance() {
        return new CancelFragment();
    }

    @Override
    public CancelInterface.Presenter createPresenter() {
        return CancelPresenter.create();
    }

    @Override
    public int getLayoutView() {
        return R.layout.fragment_cancel;
    }

    @BindView(R.id.swipeRefreshLayout_cancel) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerview_cancel) RecyclerView recyclerView;
    @BindView(R.id.layout_fail_cancel) RelativeLayout layoutCancel;
    @BindView(R.id.imageview_fail_cancel) ImageView imageViewCancel;
    @BindView(R.id.textview_fail_cancel) TextView textViewCancel;
    @Override
    public void bindView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public void setupInstance() {
        adapter = new ReportItemAdapter(getActivity());
        layoutManager = new LinearLayoutManager(getActivity());
    }

    @Override
    public void setupView() {
        setRecyclerView();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().requestCancelReport("cancel", MyApplication.getInstance().getPrefManager().getPreferrence(Constance.KEY_AGENTID));
            }
        });
        swipeRefreshLayout.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorAccent,
                R.color.Purple);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getPresenter().requestCancelReport("cancel", MyApplication.getInstance().getPrefManager().getPreferrence(Constance.KEY_AGENTID));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Constance.STATE_CANCEL_REPORT, getPresenter().getCancelItemGroup());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        getPresenter().setCancelItemGroup((AllItemGroup) savedInstanceState.getParcelable(Constance.STATE_CANCEL_REPORT));
    }

    @Override
    public void restoreView(Bundle savedInstanceState) {
        super.restoreView(savedInstanceState);
        getPresenter().setCancelItemToAdapter(getPresenter().getCancelItemGroup());
    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onDismiss() {

    }

    @Override
    public void onFail(String fail) {
        layoutCancel.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        textViewCancel.setText(fail);
    }

    @Override
    public void onSuccess(String succes) {

    }

    private void setRecyclerView() {
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void setCancelItemToAdapter(List<AllItem> allItemList) {
        swipeRefreshLayout.setRefreshing(false);

        layoutCancel.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);

        adapter.setReportItem(allItemList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
