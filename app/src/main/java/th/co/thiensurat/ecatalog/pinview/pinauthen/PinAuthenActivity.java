package th.co.thiensurat.ecatalog.pinview.pinauthen;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import th.co.thiensurat.ecatalog.MainActivity;
import th.co.thiensurat.ecatalog.R;
import th.co.thiensurat.ecatalog.api.result.ItemAuth;
import th.co.thiensurat.ecatalog.base.BaseMvpActivity;
import th.co.thiensurat.ecatalog.utils.Constance;
import th.co.thiensurat.ecatalog.utils.CustomDialog;
import th.co.thiensurat.ecatalog.utils.MyApplication;

public class PinAuthenActivity extends BaseMvpActivity<PinAuthenInterface.Presenter> implements PinAuthenInterface.View {

    private CustomDialog customDialog;
    private List<ItemAuth> itemAuthList = new ArrayList<ItemAuth>();

    @Override
    public PinAuthenInterface.Presenter createPresenter() {
        return PinAuthenPresenter.create();
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_pin_authen;
    }

    @Override
    public void setupInstance() {
        customDialog = new CustomDialog(this);
    }

    @Override
    public void setupView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setUpPin();
    }

    @Override
    public void initialize() {

    }

    @BindView(R.id.profile_name) TextView textViewName;
    @BindView(R.id.pin_lock_view) PinLockView pinLockView;
    @BindView(R.id.profile_image) ImageView imageViewProfile;
    @BindView(R.id.indicator_dots) IndicatorDots indicatorDots;
    @Override
    public void bindView() {
        ButterKnife.bind(this);
    }

    private void setUpPin() {
        pinLockView.attachIndicatorDots(indicatorDots);
        pinLockView.setPinLockListener(pinListener);
        pinLockView.setPinLength(4);
        pinLockView.setTextColor(ContextCompat.getColor(this, R.color.white));
        indicatorDots.setIndicatorType(IndicatorDots.IndicatorType.FILL_WITH_ANIMATION);
    }

    private PinLockListener pinListener = new PinLockListener() {
        @Override
        public void onComplete(String pin) {
            Log.e("PinActivity", "Pin complete: " + pin);
            try {
                if (MyApplication.getInstance().getPrefManager().getPreferrence(Constance.KEY_PIN).equals(pin)) {
                    ItemAuth item = new ItemAuth();
                    item.setUsername(MyApplication.getInstance().getPrefManager().getPreferrence(Constance.KEY_AGENTID));
                    item.setPassword(MyApplication.getInstance().getPrefManager().getPreferrence(Constance.KEY_PASSWORD));
                    itemAuthList.add(item);
                    getPresenter().auth(itemAuthList);
                } else {
                    pinLockView.resetPinLockView();
                    textViewName.setText("รหัส PIN ไม่ถูกต้อง");
                }
            } catch (Exception e) {
                Log.e("pin", e.getMessage());
            }
        }

        @Override
        public void onEmpty() {
            Log.e("PinActivity", "Pin empty");
        }

        @Override
        public void onPinChange(int pinLength, String intermediatePin) {
            Log.e("PinActivity", "Pin changed, new length " + pinLength + " with intermediate pin " + intermediatePin);
        }
    };

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
        MyApplication.getInstance().getPrefManager().setPreferrence(Constance.KEY_AUTH, "true");
        //startActivity(new Intent(PinAuthenActivity.this, MainActivity.class));
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)){
            return false;
        }
        return true;
    }
}
