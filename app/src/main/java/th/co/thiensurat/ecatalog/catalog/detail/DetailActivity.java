package th.co.thiensurat.ecatalog.catalog.detail;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import babushkatext.BabushkaText;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.http.Body;
import th.co.thiensurat.ecatalog.R;
import th.co.thiensurat.ecatalog.api.request.RequestOrder;
import th.co.thiensurat.ecatalog.api.result.ItemOrder;
import th.co.thiensurat.ecatalog.base.BaseMvpActivity;
import th.co.thiensurat.ecatalog.catalog.adapter.SpinnerCustomAdapter;
import th.co.thiensurat.ecatalog.catalog.adapter.SpinnerTitleAdapter;
import th.co.thiensurat.ecatalog.catalog.item.DataItem;
import th.co.thiensurat.ecatalog.catalog.item.DataItemGroup;
import th.co.thiensurat.ecatalog.catalog.item.ProductItem;
import th.co.thiensurat.ecatalog.catalog.item.TitleItem;
import th.co.thiensurat.ecatalog.catalog.item.TitleItemGroup;
import th.co.thiensurat.ecatalog.utils.AnimateButton;
import th.co.thiensurat.ecatalog.utils.Constance;
import th.co.thiensurat.ecatalog.utils.CustomDialog;
import th.co.thiensurat.ecatalog.utils.MyApplication;
import th.co.thiensurat.ecatalog.utils.Validation;

public class DetailActivity extends BaseMvpActivity<DetailInterface.Presenter> implements DetailInterface.View {

    private int qty = 1;
    private String type = "0";
    private String title = "0";
    private String province = "0";
    private ProductItem productItem;
    private SpinnerCustomAdapter spinnerCustomAdapter;
    private SpinnerTitleAdapter spinnerTitleAdapter;

    private String discount;
    private ItemOrder itemOrder;
    private CustomDialog customDialog;
    private DecimalFormat decimalFormat;
    private DecimalFormatSymbols symbols;
    private List<ItemOrder> itemOrderList = new ArrayList<ItemOrder>();

    @Override
    public DetailInterface.Presenter createPresenter() {
        return DetailPresenter.create();
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_detail;
    }

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.imageView) ImageView imageView;
    @BindView(R.id.button_save) Button buttonSave;
    @BindView(R.id.product_detail) TextView textViewDetail;
    @BindView(R.id.customer_type) Spinner spinnerType;
    @BindView(R.id.customer_title_name) Spinner spinnerTitle;
    @BindView(R.id.customer_name) EditText editTextName;
    @BindView(R.id.customer_lastname) EditText editTextLastName;
    @BindView(R.id.customer_phone) EditText editTextPhone;
    @BindView(R.id.customer_province) Spinner spinnerProvince;
    @BindView(R.id.toolbar_layout) CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.product_view_qty) EditText viewQty;
    @BindView(R.id.product_view_price) TextView viewPrice;
    @BindView(R.id.product_view_discount) TextView viewdiscount;
    @BindView(R.id.product_view_total) TextView viewTotal;
    @BindView(R.id.content_layout) LinearLayout linearLayout;
    @BindView(R.id.button_increasement) ImageView imageViewIncrease;
    @BindView(R.id.button_decreasement) ImageView imageViewDecrease;
    @Override
    public void bindView() {
        ButterKnife.bind(this);
    }

    @Override
    public void setupInstance() {
        symbols = new DecimalFormatSymbols();
        customDialog = new CustomDialog(DetailActivity.this);
    }

    @Override
    public void setupView() {
        getDataFromIntent();
        setToolbar();
        buttonSave.setOnClickListener( onSave() );
        imageViewIncrease.setOnClickListener( onIncrease() );
        imageViewDecrease.setOnClickListener( onDecrease() );
        linearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                hideSoftKeyboard(DetailActivity.this);
                return false;
            }
        });

        //spinnerType.setVisibility(View.GONE);
        //spinnerTitle.setVisibility(View.GONE);
        //spinnerProvince.setVisibility(View.GONE);
    }

    @Override
    public void initialize() {
        getPresenter().requestProvince("province", "");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Constance.STATE_PROVINCE, getPresenter().getDataItemGroup());
        outState.putParcelable(Constance.STATE_TITLE_NAME, getPresenter().getDataTitleGroup());
        outState.putParcelable(Constance.STATE_TYPE, getPresenter().getDataTypeGroup());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        getPresenter().setDataItemGroup((DataItemGroup) savedInstanceState.getParcelable(Constance.STATE_PROVINCE));
        getPresenter().setDataTitleGroup((TitleItemGroup) savedInstanceState.getParcelable(Constance.STATE_TITLE_NAME));
        getPresenter().setDataTypeGroup((DataItemGroup) savedInstanceState.getParcelable(Constance.STATE_TYPE));
    }

    @Override
    public void restoreView(Bundle savedInstanceState) {
        super.restoreView(savedInstanceState);
        getPresenter().setDataToAdapter(getPresenter().getDataItemGroup());
        getPresenter().setDataTitleToAdapter(getPresenter().getDataTitleGroup());
        getPresenter().setDataTypeToAdapter(getPresenter().getDataTypeGroup());
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            setResult(RESULT_OK);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void getDataFromIntent() {
        try {
            productItem = getIntent().getParcelableExtra(Constance.KEY_PRODUCT);
            setItemDetail();
            Log.e("image url", productItem.getProductImage());
        } catch (Exception ex) {
            Log.e("getDataFromIntent", ex.getMessage());
        }
    }

    private void setToolbar() {
        toolbar.setTitle(productItem.getProductName());
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.colorPrimaryDark));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setImageView(String url) {
        Glide.with(DetailActivity.this).load(url).placeholder(R.drawable.no_image).into(imageView);
    }

    private void setItemDetail() {
        symbols.setDecimalSeparator(',');
        decimalFormat = new DecimalFormat("###,###,###,###", symbols);
        setImageView(productItem.getProductImage());
        textViewDetail.setText(productItem.getProductDetail());
        viewPrice.setText(String.format("%,.0f", Float.parseFloat(productItem.getProductPrice())) + ".-");
        viewQty.setText(String.valueOf(qty));
        if (productItem.getProductDiscount().equals("0")) {
            float calculate = Float.parseFloat(productItem.getProductPrice()) * qty;
            calculate = (calculate / 100) * Float.parseFloat(productItem.getProductDiscountPercent());
            discount = decimalFormat.format(calculate);
        } else {
            discount = String.format("%,.0f", Float.parseFloat(productItem.getProductDiscount()) * qty);
        }
        viewdiscount.setText(discount + ".-");
        viewTotal.setText(String.format("%,.0f", Float.parseFloat(productItem.getProductSellPrice())) + ".-");
    }

    private View.OnClickListener onSave() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSave.startAnimation(new AnimateButton().animbutton());
                formValidation();
            }
        };
    }

    private View.OnClickListener onIncrease() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewIncrease.startAnimation(new AnimateButton().animbutton());
                viewQty.setText(String.valueOf(qty++));
                calculateItem(qty);
            }
        };
    }

    private View.OnClickListener onDecrease() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewDecrease.startAnimation(new AnimateButton().animbutton());
                if (qty > 1) {
                    viewQty.setText(String.valueOf(qty--));
                    calculateItem(qty);
                }
            }
        };
    }

    private void calculateItem(int qty) {
        symbols.setDecimalSeparator(',');
        decimalFormat = new DecimalFormat("###,###,###,###", symbols);
        viewPrice.setText(String.format("%,.0f", Float.parseFloat(productItem.getProductPrice()) * qty) + ".-");
        viewQty.setText(String.valueOf(qty));
        if (productItem.getProductDiscount().equals("0")) {
            float calculate = Float.parseFloat(productItem.getProductPrice()) * qty;
            calculate = (calculate / 100) * Float.parseFloat(productItem.getProductDiscountPercent());
            discount = decimalFormat.format(calculate);
        } else {
            discount = String.format("%,.0f", Float.parseFloat(productItem.getProductDiscount()) * qty);
        }
        viewdiscount.setText(discount + ".-");
        viewdiscount.setText(discount);
        viewTotal.setText(String.format("%,.0f", Float.parseFloat(productItem.getProductSellPrice()) * qty) + ".-");
    }

    @Override
    public void setProvince(final List<DataItem> dataItemList) {
        spinnerCustomAdapter = new SpinnerCustomAdapter(DetailActivity.this,
                R.layout.spinner_item, dataItemList, getResources(), "province");

        spinnerProvince.setAdapter(spinnerCustomAdapter);
        spinnerProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position > 0) {
                    ((TextView) view.findViewById(R.id.row_item)).setTextColor(getResources().getColor(R.color.Black));
                    DataItem item = dataItemList.get(position);
                    province = item.getDataId();
                } else {
                    province = "0";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        getPresenter().requestTitleName("titlename");
    }

    @Override
    public void setTitleName(final List<TitleItem> dataItemList) {
        spinnerTitleAdapter = new SpinnerTitleAdapter(DetailActivity.this,
                R.layout.spinner_item, dataItemList, getResources());

        spinnerTitle.setAdapter(spinnerTitleAdapter);
        spinnerTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position > 0) {
                    ((TextView) view.findViewById(R.id.row_item)).setTextColor(getResources().getColor(R.color.Black));
                    TitleItem item = dataItemList.get(position);
                    title = item.getDataCode();
                } else {
                    title = "0";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        getPresenter().requestType("type");
    }

    @Override
    public void setCustomertype(final List<DataItem> dataTypeList) {
        spinnerCustomAdapter = new SpinnerCustomAdapter(DetailActivity.this,
                R.layout.spinner_item, dataTypeList, getResources(), "type");

        spinnerType.setAdapter(spinnerCustomAdapter);
        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position > 0) {
                    ((TextView) view.findViewById(R.id.row_item)).setTextColor(getResources().getColor(R.color.Black));
                    DataItem item = dataTypeList.get(position);
                    type = item.getDataId();
                } else {
                    type = "0";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void insertOrder(String titleid, String name, String lastname, String phone, String provinceid) {
        List<RequestOrder.orderBody> orderBodyList = new ArrayList<>();
        orderBodyList.add(new RequestOrder.orderBody()
                .setTitleId(titleid)
                .setFirstname(name)
                .setLastname(lastname)
                .setPhone(phone)
                .setProvinceId(provinceid)
                .setAgentid(MyApplication.getInstance().getPrefManager().getPreferrence(Constance.KEY_AGENTID))
                .setPromotioncode(productItem.getPromotionCode())
                .setProductcode(productItem.getProductCode())
                .setPrice(productItem.getProductSellPrice())
                .setDiscountprice(productItem.getProductDiscount())
                .setCustomertype(type)
                .setQty(viewQty.getText().toString())
        );

        getPresenter().addOrder(orderBodyList);
    }

    private void formValidation() {
        String firstname = editTextName.getText().toString();
        String lastname = editTextLastName.getText().toString();
        String phone = editTextPhone.getText().toString();

        if (firstname.isEmpty() || lastname.isEmpty() || phone.isEmpty()/* || title.equals("0") || province.equals("0")*/) {
            onFail("กรุณากรอกข้อมูลให้ครบถ้วน");
        } else {
            if (Validation.validCellPhone(phone)) {
                insertOrder(title, firstname, lastname, phone, province);
            } else {
                onFail("หมายเลขโทรศัพท์ไม่ถูกต้อง");
            }
        }
    }

    public void clearForm() {
        spinnerType.setSelection(0);
        spinnerTitle.setSelection(0);
        editTextName.setText("");
        editTextLastName.setText("");
        editTextPhone.setText("");
        spinnerProvince.setSelection(0);
        setResult(RESULT_OK);
        finish();
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
}
