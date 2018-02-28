package com.acmes.acmes.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import com.acmes.acmes.AcmesActivity;
import com.acmes.acmes.R;
import com.acmes.acmes.login.AcmesDispatcherActivity;
import com.rom4ek.arcnavigationview.ArcNavigationView;

import butterknife.BindView;

/**
 * Created by fishyu on 2018/1/2.
 */

public class MainActivity extends AcmesActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.navigation_view)
    ArcNavigationView mNavigationView;

    @BindView(R.id.main_content_view)
    MainActivityContentView mMainContentView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        //register view controller
        getViewControllerHelper().registerViewController(mMainContentView, savedInstanceState);

        mNavigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                AcmesDispatcherActivity.jumpToThis(this, AcmesDispatcherActivity.CMD_LOGOUT);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (onOptionsItemSelected(item)) {
            return true;
        }
        return false;
    }
}