package th.co.thiensurat.ecatalog.catalog;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import th.co.thiensurat.ecatalog.R;
import th.co.thiensurat.ecatalog.auth.AuthActivity;
import th.co.thiensurat.ecatalog.base.BaseMvpFragment;
import th.co.thiensurat.ecatalog.catalog.adapter.CatalogAdapter;
import th.co.thiensurat.ecatalog.catalog.detail.DetailActivity;
import th.co.thiensurat.ecatalog.catalog.item.ProductItem;
import th.co.thiensurat.ecatalog.catalog.item.ProductItemGroup;
import th.co.thiensurat.ecatalog.network.ConnectionDetector;
import th.co.thiensurat.ecatalog.utils.AnimateButton;
import th.co.thiensurat.ecatalog.utils.Constance;
import th.co.thiensurat.ecatalog.utils.CustomDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class CatalogFragment extends BaseMvpFragment<CatalogInterface.Presenter>
        implements CatalogInterface.View, CatalogAdapter.ClickListener {

    private CatalogAdapter adapter;
    private CustomDialog customDialog;
    private List<ProductItem> productItemList = new ArrayList<ProductItem>();

    public CatalogFragment() {
        super();
    }

    public static CatalogFragment getInstance() {
        return new CatalogFragment();
    }

    @Override
    public CatalogInterface.Presenter createPresenter() {
        return CatalogPresenter.create();
    }

    @Override
    public int getLayoutView() {
        return R.layout.fragment_catalog;
    }

    @BindView(R.id.recyclerview) RecyclerView recyclerView;
    @BindView(R.id.catalog_fail) RelativeLayout failLayout;
    @BindView(R.id.imageview_fail) ImageView imageViewFail;
    @BindView(R.id.textview_fail) TextView textViewFail;
    @Override
    public void bindView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public void setupInstance() {
        adapter = new CatalogAdapter(getActivity());
        customDialog = new CustomDialog(getActivity());
    }

    @Override
    public void setupView() {
        setRecyclerView();
    }

    @Override
    public void initialize() {
        boolean isNetworkAvailable = ConnectionDetector.isConnectingToInternet(getActivity());
        if (!isNetworkAvailable) {
            recyclerView.setVisibility(View.GONE);
            failLayout.setVisibility(View.VISIBLE);
            imageViewFail.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_internet_error));
            textViewFail.setText("กรุณาเชื่อมต่ออินเตอร์เน็ต");
        } else {
            getPresenter().requestProduct();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Constance.STATE_PRODUCT, getPresenter().onGetProductGroup());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        getPresenter().onSetProductGroup((ProductItemGroup) savedInstanceState.getParcelable(Constance.STATE_PRODUCT));
    }

    @Override
    public void restoreView(Bundle savedInstanceState) {
        super.restoreView(savedInstanceState);
        getPresenter().onRestoreProductToAdapter(getPresenter().onGetProductGroup());
    }

    private void setRecyclerView() {
        StaggeredGridLayoutManager layout = new StaggeredGridLayoutManager( 2, StaggeredGridLayoutManager.VERTICAL );
        recyclerView.setLayoutManager(layout);
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
        //customDialog.dialogFail(fail);
        recyclerView.setVisibility(View.GONE);
        failLayout.setVisibility(View.VISIBLE);
        textViewFail.setText(fail);
    }

    @Override
    public void setProductToAdapter(List<ProductItem> productList) {
        failLayout.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        this.productItemList = productList;
        adapter.setProductItemList(productItemList);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
    }

    @Override
    public void onItemclicked(View view, int position) {
        view.startAnimation(new AnimateButton().animbutton());
        ProductItem item = productItemList.get(position);
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(Constance.KEY_PRODUCT, item);
        getActivity().startActivityForResult(intent, Constance.REQUEST_DETAIL);
    }
}
