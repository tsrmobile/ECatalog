package th.co.thiensurat.ecatalog.social;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import th.co.thiensurat.ecatalog.R;
import th.co.thiensurat.ecatalog.utils.CustomDialog;

public class SocialActivity extends AppCompatActivity {

    private TextView textViewTitle;
    private CustomDialog customDialog;
    private String url = "http://app.thiensurat.co.th/TSROnlineMarketing/CreateLink";

    @BindView(R.id.toolbar ) Toolbar toolbar;
    @BindView(R.id.webView) WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);

        ButterKnife.bind(this);
        customDialog = new CustomDialog(SocialActivity.this);
        customDialog.dialogLoading();
        setToolbar();
        setWebview();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (webView.canGoBack()) {
                webView.goBack();
            } else {
                setResult(RESULT_CANCELED);
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void setToolbar() {
        toolbar.setTitle("");
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp));
        textViewTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        textViewTitle.setText("SOCIAL LINK");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setWebview() {
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }

            public void onPageFinished(WebView view, String url) {
                customDialog.dialogDimiss();
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        setResult(RESULT_CANCELED);
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
