package th.co.thiensurat.ecatalog.registration.foreigner;


import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import th.co.thiensurat.ecatalog.catalog.item.DataItem;
import th.co.thiensurat.ecatalog.utils.AnimateButton;
import th.co.thiensurat.ecatalog.utils.Constance;
import th.co.thiensurat.ecatalog.utils.CustomDialog;
import th.co.thiensurat.ecatalog.utils.MyApplication;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForeignerFragment extends BaseMvpFragment<ForeignerInterface.Presenter> implements ForeignerInterface.View, DatePickerDialog.OnDateSetListener {

    private String country;
    private CustomDialog customDialog;
    private SpinnerCustomAdapter spinnerCustomAdapter;

    private Calendar calendar;
    private DatePickerDialog datePickerDialog;

    public ForeignerFragment() {
        // Required empty public constructor
    }

    public static ForeignerFragment getInstance() {
        return new ForeignerFragment();
    }

    @Override
    public ForeignerInterface.Presenter createPresenter() {
        return ForeignerPresenter.create();
    }

    @Override
    public int getLayoutView() {
        return R.layout.fragment_foreigner;
    }

    @BindView(R.id.name) EditText editTextName;
    @BindView(R.id.passport_id) EditText editTextPassport;
    @BindView(R.id.birth_date) EditText editTextBirthdate;
    @BindView(R.id.contact_name) EditText editTextContactName;
    @BindView(R.id.contact_mobile) EditText editTextContactMobile;
    @BindView(R.id.address) EditText editTextAddress;
    @BindView(R.id.spinner_country) Spinner spinnerCountry;
    @BindView(R.id.image_calendar) ImageView imageViewCalendar;
    @BindView(R.id.button_save) Button buttonSave;
    @BindView(R.id.foreigner_name_error) TextView nameError;
    @BindView(R.id.foreigner_passport_error) TextView passportError;
    @BindView(R.id.foreigner_contact_name_error) TextView contactError;
    @BindView(R.id.foreigner_mobile_error) TextView mobileError;
    @Override
    public void bindView(View view) {
        ButterKnife.bind(this, view);
        imageViewCalendar.setOnClickListener( onCalendar() );
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
        Configuration config;
        config = new Configuration(getResources().getConfiguration());
        config.locale = new Locale("TH", "th");
        Locale locale = getResources().getConfiguration().locale;
        Locale.setDefault(locale);
        calendar = Calendar.getInstance(locale);
        datePickerDialog = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), true);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser)
            getPresenter().requestCountry();
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
    public void setDataCountryToAdapter(final List<DataItem> dataCountryList) {
        spinnerCustomAdapter = new SpinnerCustomAdapter(getActivity(),
                R.layout.spinner_item, dataCountryList, getResources(), "country");

        spinnerCountry.setAdapter(spinnerCustomAdapter);
        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position > 0) {
                    ((TextView) view.findViewById(R.id.row_item)).setTextColor(getResources().getColor(R.color.Black));
                    DataItem item = dataCountryList.get(position);
                    country = item.getDataId();
                } else {
                    country = "0";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {
        String birthDate = convertDateToString(year, month + 1, day);
        editTextBirthdate.setText(birthDate);
    }

    private View.OnClickListener onCalendar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewCalendar.startAnimation(new AnimateButton().animbutton());
                datePickerDialog.setYearRange(1950, calendar.get(Calendar.YEAR));
                datePickerDialog.show(getActivity().getSupportFragmentManager(), Constance.DATEPICKER_TAG);
            }
        };
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

        strYear = String.valueOf(year);
        return strYear + "-" + strMonth + "-" + strDate;
    }

    private void submitForm() {

        if (editTextName.getText().toString().isEmpty() && editTextPassport.getText().toString().isEmpty()
                && editTextContactName.getText().toString().isEmpty() && editTextAddress.getText().toString().isEmpty()) {
            nameError.setText("* Please enter your name.");
            passportError.setText("* Please enter your passport.");
            contactError.setText("* Please enter contact name.");
            mobileError.setText("* Please enter contact phone.");
            return;
        } else {
            if (!editTextName.getText().toString().isEmpty()) {
                nameError.setText("*");
            } else if (editTextName.getText().toString().isEmpty()) {
                nameError.setText("* Please enter your name.");
                return;
            }

            if (!editTextPassport.getText().toString().isEmpty()) {
                passportError.setText("*");
            } else if (editTextPassport.getText().toString().isEmpty()) {
                passportError.setText("* Please enter your passport.");
                return;
            }

            if (!editTextContactName.getText().toString().isEmpty()) {
                contactError.setText("*");
            } else if (editTextContactName.getText().toString().isEmpty()) {
                contactError.setText("* Please enter contact name.");
                return;
            }

            if (!editTextContactMobile.getText().toString().isEmpty()) {
                mobileError.setText("*");
            } else if (editTextContactMobile.getText().toString().isEmpty()) {
                mobileError.setText("* Please enter contact phone.");
                return;
            }
        }
        List<RequestRegistration.generalBody> generalBodyList = new ArrayList<>();
        generalBodyList.add( new RequestRegistration.generalBody()
                .setIdcard(editTextPassport.getText().toString())
                .setType("03")
                .setTitleid("")
                .setFirstname(editTextName.getText().toString())
                .setLastname("")
                .setBirthdate(editTextBirthdate.getText().toString())
                .setAddress(editTextAddress.getText().toString())
                .setProvinceid("")
                .setDistrictid("")
                .setSubdistrictid("")
                .setZipcode("")
                .setPhone("")
                .setMobile("")
                .setEmail("")
                .setLineid("")
                .setFacebook("")
                .setPromtpayaccount("")
                .setContactname(editTextContactName.getText().toString())
                .setContactphone(editTextContactMobile.getText().toString())
                .setCountry(country)
                .setEmpid(MyApplication.getInstance().getPrefManager().getPreferrence(Constance.KEY_AGENTID))
        );

        getPresenter().creatForeigner(generalBodyList);
    }

    private void clearForm() {
        editTextName.setText("");
        editTextAddress.setText("");
        editTextPassport.setText("");
        editTextBirthdate.setText("");
        editTextContactName.setText("");
        editTextContactMobile.setText("");
        spinnerCountry.setSelection(0);
    }
}
