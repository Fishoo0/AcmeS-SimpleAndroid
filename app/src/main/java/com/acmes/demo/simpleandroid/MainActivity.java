package com.acmes.demo.simpleandroid;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.acmes.demo.R;
import com.acmes.simpleandroid.SimpleActivity;
import com.google.gson.Gson;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends SimpleActivity implements View.OnClickListener {

    @BindView(R.id.textview)
    TextView mTextView;

    @BindView(R.id.imageview)
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

    }


    @OnClick(R.id.textview)
    @Override
    public void onClick(final View view) {
//        Consumer<Object> consumer = new Consumer<Object>() {
//
//            @Override
//            public void accept(Object o) throws Exception {
//                Log.v(TAG, "accept -> " + o);
//            }
//        };
//
//
//        Observer<Object> observer = new Observer<Object>() {
//
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//                Log.v(TAG, "onSubscribe");
//            }
//
//            @Override
//            public void onNext(@NonNull Object s) {
//                Log.v(TAG, "onNext -> " + s);
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                Log.v(TAG, "onError");
//            }
//
//            @Override
//            public void onComplete() {
//                Log.v(TAG, "onComplete");
//            }
//        };
//
//        Observable<String> observable = Observable.just("1", "2");
//        observable.subscribe(consumer);
////        observable.subscribe(observer);
//
//
//        Flowable<String> flowable = Flowable.just("a","b");
//        flowable.subscribe(consumer);
//
//
//        Single<String> single = Single.just("Single");
//        single.subscribe(consumer);
//
//        Maybe<String> maybe = Maybe.just("Maybe");
//        maybe.subscribe(consumer);
//
//        Completable completable = Completable.complete();
//        completable.doOnSubscribe(consumer);
//
//        Optional ss;
//
//        Future<String> future;
//
//
//        Observable<String> test = Observable.create(new ObservableOnSubscribe<String>() {
//
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
//                try {
//
//
//
//                } catch (Throwable t) {
//
//                }
//
//            }
//        });


        testRetrofit();


    }


    Picasso picasso;
    AcmesService acmesService;


    private void initNet() {
        if (picasso != null) {
            return;
        }

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("OKHttp", message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        picasso = new Picasso.Builder(this)
                .downloader(new OkHttp3Downloader(okHttpClient))
                .memoryCache(new LruCache(getApplicationContext()))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(okHttpClient)
                .baseUrl("http://45.77.47.182/")
                .build();


        acmesService = retrofit.create(AcmesService.class);

    }


    private void testRetrofit() {
        initNet();

        mTextView.setText("Loading ...");

        Call<DAcmeS> call = acmesService.acmes();
        call.enqueue(new Callback<DAcmeS>() {
            @Override
            public void onResponse(Call<DAcmeS> call, Response<DAcmeS> response) {
                Log.v(TAG, "onResponse");

                DAcmeS data = response.body();

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

                picasso.load(data.logo)
                        .fit()
                        .centerCrop()
                        .into(mImageView);

            }

            @Override
            public void onFailure(Call<DAcmeS> call, Throwable t) {
                Log.e(TAG, "onFailure");

                mTextView.setText("Failed!");

            }
        });
    }
}
