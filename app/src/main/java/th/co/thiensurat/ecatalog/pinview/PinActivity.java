package th.co.thiensurat.ecatalog.pinview;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;

import butterknife.BindView;
import butterknife.ButterKnife;
import th.co.thiensurat.ecatalog.R;
import th.co.thiensurat.ecatalog.utils.Constance;
import th.co.thiensurat.ecatalog.utils.MyApplication;

public class PinActivity extends AppCompatActivity {

    private String pinTemp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        bindView();
        setUpPin();
    }

    @BindView(R.id.profile_name) TextView textViewName;
    @BindView(R.id.pin_lock_view) PinLockView pinLockView;
    @BindView(R.id.profile_image) ImageView imageViewProfile;
    @BindView(R.id.indicator_dots) IndicatorDots indicatorDots;
    private void bindView() {
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
                if (pinTemp.isEmpty()) {
                    pinTemp = pin;
                    pinLockView.resetPinLockView();
                    textViewName.setText("ยืนยันรหัส PIN");
                } else {
                    if (pinTemp.equals(pin)) {
                        MyApplication.getInstance().getPrefManager().setPreferrence(Constance.KEY_PIN, pin);
                        finish();
                    } else {
                        pinLockView.resetPinLockView();
                        textViewName.setText("รหัส PIN ไม่ถูกต้อง");

                        new CountDownTimer(1500, 1000) {

                            public void onTick(long millisUntilFinished) {
                            }

                            public void onFinish() {
                                pinTemp = "";
                                textViewName.setText("ใส่ PIN 4 หลัก");
                            }

                        }.start();
                    }
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
}
