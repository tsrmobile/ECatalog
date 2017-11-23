package th.co.thiensurat.ecatalog.registration.general;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.fourmob.datetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import th.co.thiensurat.ecatalog.R;
import th.co.thiensurat.ecatalog.api.request.RequestRegistration;
import th.co.thiensurat.ecatalog.base.BaseMvpFragment;
import th.co.thiensurat.ecatalog.catalog.adapter.SpinnerCustomAdapter;
import th.co.thiensurat.ecatalog.catalog.adapter.SpinnerTitleAdapter;
import th.co.thiensurat.ecatalog.catalog.item.DataItem;
import th.co.thiensurat.ecatalog.catalog.item.DataItemGroup;
import th.co.thiensurat.ecatalog.catalog.item.TitleItem;
import th.co.thiensurat.ecatalog.catalog.item.TitleItemGroup;
import th.co.thiensurat.ecatalog.utils.AnimateButton;
import th.co.thiensurat.ecatalog.utils.Constance;
import th.co.thiensurat.ecatalog.utils.CustomDialog;
import th.co.thiensurat.ecatalog.utils.MyApplication;
import th.co.thiensurat.ecatalog.utils.Validation;

/**
 * A simple {@link Fragment} subclass.
 */
public class GeneralFragment extends BaseMvpFragment<GeneralInterface.Presenter> implements GeneralInterface.View, DatePickerDialog.OnDateSetListener {

    private String title;
    private String promtpay;
    private CustomDialog customDialog;
    private String province, district, subdistrict;
    private SpinnerCustomAdapter spinnerCustomAdapter;
    private SpinnerTitleAdapter spinnerTitleAdapter;

    private Calendar calendar;
    private DatePickerDialog datePickerDialog;

    public GeneralFragment() {
        // Required empty public constructor
    }

    public static GeneralFragment getInstance() {
        return new GeneralFragment();
    }

    @Override
    public GeneralInterface.Presenter createPresenter() {
        return GeneralPresenter.create();
    }

    @Override
    public int getLayoutView() {
        return R.layout.fragment_general;
    }

    @BindView(R.id.layout_container) RelativeLayout container;
    /***************************Form************************************/
    @BindView(R.id.idcard) EditText editTextID;
    @BindView(R.id.spinner_title) Spinner spinnerTitle;
    @BindView(R.id.firstname) EditText editTextFirstname;
    @BindView(R.id.lastname) EditText editTextLastname;
    @BindView(R.id.birth_date) EditText editTextBirthdate;
    @BindView(R.id.address) EditText editTextAddress;
    @BindView(R.id.spinner_province) Spinner spinnerProvince;
    @BindView(R.id.spinner_district) Spinner spinnerDistrict;
    @BindView(R.id.spinner_sub_district) Spinner spinnerSubdistrict;
    @BindView(R.id.zipcode) EditText editTextZipcode;
    @BindView(R.id.phone) EditText editTextPhone;
    @BindView(R.id.mobile) EditText editTextMobile;
    @BindView(R.id.facebook) EditText editTextFacebook;
    @BindView(R.id.line) EditText editTextLine;
    @BindView(R.id.email) EditText editTextEmail;
    @BindView(R.id.radioGroup) RadioGroup promtpayGroup;
    @BindView(R.id.no) RadioButton radioButtonNo;
    @BindView(R.id.promtpay) RadioButton radioButtonPromtpay;
    @BindView(R.id.button_save) Button buttonSave;
    @BindView(R.id.image_calendar) ImageView imageViewCalendar;
    /***************************End************************************/
    @BindView(R.id.idcard_error) TextView idcardError;
    @BindView(R.id.title_error) TextView titleError;
    @BindView(R.id.firstname_error) TextView firstnameError;
    @BindView(R.id.lastname_error) TextView lastnameError;
    @BindView(R.id.birthdate_error) TextView birthdateError;
    @BindView(R.id.phone_error) TextView phoneError;
    @BindView(R.id.mobile_error) TextView mobileError;
    @BindView(R.id.email_error) TextView emailError;
    @Override
    public void bindView(View view) {
        ButterKnife.bind(this, view);
        buttonSave.setOnClickListener( onSave() );
        imageViewCalendar.setOnClickListener( onCalendar() );
        editTextBirthdate.setOnClickListener( onCalendar() );
        radioButtonNo.setOnClickListener( OptionOnClickListener );
        radioButtonPromtpay.setOnClickListener( OptionOnClickListener );
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
        Configuration config;
        config = new Configuration(getResources().getConfiguration());
        config.locale = new Locale("TH", "th");
        Locale locale = getResources().getConfiguration().locale;
        Locale.setDefault(locale);
        calendar = Calendar.getInstance(locale);
        datePickerDialog = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), true);
        getPresenter().requestTitleName();
        getPresenter().requestDataItem("province", "");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Constance.STATE_TITLE_NAME, getPresenter().getDataTitleGroup());
        outState.putParcelable(Constance.STATE_PROVINCE, getPresenter().onGetDataItemGroup());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        getPresenter().setDataTitleGroup((TitleItemGroup) savedInstanceState.getParcelable(Constance.STATE_TITLE_NAME));
        getPresenter().onSetDataItemGroup((DataItemGroup) savedInstanceState.getParcelable(Constance.STATE_PROVINCE));
    }

    @Override
    public void restoreView(Bundle savedInstanceState) {
        super.restoreView(savedInstanceState);
        getPresenter().setDataTitleToAdapter(getPresenter().getDataTitleGroup());
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
    public void setTitleName(final List<TitleItem> dataItemList) {
        spinnerTitleAdapter = new SpinnerTitleAdapter(getActivity(),
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

    private View.OnClickListener onCalendar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewCalendar.startAnimation(new AnimateButton().animbutton());
                //datePickerDialog.setVibrate(true);
                datePickerDialog.setYearRange(1950, calendar.get(Calendar.YEAR));
                datePickerDialog.show(getActivity().getSupportFragmentManager(), Constance.DATEPICKER_TAG);
            }
        };
    }

    private void submitForm() {
        if (!editTextID.getText().toString().isEmpty() && editTextID.getText().toString().length() == 13) {
            if (!Validation.checkDigit(editTextID.getText().toString())) {
                idcardError.setText("* เลขที่บัตรประชาชนไม่ถูกต้อง");
                return;
            } else {
                idcardError.setText("*");
            }
        } else if (editTextID.getText().toString().isEmpty()) {
            idcardError.setText("* กรุณาระบุเลขที่บัตรประชาชน");
        } else if (editTextID.getText().toString().length() != 13) {
            idcardError.setText("* เลขที่บัตรประชาชนไม่ถูกต้อง");
        }

        if (!editTextFirstname.getText().toString().isEmpty()) {
            firstnameError.setText("*");
        } else if (editTextFirstname.getText().toString().isEmpty()) {
            firstnameError.setText("* กรุณาระบุชื่อผู้สมัคร");
            return;
        }

        if (!editTextLastname.getText().toString().isEmpty()) {
            lastnameError.setText("*");
        } else if (editTextLastname.getText().toString().isEmpty()) {
            lastnameError.setText("* กรุณาระบุนามสกุลผู้สมัคร");
            return;
        }

        if (!editTextEmail.getText().toString().isEmpty()) {
            if (!Validation.validateEmail(editTextEmail.getText().toString())) {
                emailError.setText("* อีเมลไม่ถูกต้อง");
                return;
            }
        } else {
            emailError.setText("*");
        }

        if (editTextPhone.getText().toString().length() < 10 && !editTextPhone.getText().toString().isEmpty()) {
            phoneError.setText("* เบอร์โทรศัพท์ไม่ถูกต้อง");
            return;
        } else {
            phoneError.setText("*");
        }

        if (editTextMobile.getText().toString().length() < 10 && !editTextMobile.getText().toString().isEmpty()) {
            mobileError.setText("* เบอร์มือถือไม่ถูกต้อง");
            return;
        } else if (editTextMobile.getText().toString().isEmpty()) {
            mobileError.setText("* กรุณาระบุเบอร์มือถือ");
        } else {
            mobileError.setText("*");
        }

        List<RequestRegistration.generalBody> generalBodyList = new ArrayList<>();
        generalBodyList.add( new RequestRegistration.generalBody()
                .setIdcard(editTextID.getText().toString())
                .setType("01")
                .setTitleid(title)
                .setFirstname(editTextFirstname.getText().toString())
                .setLastname(editTextLastname.getText().toString())
                .setBirthdate(editTextBirthdate.getText().toString())
                .setAddress(editTextAddress.getText().toString())
                .setProvinceid(province)
                .setDistrictid(district)
                .setSubdistrictid(subdistrict)
                .setZipcode(editTextZipcode.getText().toString())
                .setPhone(editTextPhone.getText().toString())
                .setMobile(editTextMobile.getText().toString())
                .setEmail(editTextEmail.getText().toString())
                .setLineid(editTextLine.getText().toString())
                .setFacebook(editTextFacebook.getText().toString())
                .setPromtpayaccount(promtpay)
                .setContactname("")
                .setContactphone("")
                .setEmpid(MyApplication.getInstance().getPrefManager().getPreferrence(Constance.KEY_AGENTID))
        );

        getPresenter().creatFreeAgent(generalBodyList);
    }

    private void clearForm() {
        editTextID.setText("");
        spinnerTitle.setSelection(0);
        editTextFirstname.setText("");
        editTextLastname.setText("");
        editTextBirthdate.setText("");
        editTextAddress.setText("");
        spinnerProvince.setSelection(0);
        spinnerDistrict.setAdapter(null);
        spinnerSubdistrict.setAdapter(null);
        editTextZipcode.setText("");
        editTextPhone.setText("");
        editTextMobile.setText("");
        editTextEmail.setText("");
        editTextLine.setText("");
        editTextFacebook.setText("");
        promtpayGroup.clearCheck();
        container.clearFocus();
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {
        String birthDate = convertDateToString(year, month + 1, day);
        editTextBirthdate.setText(birthDate);
    }

    private String convertDateToString(int year, int month, int day) {
        String strMonth, strDate, strYear;
        if (month < 10) {
            strMonth = "0" + month;
        } else {
            strMonth = "" + month;
        }

        if (day < 10) {
            strDate = "0" + day;
        } else {
            strDate = "" + day;
        }

        strYear = String.valueOf(year/* + 543*/);
        return strYear + "-" + strMonth + "-" + strDate;
    }

    RadioButton.OnClickListener OptionOnClickListener = new RadioButton.OnClickListener() {
        public void onClick(View v) {
            if (radioButtonNo.isChecked()) {
                promtpay = "0";
            } else {
                promtpay = "1";
            }
        }
    };
}
