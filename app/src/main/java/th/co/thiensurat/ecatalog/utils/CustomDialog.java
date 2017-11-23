package th.co.thiensurat.ecatalog.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.widget.EditText;

import cn.pedant.SweetAlert.SweetAlertDialog;
import th.co.thiensurat.ecatalog.R;
import th.co.thiensurat.ecatalog.catalog.detail.DetailActivity;

import static th.co.thiensurat.ecatalog.utils.Constance.REQUEST_SETTINGS;

/**
 * Created by teerayut.k on 9/26/2017.
 */

public class CustomDialog {

    private static Context context;
    private static SweetAlertDialog sweetAlertDialog;

    public CustomDialog(Context mcontext) {
        this.context = mcontext;
        sweetAlertDialog = new SweetAlertDialog(context);
    }

    public CustomDialog(final FragmentActivity mcontext) {
        this.context = mcontext;
        sweetAlertDialog = new SweetAlertDialog(context);
    }

    public static void dialogLoading() {
        sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.getProgressHelper().setBarColor(context.getResources().getColor(R.color.colorPrimaryDark));
        sweetAlertDialog.setTitleText("Loading...");
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.show();
    }

    public static void dialogDimiss() {
        if (sweetAlertDialog.isShowing()) {
            sweetAlertDialog.dismiss();
        }
    }

    public static void dialogFail(String fail) {
        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Error")
                .setContentText(fail)
                .showCancelButton(false)
                .setConfirmText("OK")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog dialog) {
                        dialog.dismiss();
                    }
                }).show();
    }

    public static void dialogSuccess(String success) {
        new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Success")
                .setContentText(success)
                .showCancelButton(false)
                .setConfirmText("OK")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog dialog) {
                        dialog.dismiss();
                        if (context instanceof DetailActivity) {
                            ((DetailActivity)context).clearForm();
                        }
                    }
                }).show();
    }

    public static void dialogNetworkError() {
        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("ไม่ได้เชื่อมต่ออินเตอร์เน็ต!")
                .setContentText("กรุณาตั้งค่าการเชื่อมต่อ")
                .showCancelButton(true)
                .setCancelText("ยกเลิก")
                .setConfirmText("ตั้งค่า")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.setClassName("com.android.settings", "com.android.settings.wifi.WifiSettings");
                        ((Activity) context).startActivityForResult(intent, REQUEST_SETTINGS);
                    }
                })
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                        ((Activity) context).finish();
                    }
                })
                .show();
    }
}
