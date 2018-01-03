package com.acmes.ethome.login;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.acmes.ethome.ETHomeApplication;
import com.acmes.ethome.mode.bean.DUser;
import com.google.gson.Gson;

/**
 * Created by fishyu on 2018/1/2.
 */

public class SharedPrefAccountManager implements IAccountManager {

    private static SharedPrefAccountManager mInstance = new SharedPrefAccountManager(ETHomeApplication.getInstance());

    private static final String ACCOUNT = "Account";
    private static final String KEY_ACCOUNT = "key_account";

    private static final Gson GSON = new Gson();
    private SharedPreferences mSharedPreferences;

    private DUser mCurrentUser;

    public SharedPrefAccountManager(Application context) {
        mSharedPreferences = context.getSharedPreferences(ACCOUNT, Context.MODE_PRIVATE);
        String user = mSharedPreferences.getString(KEY_ACCOUNT, "");
        if (!TextUtils.isEmpty(user)) {
            mCurrentUser = GSON.fromJson(user, DUser.class);
        }
    }

    public static final SharedPrefAccountManager getInstance() {
        return mInstance;
    }

    @Override
    public DUser getCurrentUser() {
        return mCurrentUser;
    }

    @Override
    public void addUser(DUser user) {
        String userString = GSON.toJson(user);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(KEY_ACCOUNT, userString);
        editor.commit();
        mCurrentUser = user;
    }

    @Override
    public void removeUser(DUser user) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.remove(KEY_ACCOUNT);
        editor.commit();
        mCurrentUser = null;
    }


}
