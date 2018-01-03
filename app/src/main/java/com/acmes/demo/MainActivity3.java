package com.acmes.demo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.acmes.ethome.R;
import com.acmes.demo.simpleandroid.AcmeSAPI;
import com.acmes.demo.simpleandroid.DAcmeS;
import com.acmes.simpleandroid.imp.Square.RetrofitSimpleModel;
import com.acmes.simpleandroid.mvc.SimpleActivity;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by fishyu on 2017/9/7.
 */

public class MainActivity3 extends SimpleActivity<RetrofitSimpleModel<AcmeSAPI>> implements View.OnClickListener {


    @Override
    protected RetrofitSimpleModel<AcmeSAPI> createMode() {
        return new RetrofitSimpleModel(DemoApplication.getInstance().getRetrofit(), AcmeSAPI.class);
    }

    @BindView(R.id.textview)
    TextView mTextView;

    @BindView(R.id.imageview)
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @OnClick(R.id.textview)
    @Override
    public void onClick(final View view) {
//        getModel().performRequestRetrofit(null, getModel().getAPI().acmesRxObservable());
    }


    void updateView(DAcmeS data) {
        Log.v(TAG, "updateView");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(data.name);
        stringBuilder.append("\n\n");

        stringBuilder.append(data.we);
        stringBuilder.append("\n\n");

        stringBuilder.append(data.about);
        stringBuilder.append("\n\n");

        stringBuilder.append(data.version);
        stringBuilder.append("\n\n");

        mTextView.setText(stringBuilder.toString());

        DemoApplication.getInstance().getPicasso()
                .load(data.logo)
                .fit()
                .centerCrop()
                .into(mImageView);
    }

}
