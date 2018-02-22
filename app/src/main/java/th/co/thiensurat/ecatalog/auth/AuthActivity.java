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

import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import th.co.thiensurat.ecatalog.api.result.ItemAuth;
import th.co.thiensurat.ecatalog.MainActivity;
import th.co.thiensurat.ecatalog.R;
import th.co.thiensurat.ecatalog.base.BaseMvpActivity;
import th.co.thiensurat.ecatalog.forgetpassword.ForgetPasswordActivity;
import th.co.thiensurat.ecatalog.network.ConnectionDetector;
import th.co.thiensurat.ecatalog.pinview.pinauthen.PinAuthenActivity;
import th.co.thiensurat.ecatalog.utils.AnimateButton;
import th.co.thiensurat.ecatalog.utils.ChangeTintColor;
import th.co.thiensurat.ecatalog.utils.Constance;
import th.co.thiensurat.ecatalog.utils.CustomDialog;
import th.co.thiensurat.ecatalog.utils.MyApplication;

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
                getUsernamePassword(username.getText().toString(), password.getText().toString());

            }
        };
    }

    private void getUsernamePassword(String username, String password) {
        ItemAuth item = new ItemAuth();
        item.setUsername(username);
        item.setPassword(password);
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
        try {
            String token = FirebaseInstanceId.getInstance().getToken();
            Log.e("token", token);
            getPresenter().senTokenToServer(MyApplication.getInstance().getPrefManager().getPreferrence(Constance.KEY_AGENTID), token);
        } catch (Exception e) {
            Log.e("save token", e.getMessage());
        }
        MyApplication.getInstance().getPrefManager().setPreferrence(Constance.KEY_AUTH, "true");
        nextPage();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)){
            return false;
        }
        return true;
    }

    private void loginSession() {
        try {
            if (MyApplication.getInstance().getPrefManager().getPreferrence(Constance.KEY_AUTH).equals("true")) {
                nextPage();
            } else {
                if (!MyApplication.getInstance().getPrefManager().getPreferrence(Constance.KEY_PIN).isEmpty()) {
                    startActivityForResult(new Intent(AuthActivity.this, PinAuthenActivity.class), Constance.REQUEST_PIN_AUTHEN);
                }
            }
        } catch (Exception ex) {
            Log.e("session exception", ex.getMessage());
        }
    }

    private void nextPage() {
        //startActivity(new Intent(getApplicationContext(), MainActivity.class));
        setResult(RESULT_OK);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == Constance.REQUEST_SETTINGS) {
                initialize();
            }

            if (requestCode == Constance.REQUEST_PIN_AUTHEN) {
                nextPage();
            }
        } else {
            reload();
            initialize();
        }
    }

    private View.OnClickListener onForget() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewForgetPassword.startAnimation(new AnimateButton().animbutton());
                startActivityForResult(new Intent(AuthActivity.this, ForgetPasswordActivity.class), Constance.REQUEST_FORGET);
            }
        };
    }

    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }
}
