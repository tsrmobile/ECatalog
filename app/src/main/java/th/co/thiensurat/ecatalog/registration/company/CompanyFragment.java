package th.co.thiensurat.ecatalog.registration.company;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import th.co.thiensurat.ecatalog.R;
import th.co.thiensurat.ecatalog.api.request.RequestRegistration;
import th.co.thiensurat.ecatalog.base.BaseMvpFragment;
import th.co.thiensurat.ecatalog.catalog.adapter.SpinnerCustomAdapter;
import th.co.thiensurat.ecatalog.catalog.item.DataItem;
import th.co.thiensurat.ecatalog.catalog.item.DataItemGroup;
import th.co.thiensurat.ecatalog.utils.AnimateButton;
import th.co.thiensurat.ecatalog.utils.Constance;
import th.co.thiensurat.ecatalog.utils.CustomDialog;
import th.co.thiensurat.ecatalog.utils.MyApplication;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompanyFragment extends BaseMvpFragment<CompanyInterface.Presenter> implements CompanyInterface.View {

    private String province, district, subdistrict;

    private CustomDialog customDialog;
    private SpinnerCustomAdapter spinnerCustomAdapter;

    public CompanyFragment() {
        // Required empty public constructor
    }

    public static CompanyFragment getInstance() {
        return new CompanyFragment();
    }

    @Override
    public CompanyInterface.Presenter createPresenter() {
        return CompanyPresenter.create();
    }

    @Override
    public int getLayoutView() {
        return R.layout.fragment_company;
    }

    /***************************Form************************************/
    @BindView(R.id.comp_name) EditText editTextCompName;
    @BindView(R.id.comp_tax_id) EditText editTextCompTaxID;
    @BindView(R.id.comp_contact_name) EditText editTextContactName;
    @BindView(R.id.comp_contact_mobil) EditText editTextContactMobile;
    @BindView(R.id.address) EditText editTextAddress;
    @BindView(R.id.spinner_province) Spinner spinnerProvince;
    @BindView(R.id.spinner_district) Spinner spinnerDistrict;
    @BindView(R.id.spinner_sub_district) Spinner spinnerSubdistrict;
    @BindView(R.id.zipcode) EditText editTextZipcode;
    @BindView(R.id.button_save) Button buttonSave;
    /***************************End************************************/
    @BindView(R.id.textview_name_error) TextView compNameError;
    @BindView(R.id.textview_tax_error) TextView compTaxError;
    @BindView(R.id.textview_contact_name_error) TextView contactNameError;
    @BindView(R.id.textview_contact_mobile_error) TextView contactMobileError;
    @Override
    public void bindView(View view) {
        ButterKnife.bind(this, view);
        buttonSave.setOnClickListener( onSave() );
    }

    @Override
    public void setupInstance() {
        customDialog = new CustomDialog(getActivity());
    }

    @Override
    public void setupView() {

    }

    @Override
    public void initialize() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getPresenter().requestDataItem("province", "");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Constance.STATE_PROVINCE, getPresenter().onGetDataItemGroup());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        getPresenter().onSetDataItemGroup((DataItemGroup) savedInstanceState.getParcelable(Constance.STATE_PROVINCE));
    }

    @Override
    public void restoreView(Bundle savedInstanceState) {
        super.restoreView(savedInstanceState);
        getPresenter().onRestoreDataItemToAdapter(getPresenter().onGetDataItemGroup());
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
        clearForm();
    }

    @Override
    public void setProvinceToSpinner(final List<DataItem> dataItems) {
        spinnerCustomAdapter = new SpinnerCustomAdapter(getActivity(),
                R.layout.spinner_item, dataItems, getResources(), "province");

        spinnerProvince.setAdapter(spinnerCustomAdapter);
        spinnerProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position > 0) {
                    ((TextView) view.findViewById(R.id.row_item)).setTextColor(getResources().getColor(R.color.Black));
                    DataItem item = dataItems.get(position);
                    province = item.getDataId();
                    getPresenter().requestDataItem("district", province);
                } else {
                    province = "0";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void setDistrictToSpinner(final List<DataItem> dataItems) {
        spinnerCustomAdapter = new SpinnerCustomAdapter(getActivity(),
                R.layout.spinner_item, dataItems, getResources(), "district");

        spinnerDistrict.setAdapter(spinnerCustomAdapter);
        spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position > 0) {
                    ((TextView) view.findViewById(R.id.row_item)).setTextColor(getResources().getColor(R.color.Black));
                    DataItem item = dataItems.get(position);
                    district = item.getDataId();
                    getPresenter().requestDataItem("subdistrict", district);
                } else {
                    district = "0";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void setSubDistrictToSpinner(final List<DataItem> dataItems) {
        spinnerCustomAdapter = new SpinnerCustomAdapter(getActivity(),
                R.layout.spinner_item, dataItems, getResources(), "subdistrict");

        spinnerSubdistrict.setAdapter(spinnerCustomAdapter);
        spinnerSubdistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position > 0) {
                    ((TextView) view.findViewById(R.id.row_item)).setTextColor(getResources().getColor(R.color.Black));
                    DataItem item = dataItems.get(position);
                    subdistrict = item.getDataId();
                    editTextZipcode.setText(item.getDataCode());
                } else {
                    subdistrict = "0";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private View.OnClickListener onSave() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSave.startAnimation(new AnimateButton().animbutton());
                submitForm();
            }
        };
    }

    private void submitForm() {

        if (editTextCompTaxID.getText().toString().isEmpty() && editTextCompName.getText().toString().isEmpty()
                && editTextContactName.getText().toString().isEmpty() && editTextContactName.getText().toString().isEmpty()) {
            compTaxError.setText("* กรุณาระบุเลขที่ผู้เสียภาษี");
            compNameError.setText("* กรุณาระบุชื่อนิติบุคคล");
            contactNameError.setText("* กรุณาระบุชื่อผู้ติดต่อ");
            contactMobileError.setText("* กรุณาระบุเบอร์โทรศัพท์ผู้ติดต่อ");
            return;
        } else {
            if (!editTextCompTaxID.getText().toString().isEmpty()) {
                compTaxError.setText("*");
            } else if (editTextCompTaxID.getText().toString().isEmpty()) {
                compTaxError.setText("* กรุณาระบุเลขที่ผู้เสียภาษี");
                return;
            }

            if (!editTextCompName.getText().toString().isEmpty()) {
                compNameError.setText("*");
            } else if (editTextCompName.getText().toString().isEmpty()) {
                compNameError.setText("* กรุณาระบุชื่อนิติบุคคล");
                return;
            }

            if (!editTextContactName.getText().toString().isEmpty()) {
                contactNameError.setText("*");
            } else if (editTextContactName.getText().toString().isEmpty()) {
                contactNameError.setText("* กรุณาระบุชื่อผู้ติดต่อ");
                return;
            }

            if (!editTextContactMobile.getText().toString().isEmpty()) {
                contactMobileError.setText("*");
            } else if (editTextContactName.getText().toString().isEmpty()) {
                contactMobileError.setText("* กรุณาระบุเบอร์โทรศัพท์ผู้ติดต่อ");
                return;
            }
        }

        List<RequestRegistration.generalBody> generalBodyList = new ArrayList<>();
        generalBodyList.add( new RequestRegistration.generalBody()
                .setIdcard(editTextCompTaxID.getText().toString())
                .setType("02")
                .setTitleid("")
                .setFirstname(editTextCompName.getText().toString())
                .setLastname("")
                .setBirthdate("")
                .setAddress(editTextAddress.getText().toString())
                .setProvinceid(province)
                .setDistrictid(district)
                .setSubdistrictid(subdistrict)
                .setZipcode(editTextZipcode.getText().toString())
                .setPhone("")
                .setMobile("")
                .setEmail("")
                .setLineid("")
                .setFacebook("")
                .setPromtpayaccount("")
                .setContactname(editTextContactName.getText().toString())
                .setContactphone(editTextContactMobile.getText().toString())
                .setEmpid(MyApplication.getInstance().getPrefManager().getPreferrence(Constance.KEY_AGENTID))
        );

        getPresenter().compRegister(generalBodyList);
    }

    private void clearForm() {
        editTextCompName.setText("");
        editTextCompTaxID.setText("");
        editTextContactName.setText("");
        editTextContactMobile.setText("");
        editTextAddress.setText("");
        editTextZipcode.setText("");
        spinnerProvince.setSelection(0);
        spinnerDistrict.setAdapter(null);
        spinnerSubdistrict.setAdapter(null);
    }
}