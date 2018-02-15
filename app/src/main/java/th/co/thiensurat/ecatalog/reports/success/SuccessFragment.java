package th.co.thiensurat.ecatalog.reports.success;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
public class SuccessFragment extends BaseMvpFragment<SuccessInterface.Presenter> implements SuccessInterface.View {

    private CustomDialog customDialog;
    private LinearLayoutManager layoutManager;
    private ReportItemAdapter adapter;

    public SuccessFragment() {
        // Required empty public constructor
    }

    public static SuccessFragment getInstance() {
        return new SuccessFragment();
    }

    @Override
    public SuccessInterface.Presenter createPresenter() {
        return SuccessPresenter.create();
    }

    @Override
    public int getLayoutView() {
        return R.layout.fragment_success;
    }

    @BindView(R.id.recyclerview_complete) RecyclerView recyclerViewComplete;
    @BindView(R.id.swipeRefreshLayout_complete) SwipeRefreshLayout swipeRefreshLayoutComplete;
    @BindView(R.id.layout_fail_complete) RelativeLayout relativeLayoutFail;
    @BindView(R.id.imageview_fail_complete) ImageView imageViewFail;
    @BindView(R.id.textview_fail_complete) TextView textViewFail;
    @Override
    public void bindView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public void setupInstance() {
        adapter = new ReportItemAdapter(getActivity());
        customDialog = new CustomDialog(getActivity());
        layoutManager = new LinearLayoutManager(getActivity());
    }

    @Override
    public void setupView() {
        setRecyclerView();
        swipeRefreshLayoutComplete.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().requestCompleteReport("complete", MyApplication.getInstance().getPrefManager().getPreferrence(Constance.KEY_AGENTID));
            }
        });
        swipeRefreshLayoutComplete.setColorSchemeResources(
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
            getPresenter().requestCompleteReport("complete", MyApplication.getInstance().getPrefManager().getPreferrence(Constance.KEY_AGENTID));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Constance.STATE_COMPLETE_REPORT, getPresenter().getCompleteItemGroup());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        getPresenter().setCompleteITemGroup((AllItemGroup) savedInstanceState.getParcelable(Constance.STATE_COMPLETE_REPORT));
    }

    @Override
    public void restoreView(Bundle savedInstanceState) {
        super.restoreView(savedInstanceState);
        getPresenter().setCompleteItemToAdapter(getPresenter().getCompleteItemGroup());
    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onDismiss() {

    }

    @Override
    public void onFail(String fail) {
        swipeRefreshLayoutComplete.setRefreshing(false);
        relativeLayoutFail.setVisibility(View.VISIBLE);
        recyclerViewComplete.setVisibility(View.GONE);
        textViewFail.setText(fail);
    }

    @Override
    public void onSuccess(String success) {

    }

    private void setRecyclerView() {
        recyclerViewComplete.setLayoutManager(layoutManager);
        recyclerViewComplete.setHasFixedSize(true);
    }

    @Override
    public void setCompleteItemToAdapter(List<AllItem> allItemList) {
        swipeRefreshLayoutComplete.setRefreshing(false);

        relativeLayoutFail.setVisibility(View.GONE);
        recyclerViewComplete.setVisibility(View.VISIBLE);

        adapter.setReportItem(allItemList);
        recyclerViewComplete.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
