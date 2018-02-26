package com.acmes.acmes.mode.request;

import com.acmes.acmes.AcmesAPI;
import com.acmes.acmes.mode.bean.DCategories;

import io.reactivex.Observable;

/**
 * Created by fishyu on 2018/2/5.
 */

public class CategoriesRequest extends AcmesRequest<DCategories> {

    public CategoriesRequest() {

    }

    @Override
    public Observable<DCategories> callAPI(AcmesAPI api) {
        return api.categories(this);
    }
}
