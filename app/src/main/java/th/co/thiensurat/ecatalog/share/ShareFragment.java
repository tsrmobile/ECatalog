package th.co.thiensurat.ecatalog.share;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.http.Body;
import th.co.thiensurat.ecatalog.MainActivity;
import th.co.thiensurat.ecatalog.R;
import th.co.thiensurat.ecatalog.base.BaseMvpFragment;
import th.co.thiensurat.ecatalog.share.adapter.ShareAdapter;
import th.co.thiensurat.ecatalog.share.item.BannerItem;
import th.co.thiensurat.ecatalog.utils.AnimateButton;
import th.co.thiensurat.ecatalog.utils.CustomDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShareFragment extends BaseMvpFragment<ShareInterface.Presenter>
        implements ShareInterface.View, ShareAdapter.ClickListener {

    private ShareAdapter adapter;
    private CustomDialog customDialog;
    private List<BannerItem> bannerItems;

    public ShareFragment() {
        // Required empty public constructor
    }

    public static ShareFragment getInstance() {
        return new ShareFragment();
    }

    @Override
    public ShareInterface.Presenter createPresenter() {
        return SharePresenter.create();
    }

    @Override
    public int getLayoutView() {
        return R.layout.fragment_share;
    }

    @BindView(R.id.recyclerview) RecyclerView recyclerView;
    @Override
    public void bindView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public void setupInstance() {
        adapter = new ShareAdapter(getActivity());
        customDialog = new CustomDialog(getActivity());
    }

    @Override
    public void setupView() {
        ((MainActivity)getActivity()).setTitle("SOCIAL LINK");
        setRecyclerView();
    }

    @Override
    public void initialize() {
        getPresenter().requestBanner();
    }

    private void setRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
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
        customDialog.dialogFail(fail);
    }

    @Override
    public void onSuccess(String success) {
        customDialog.dialogSuccess(success);
    }

    @Override
    public void setBannerToAdapter(List<BannerItem> bannerItemList) {
        this.bannerItems = bannerItemList;
        adapter.setBannerItems(bannerItems);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void shareFacebook(View view, int position) {
        view.startAnimation(new AnimateButton().animbutton());
    }

    @Override
    public void shareGoogle(View view, int position) {
        view.startAnimation(new AnimateButton().animbutton());
    }

    @Override
    public void shareTwitter(View view, int position) {
        view.startAnimation(new AnimateButton().animbutton());
    }
}
