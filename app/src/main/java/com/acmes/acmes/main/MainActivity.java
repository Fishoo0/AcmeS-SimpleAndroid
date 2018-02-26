package com.acmes.acmes.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.acmes.acmes.AcmesActivity;
import com.acmes.acmes.AcmesMode;
import com.acmes.acmes.R;
import com.acmes.acmes.login.AcmesDispatcherActivity;
import com.acmes.acmes.mode.bean.DCategories;
import com.acmes.acmes.mode.request.CategoriesRequest;
import com.acmes.simpleandroid.mvc.model.SimpleModel;
import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;
import com.rom4ek.arcnavigationview.ArcNavigationView;

import butterknife.BindView;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

/**
 * Created by fishyu on 2018/1/2.
 */

public class MainActivity extends AcmesActivity implements SwipeRefreshLayout.OnRefreshListener, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;

    MainActivityAdapter mAdapter;

    @BindView(R.id.navigation_view)
    ArcNavigationView mNavigationView;

    @Override
    protected SimpleModel createModel() {
        return new AcmesMode();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mNavigationView.setNavigationItemSelectedListener(this);
        getSwipeRefreshLayout().setOnRefreshListener(this);

        mRecycleView.setItemAnimator(new SlideInLeftAnimator());

        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MainActivityAdapter();
        mRecycleView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (mAdapter.getItemCount() <= 0) {
            onRefresh();
        }
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
    public void onResponse(SimpleRequest request, SimpleResponse response) {
        super.onResponse(request, response);
        if (response instanceof DCategories) {
            mAdapter.setData((DCategories) response);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRefresh() {
        getModel().performRequest(new CategoriesRequest());
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (onOptionsItemSelected(item)) {
            return true;
        }
        return false;
    }
}