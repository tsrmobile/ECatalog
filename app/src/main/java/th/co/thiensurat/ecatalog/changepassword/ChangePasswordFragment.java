package th.co.thiensurat.ecatalog.changepassword;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import th.co.thiensurat.ecatalog.MainActivity;
import th.co.thiensurat.ecatalog.R;
import th.co.thiensurat.ecatalog.base.BaseMvpFragment;
import th.co.thiensurat.ecatalog.utils.AnimateButton;
import th.co.thiensurat.ecatalog.utils.ChangeTintColor;
import th.co.thiensurat.ecatalog.utils.Constance;
import th.co.thiensurat.ecatalog.utils.CustomDialog;
import th.co.thiensurat.ecatalog.utils.MyApplication;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePasswordFragment extends BaseMvpFragment<ChangePasswordInterface.Presenter> implements ChangePasswordInterface.View {

    private TextView textViewTitle;
    private CustomDialog customDialog;
    private ChangeTintColor changeTintColor;

    private String id;
    private String newPassword;
    private String oldPassword;
    private String StringPlattern = "[a-zA-Z0-9.? ]*";

    public ChangePasswordFragment() {
        // Required empty public constructor
    }

    public static ChangePasswordFragment getInstance() {
        return new ChangePasswordFragment();
    }

    @Override
    public ChangePasswordInterface.Presenter createPresenter() {
        return ChangePasswordPresenter.create();
    }

    @Override
    public int getLayoutView() {
        return R.layout.fragment_change_password;
    }

    @BindView(R.id.edt_old_password) EditText editTextOldPassword;
    @BindView(R.id.edt_new_password) EditText editTextNewPassword;
    @BindView(R.id.edt_confirm_password) EditText editTextConfrimPassword;
    @BindView(R.id.button_change) Button buttonChangePassword;
    @Override
    public void bindView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public void setupInstance() {
        customDialog = new CustomDialog(getActivity());
        changeTintColor = new ChangeTintColor(getActivity());
    }

    @Override
    public void setupView() {
        ((MainActivity)getActivity()).setTitle("เปลี่ยนรหัสผ่าน");
        buttonChangePassword.setOnClickListener( onChange() );
        editTextConfrimPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    if (editTextNewPassword.getText().toString().equals(editTextConfrimPassword.getText().toString())) {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(editTextConfrimPassword.getWindowToken(), 0);
                        newPassword = editTextConfrimPassword.getText().toString();
                        buttonChangePassword.performClick();
                        return true;
                    } else {
                        onFail("การยืนยันรหัสผ่านไม่ถูกต้อง");
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void initialize() {
        try {
            id = MyApplication.getInstance().getPrefManager().getPreferrence(Constance.KEY_AGENTID);
        } catch (Exception e) {

        }
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
        clearNewFrom();
    }

    @Override
    public void onSuccess(String success) {
        customDialog.dialogSuccess(success);
        clearFrom();
    }

    private View.OnClickListener onChange() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonChangePassword.startAnimation(new AnimateButton().animbutton());
                if (editTextNewPassword.getText().toString().length() < 8) {
                    onFail("กรุณาระบุรหัสผ่านใหม่\nอย่างน้อย 8 ตัว");
                } else if (!editTextNewPassword.getText().toString().matches(StringPlattern) && !editTextConfrimPassword.getText().toString().matches(StringPlattern)) {
                    onFail("กรุณาระบุเฉพาะ\nตัวอักษรภาษาอังกฤษและตัวเลขเท่านั้น");
                } else if (editTextNewPassword.getText().toString().equals(editTextConfrimPassword.getText().toString())) {
                    newPassword = editTextConfrimPassword.getText().toString();
                    oldPassword = editTextOldPassword.getText().toString();
                    getPresenter().requestChangePassword("change", id, oldPassword, newPassword);
                } else {
                    onFail("การยืนยันรหัสผ่านไม่ถูกต้อง");
                }
            }
        };
    }

    private void clearFrom() {
        editTextOldPassword.setText("");
        editTextNewPassword.setText("");
        editTextConfrimPassword.setText("");
    }

    private void clearNewFrom() {
        editTextNewPassword.setText("");
        editTextConfrimPassword.setText("");
    }
}
