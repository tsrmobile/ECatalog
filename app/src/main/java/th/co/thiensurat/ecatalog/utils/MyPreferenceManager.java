package th.co.thiensurat.ecatalog.utils;

import android.content.Context;
import android.content.SharedPreferences;

import th.co.thiensurat.ecatalog.auth.item.AuthenItem;
import th.co.thiensurat.ecatalog.auth.item.AuthenItemGroup;


/**
 * Created by teera-s on 5/19/2016 AD.
 */
public class MyPreferenceManager {
    private String TAG = MyPreferenceManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    private static final int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "APP";

    // Constructor
    public MyPreferenceManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setPreferrence(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public String getPreferrence(String key) {
        return pref.getString(key, null);
    }

    public boolean getPreferrenceBoolean(String key) {
        return pref.getBoolean(key, false);
    }

    public void setProfile(AuthenItemGroup authenItemGroup) {
        for (AuthenItem item : authenItemGroup.getData()) {
            editor.putString(Constance.KEY_AGENTID, item.getAgentid());
            editor.putString(Constance.KEY_PASSWORD, item.getPassword());
            editor.putString(Constance.KEY_REFNO, item.getRefno());
            editor.putString(Constance.KEY_IDCARD, item.getIdcard());
            editor.putString(Constance.KEY_TITLE, item.getTitle());
            editor.putString(Constance.KEY_FIRSTNAME, item.getFirstname());
            editor.putString(Constance.KEY_LASTNAME, item.getLastname());
            editor.putString(Constance.KEY_ADDRESS, item.getAddress());
            editor.putString(Constance.KEY_PROVINCE, item.getProvince());
            editor.putString(Constance.KEY_DISTRICT, item.getDistrict());
            editor.putString(Constance.KEY_SUBDISTRICT, item.getSubdistrict());
            editor.putString(Constance.KEY_ZIPCODE, item.getZipcode());
            editor.putString(Constance.KEY_EMAIL, item.getEmail());
            editor.putString(Constance.KEY_PHONE, item.getPhone());
            editor.putString(Constance.KEY_MOBILE, item.getMobile());
            editor.putString(Constance.KEY_LINE, item.getLine());
            editor.putString(Constance.KEY_FACEBOOK, item.getFacebook());
        }
        editor.commit();
    }

    public AuthenItem getProfile() {
        AuthenItem item = new AuthenItem()
                .setAgentid(pref.getString(Constance.KEY_AGENTID, null))
                .setPassword(pref.getString(Constance.KEY_PASSWORD, null))
                .setRefno(pref.getString(Constance.KEY_REFNO, null))
                .setIdcard(pref.getString(Constance.KEY_IDCARD, null))
                .setTitle(pref.getString(Constance.KEY_TITLE, null))
                .setFirstname(pref.getString(Constance.KEY_FIRSTNAME, null))
                .setLastname(pref.getString(Constance.KEY_LASTNAME, null))
                .setAddress(pref.getString(Constance.KEY_ADDRESS, null))
                .setProvince(pref.getString(Constance.KEY_PROVINCE, null))
                .setDistrict(pref.getString(Constance.KEY_DISTRICT, null))
                .setSubdistrict(pref.getString(Constance.KEY_SUBDISTRICT, null))
                .setZipcode(pref.getString(Constance.KEY_ZIPCODE, null))
                .setEmail(pref.getString(Constance.KEY_EMAIL, null))
                .setPhone(pref.getString(Constance.KEY_PHONE, null))
                .setMobile(pref.getString(Constance.KEY_MOBILE, null))
                .setLine(pref.getString(Constance.KEY_LINE, null))
                .setFacebook(pref.getString(Constance.KEY_FACEBOOK, null));
        return item;
    }

    public void clear() {
        editor.clear();
        editor.commit();
    }
}
