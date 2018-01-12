package com.acmes.acmes;

import android.util.Log;
import android.widget.Toast;

import com.acmes.simpleandroid.mvc.SimpleActivity;
import com.acmes.simpleandroid.mvc.model.SimpleRequest;

/**
 * Created by fishyu on 2018/1/2.
 */

public abstract class AcmesActivity<T extends AcmesMode> extends SimpleActivity<T> {


    @Override
    public void onFailure(SimpleRequest request, Throwable exception) {
        super.onFailure(request, exception);
        Log.e(TAG, exception.getMessage());
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
    }
}

