package th.co.thiensurat.ecatalog;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.thefinestartist.finestwebview.FinestWebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import th.co.thiensurat.ecatalog.auth.AuthActivity;
import th.co.thiensurat.ecatalog.base.BaseMvpActivity;
import th.co.thiensurat.ecatalog.catalog.CatalogFragment;
import th.co.thiensurat.ecatalog.changepassword.ChangePasswordFragment;
import th.co.thiensurat.ecatalog.network.ConnectionDetector;
import th.co.thiensurat.ecatalog.pinview.PinActivity;
import th.co.thiensurat.ecatalog.pinview.pinauthen.PinAuthenActivity;
import th.co.thiensurat.ecatalog.profile.ProfileActivity;
import th.co.thiensurat.ecatalog.registration.RegistrationFragment;
import th.co.thiensurat.ecatalog.registration.general.GeneralFragment;
import th.co.thiensurat.ecatalog.reports.ReportFragment;
import th.co.thiensurat.ecatalog.share.ShareFragment;
import th.co.thiensurat.ecatalog.social.SocialActivity;
import th.co.thiensurat.ecatalog.utils.Constance;
import th.co.thiensurat.ecatalog.utils.CustomDialog;
import th.co.thiensurat.ecatalog.utils.MyApplication;

public class MainActivity extends BaseMvpActivity<MainInterface.Presenter> implements MainInterface.View {

    private TextView textViewName, textViewTitle;
    private ImageView imageViewProfile;
    private boolean clickBackAain;
    private MenuItem menuItemClicked;
    private CustomDialog customDialog;

    @Override
    public MainInterface.Presenter createPresenter() {
        return MainPresenter.create();
    }

    @BindView(R.id.toolbar) Toolbar toolbar;
    @Override
    public int getLayoutView() {
        return R.layout.activity_main;
    }

    @BindView(R.id.container) FrameLayout containner;
    @BindView(R.id.drawer) DrawerLayout drawerLayout;
    @BindView(R.id.text_version) TextView textVersion;
    @BindView(R.id.navigation_view) NavigationView navigationView;
    @Override
    public void bindView() {
        ButterKnife.bind(this);
    }

    @Override
    public void setupInstance() {
        customDialog = new CustomDialog(this);
    }

    @Override
    public void setupView() {
        setToolbar();
        setMenu();
    }

    @Override
    public void initialize() {
        boolean isNetworkAvailable = ConnectionDetector.isConnectingToInternet(MainActivity.this);
        if (!isNetworkAvailable) {
            customDialog.dialogNetworkError();
        } else {
            try {
                if (MyApplication.getInstance().getPrefManager().getPreferrence(Constance.KEY_AUTH).equals("false")) {
                    if (!MyApplication.getInstance().getPrefManager().getPreferrence(Constance.KEY_PIN).isEmpty()) {
                        startActivityForResult(new Intent(MainActivity.this, PinAuthenActivity.class), Constance.REQUEST_PIN_AUTHEN);
                    } else {
                        startActivityForResult(new Intent(MainActivity.this, AuthActivity.class), Constance.REQUEST_AUTHEN);
                    }
                } else {
                    loadHomePage();
                }
            } catch (Exception ex) {
                Log.e("session exception", ex.getMessage());
                startActivityForResult(new Intent(MainActivity.this, AuthActivity.class), Constance.REQUEST_AUTHEN);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void restoreView(Bundle savedInstanceState) {
        super.restoreView(savedInstanceState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constance.REQUEST_SETTINGS) {
            initialize();
        } else if (requestCode == Constance.REQUEST_AUTHEN || requestCode == Constance.REQUEST_PIN_AUTHEN) {
            reload();
            loadHomePage();
        }
    }

    private void loadHomePage() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, CatalogFragment.getInstance()).addToBackStack(null).commit();
    }

    private void setToolbar() {
        toolbar.setTitle("");
        textViewTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        textViewTitle.setText(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);
    }

    private void setHeaderMenu() {
        LayoutInflater inflater = getLayoutInflater();
        View header = navigationView.inflateHeaderView(R.layout.menu_header);
        textViewName = (TextView) header.findViewById(R.id.name);
        imageViewProfile = (ImageView) header.findViewById(R.id.image_profile);

        textVersion.setText("App v. " + appVersion() + "");

        String title = MyApplication.getInstance().getPrefManager().getPreferrence(Constance.KEY_TITLE);
        String firstname = MyApplication.getInstance().getPrefManager().getPreferrence(Constance.KEY_FIRSTNAME);
        String lastname = MyApplication.getInstance().getPrefManager().getPreferrence(Constance.KEY_LASTNAME);

        String fullname = title + "" + firstname + " " + lastname;
        textViewName.setText(fullname);

        Log.e("Agent name", fullname);
    }

    private void setMenu() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on Item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItemClicked = menuItem;
                drawerLayout.closeDrawers();
                return true;
            }
        });

        setHeaderMenu();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        navigationView.inflateMenu(R.menu.main_menu);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer, R.string.closeDrawer){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if(menuItemClicked != null)
                    handleSelectedMenu(menuItemClicked);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                menuItemClicked = null;
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void handleSelectedMenu(MenuItem menuItem) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.container);
        switch (menuItem.getItemId()){
            case R.id.memu_home :
                if (currentFragment instanceof CatalogFragment) {
                    drawerLayout.closeDrawers();
                } else {
                    setTitle(getResources().getString(R.string.app_name));
                    transaction.replace(R.id.container, CatalogFragment.getInstance(), "CatalogFragment").addToBackStack(null).commit();
                }
                break;
            case R.id.menu_report :
                if (currentFragment instanceof ReportFragment) {
                    drawerLayout.closeDrawers();
                } else {
                    transaction.replace(R.id.container, ReportFragment.getInstance(), "ReportFragment").addToBackStack(null).commit();
                }
                break;
            case R.id.menu_register :
                if (currentFragment instanceof GeneralFragment) {
                    drawerLayout.closeDrawers();
                } else {
                    transaction.replace(R.id.container, RegistrationFragment.getInstance(), "RegistrationFragment").addToBackStack(null).commit();
                }
                break;
            case R.id.menu_share :
                if (currentFragment instanceof ShareFragment) {
                    drawerLayout.closeDrawers();
                } else {
                    transaction.replace(R.id.container, ShareFragment.getInstance(), "ShareFragment").addToBackStack(null).commit();
                }
                break;
            case R.id.menu_social :
                //startActivityForResult(new Intent(getApplicationContext(), SocialActivity.class), Constance.REQUEST_SOCIAL);
                socialLink();
                break;
            case R.id.menu_profile :
                startActivityForResult(new Intent(getApplicationContext(), ProfileActivity.class), Constance.REQUEST_PROFILE);
                break;
            case R.id.menu_change_password :
                if (currentFragment instanceof ChangePasswordFragment) {
                    drawerLayout.closeDrawers();
                } else {
                    transaction.replace(R.id.container, ChangePasswordFragment.getInstance(), "ChangePasswordFragment").addToBackStack(null).commit();
                }
                break;
            case R.id.menu_pin :
                startActivityForResult(new Intent(MainActivity.this, PinActivity.class), Constance.REQUEST_SET_PIN);
                break;
            case R.id.menu_logout :
                //MyApplication.getInstance().getPrefManager().clear();
                MyApplication.getInstance().getPrefManager().setPreferrence(Constance.KEY_AUTH, "false");
                startActivityForResult(new Intent(MainActivity.this, AuthActivity.class), Constance.REQUEST_AUTHEN);
                //finish();
                break;
            default: break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)){
            if (clickBackAain) {
                finish();
                return true;
            }
            this.clickBackAain = true;
            Toast.makeText(MainActivity.this, "กด BACK อีกครั้งเพื่อออกจากแอพ", Toast.LENGTH_LONG).show();

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

    public void setTitle(String title) {
        textViewTitle.setText(title);
    }

    private String appVersion() {
        try {
            PackageInfo pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            return pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    private void socialLink() {
        String url = "http://app.thiensurat.co.th/TSROnlineMarketing/CreateLink";
        new FinestWebView.Builder(this).theme(R.style.FinestWebViewTheme)
                .titleDefault("SOCIAL LINK")
                .showUrl(false)
                .statusBarColorRes(R.color.colorPrimaryDark)
                .toolbarColorRes(R.color.colorPrimary)
                .titleColorRes(R.color.finestWhite)
                .urlColorRes(R.color.colorAccent)
                .iconDefaultColorRes(R.color.finestWhite)
                .progressBarColorRes(R.color.finestWhite)
                .stringResCopiedToClipboard(R.string.copied_to_clipboard)
                .stringResCopiedToClipboard(R.string.copied_to_clipboard)
                .stringResCopiedToClipboard(R.string.copied_to_clipboard)
                .showSwipeRefreshLayout(true)
                .swipeRefreshColorRes(R.color.colorPrimaryDark)
                .menuSelector(R.drawable.selector_light_theme)
                .menuTextGravity(Gravity.CENTER)
                .menuTextPaddingRightRes(R.dimen.defaultMenuTextPaddingLeft)
                .dividerHeight(0)
                .gradientDivider(false)
                .setCustomAnimations(R.anim.slide_up, R.anim.hold, R.anim.hold, R.anim.slide_down)
                .show(url);
    }
}
