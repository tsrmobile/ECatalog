package th.co.thiensurat.ecatalog.profile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.http.Body;
import th.co.thiensurat.ecatalog.R;
import th.co.thiensurat.ecatalog.base.BaseMvpActivity;
import th.co.thiensurat.ecatalog.utils.Constance;
import th.co.thiensurat.ecatalog.utils.MyApplication;
import th.co.thiensurat.ecatalog.utils.MyPreferenceManager;

public class ProfileActivity extends BaseMvpActivity<ProfileInterface.Presenter> implements ProfileInterface.View {

    @Override
    public ProfileInterface.Presenter createPresenter() {
        return ProfilePresenter.create();
    }

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.textview_homeaddress) TextView homeAddress;
    @BindView(R.id.textview_phone) TextView phone;
    @BindView(R.id.textview_mobile) TextView mobile;
    @BindView(R.id.textview_email) TextView email;
    @Override
    public int getLayoutView() {
        return R.layout.activity_profile;
    }

    @Override
    public void bindView() {
        ButterKnife.bind(this);
    }

    @Override
    public void setupInstance() {

    }

    @Override
    public void setupView() {
        setToolbar();
    }

    @Override
    public void initialize() {

    }

    private void setToolbar() {
        setProfile();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            setResult(RESULT_CANCELED);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setProfile() {
        String title = MyApplication.getInstance().getPrefManager().getPreferrence(Constance.KEY_TITLE);
        String firstname = MyApplication.getInstance().getPrefManager().getPreferrence(Constance.KEY_FIRSTNAME);
        String lastname = MyApplication.getInstance().getPrefManager().getPreferrence(Constance.KEY_LASTNAME);
        toolbar.setTitle(title + firstname + " " + lastname);

        String address = MyApplication.getInstance().getPrefManager().getPreferrence(Constance.KEY_ADDRESS);
        String subdistrict = MyApplication.getInstance().getPrefManager().getPreferrence(Constance.KEY_SUBDISTRICT);
        String district = MyApplication.getInstance().getPrefManager().getPreferrence(Constance.KEY_DISTRICT);
        String province = MyApplication.getInstance().getPrefManager().getPreferrence(Constance.KEY_PROVINCE);
        String zipcode = MyApplication.getInstance().getPrefManager().getPreferrence(Constance.KEY_ZIPCODE);
        String fulladdress = address + " ตำบล" + subdistrict + "\nอำเภอ" + district + " จังหวัด" + province + "\n" + zipcode;
        homeAddress.setText(fulladdress);

        phone.setText(MyApplication.getInstance().getPrefManager().getPreferrence(Constance.KEY_PHONE));

        mobile.setText(MyApplication.getInstance().getPrefManager().getPreferrence(Constance.KEY_MOBILE));

        email.setText(MyApplication.getInstance().getPrefManager().getPreferrence(Constance.KEY_EMAIL));
    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onDismiss() {

    }

    @Override
    public void onFail(String fail) {

    }

    @Override
    public void onSuccess(String success) {

    }
}
