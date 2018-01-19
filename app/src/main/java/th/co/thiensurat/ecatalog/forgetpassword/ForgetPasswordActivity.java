package th.co.thiensurat.ecatalog.forgetpassword;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import th.co.thiensurat.ecatalog.R;
import th.co.thiensurat.ecatalog.base.BaseMvpActivity;
import th.co.thiensurat.ecatalog.utils.AnimateButton;
import th.co.thiensurat.ecatalog.utils.ChangeTintColor;
import th.co.thiensurat.ecatalog.utils.CustomDialog;

public class ForgetPasswordActivity extends BaseMvpActivity<ForgetPasswordInterface.Presenter> implements ForgetPasswordInterface.View {

    private TextView textViewTitle;
    private CustomDialog customDialog;
    private ChangeTintColor changeTintColor;

    @Override
    public ForgetPasswordInterface.Presenter createPresenter() {
        return ForgetPasswordPresenter.create();
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_forget_password;
    }

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.edt_email) EditText editText;
    @BindView(R.id.button_reset) Button buttonReset;
    @Override
    public void bindView() {
        ButterKnife.bind(this);
    }

    @Override
    public void setupInstance() {
        customDialog = new CustomDialog(ForgetPasswordActivity.this);
        changeTintColor = new ChangeTintColor(ForgetPasswordActivity.this);
    }

    @Override
    public void setupView() {
        setToolbar();
        buttonReset.setOnClickListener( onReset() );
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    buttonReset.performClick();
                    return true;
                }
                return false;
            }
        });
        changeTintColor.setEditTextDrawableColor(editText, R.color.DarkGray);
    }

    @Override
    public void initialize() {

    }

    private void setToolbar() {
        toolbar.setTitle("");
        textViewTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        textViewTitle.setText("รีเซ็ตรหัสผ่าน");
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            setResult(RESULT_CANCELED);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private View.OnClickListener onReset() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonReset.startAnimation(new AnimateButton().animbutton());
                getPresenter().requestReset(editText.getText().toString().trim());
            }
        };
    }
}
