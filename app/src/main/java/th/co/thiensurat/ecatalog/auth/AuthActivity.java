package th.co.thiensurat.ecatalog.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import th.co.thiensurat.ecatalog.api.result.ItemAuth;
import th.co.thiensurat.ecatalog.MainActivity;
import th.co.thiensurat.ecatalog.R;
import th.co.thiensurat.ecatalog.base.BaseMvpActivity;
import th.co.thiensurat.ecatalog.network.ConnectionDetector;
import th.co.thiensurat.ecatalog.utils.AnimateButton;
import th.co.thiensurat.ecatalog.utils.ChangeTintColor;
import th.co.thiensurat.ecatalog.utils.Constance;
import th.co.thiensurat.ecatalog.utils.CustomDialog;
import th.co.thiensurat.ecatalog.utils.MyApplication;

import static th.co.thiensurat.ecatalog.utils.Constance.REQUEST_SETTINGS;

public class AuthActivity extends BaseMvpActivity<AuthInterface.Presenter> implements AuthInterface.View {

    private boolean clickBackAain;
    private CustomDialog customDialog;
    private ChangeTintColor changeTintColor;
    private List<ItemAuth> itemAuthList = new ArrayList<ItemAuth>();

    @Override
    public AuthInterface.Presenter createPresenter() {
        return AuthPresenter.create();
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_auth;
    }

    @BindView(R.id.edt_user) EditText username;
    @BindView(R.id.edt_pwd) EditText password;
    @BindView(R.id.button_login) Button buttonLogin;
    @BindView(R.id.foget_password) TextView textViewForgetPassword;
    @Override
    public void bindView() {
        ButterKnife.bind(this);
    }

    @Override
    public void setupInstance() {
        customDialog = new CustomDialog(AuthActivity.this);
        changeTintColor = new ChangeTintColor(AuthActivity.this);
    }

    @Override
    public void setupView() {
        buttonLogin.setOnClickListener( onLogin() );
        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(password.getWindowToken(), 0);
                    buttonLogin.performClick();
                    return true;
                }
                return false;
            }
        });
        textViewForgetPassword.setOnClickListener( onForget() );

        changeTintColor.setEditTextDrawableColor(username, R.color.DarkGray);
        changeTintColor.setEditTextDrawableColor(password, R.color.DarkGray);
    }

    @Override
    public void initialize() {
        boolean isNetworkAvailable = ConnectionDetector.isConnectingToInternet(AuthActivity.this);
        if (!isNetworkAvailable) {
            customDialog.dialogNetworkError();
        } else {
            loginSession();
        }
    }

    private View.OnClickListener onLogin() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLogin.startAnimation(new AnimateButton().animbutton());
                getUsernamePassword();

            }
        };
    }

    private void getUsernamePassword() {
        ItemAuth item = new ItemAuth();
        item.setUsername(username.getText().toString());
        item.setPassword(password.getText().toString());
        itemAuthList.add(item);
        getPresenter().auth(itemAuthList);
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
    public void onSuccess() {
        nextPage();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)){
            if (clickBackAain) {
                finish();
                return true;
            }
            this.clickBackAain = true;
            Toast.makeText(AuthActivity.this, "คลิกอีกครั้งเพื่อออกจากแอพพลิเคชั่น", Toast.LENGTH_LONG).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    clickBackAain=false;
                }
            }, 2000);
            return false;
        }
        return true;
    }

    private void loginSession() {
        try {
            if (!MyApplication.getInstance().getPrefManager().getPreferrence(Constance.KEY_AGENTID).isEmpty()) {
                nextPage();
            }
        } catch (Exception ex) {

        }
    }

    private void nextPage() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constance.REQUEST_SETTINGS) {
            initialize();
        }
    }

    private View.OnClickListener onForget() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewForgetPassword.startAnimation(new AnimateButton().animbutton());
            }
        };
    }
}
