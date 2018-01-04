//package com.acmes.demo.simpleandroid;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.acmes.demo.R;
//import com.acmes.simplebad.SimpleActivity;
//import com.acmes.simplebad.model.SimpleModel;
//import com.google.gson.Gson;
//import com.jakewharton.picasso.OkHttp3Downloader;
//import com.squareup.picasso.LruCache;
//import com.squareup.picasso.Picasso;
//
//import org.reactivestreams.Subscriber;
//import org.reactivestreams.Subscription;
//
//import butterknife.BindView;
//import butterknife.OnClick;
//import io.reactivex.Observable;
//import io.reactivex.ObservableEmitter;
//import io.reactivex.ObservableOnSubscribe;
//import io.reactivex.Observer;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.annotations.NonNull;
//import io.reactivex.disposables.Disposable;
//import io.reactivex.functions.Consumer;
//import io.reactivex.schedulers.Schedulers;
//import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class MainActivity extends Activity implements View.OnClickListener {
//
//    @BindView(R.id.textview)
//    TextView mTextView;
//
//    @BindView(R.id.imageview)
//    ImageView mImageView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//
//    @Override
//    protected SimpleModel createMode() {
//        return null;
//    }
//
//
//    @OnClick(R.id.textview)
//    @Override
//    public void onClick(final View view) {
//
//        testRxJava2Retrofit();
//
//
//    }
//
//
//    Picasso picasso;
//    AcmeSAPI acmesService;
//
//
//    private void initNet() {
//        if (picasso != null) {
//            return;
//        }
//
//        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//            @Override
//            public void log(String message) {
//                Log.d("OKHttp", message);
//            }
//        });
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
//
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(httpLoggingInterceptor)
//                .build();
//
//        picasso = new Picasso.Builder(this)
//                .downloader(new OkHttp3Downloader(okHttpClient))
//                .memoryCache(new LruCache(getApplicationContext()))
//                .build();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create(new Gson()))
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .client(okHttpClient)
//                .baseUrl("http://45.77.47.182/")
//                .build();
//
//
//        acmesService = retrofit.create(AcmeSAPI.class);
//
//    }
//
//
//    private void testRetrofit() {
//        initNet();
//
//        mTextView.setText("Loading ...");
//
//        Call<DAcmeS> call = acmesService.acmes();
//        call.enqueue(new Callback<DAcmeS>() {
//            @Override
//            public void deliveryOnResponse(Call<DAcmeS> call, Response<DAcmeS> response) {
//                Log.d(TAG, "deliveryOnResponse");
//
//                DAcmeS data = response.body();
//
//                StringBuilder stringBuilder = new StringBuilder();
//                stringBuilder.append(data.name);
//                stringBuilder.append("\n\n");
//
//                stringBuilder.append(data.we);
//                stringBuilder.append("\n\n");
//
//                stringBuilder.append(data.about);
//                stringBuilder.append("\n\n");
//
//                stringBuilder.append(data.version);
//                stringBuilder.append("\n\n");
//
//                mTextView.setText(stringBuilder.toString());
//
//                picasso.load(data.logo)
//                        .fit()
//                        .centerCrop()
//                        .into(mImageView);
//
//            }
//
//            @Override
//            public void deliveryOnFailure(Call<DAcmeS> call, Throwable t) {
//                Log.d(TAG, "deliveryOnFailure");
//
//                mTextView.setText("Failed!");
//
//            }
//        });
//    }
//
//
//    void testRxJava2() {
//        Observable<DAcmeS> observable = Observable.create(new ObservableOnSubscribe<DAcmeS>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<DAcmeS> e) throws Exception {
//                Log.e(TAG, "subscribe ");
//
//                for (int i = 0; i < 3; i++) {
//                    Call<DAcmeS> call = acmesService.acmes();
//                    Response<DAcmeS> response = call.execute();
//                    e.onNext(response.body());
//                }
//
//                e.onComplete();
//
//            }
//        })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnNext(new Consumer<DAcmeS>() {
//                    @Override
//                    public void accept(DAcmeS dAcmeS) throws Exception {
//                        Log.e(TAG, "accept " + Thread.currentThread().getName());
//
//                    }
//                });
//
//
//        observable.subscribe(new Observer<DAcmeS>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//                Log.v(TAG, "onSubscribe");
//
//            }
//
//            @Override
//            public void onNext(@NonNull DAcmeS dAcmeS) {
//                Log.v(TAG, "onNext");
//                updateView(dAcmeS);
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                Log.v(TAG, "onError");
//                mTextView.setText("onError -> " + e.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//                Log.v(TAG, "onComplete");
//            }
//        });
//
//
//    }
//
//
//    void testRxJava2Retrofit() {
//        acmesService.acmesRx()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<DAcmeS>() {
//                    @Override
//                    public void onSubscribe(Subscription s) {
//                        Log.v(TAG, "onSubscribe");
//
//                        s.request(10);
//
//                    }
//
//                    @Override
//                    public void onNext(@NonNull DAcmeS dAcmeS) {
//                        Log.v(TAG, "onNext");
//                        updateView(dAcmeS);
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        Log.v(TAG, "onError");
//                        mTextView.setText("onError -> " + e.getMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.v(TAG, "onComplete");
//                    }
//                });
//    }
//
//
//    void testFollowable() {
//    }
//
//
//
//    void updateView(DAcmeS data) {
//        Log.v(TAG, "updateView");
//
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(data.name);
//        stringBuilder.append("\n\n");
//
//        stringBuilder.append(data.we);
//        stringBuilder.append("\n\n");
//
//        stringBuilder.append(data.about);
//        stringBuilder.append("\n\n");
//
//        stringBuilder.append(data.version);
//        stringBuilder.append("\n\n");
//
//        mTextView.setText(stringBuilder.toString());
//
//        picasso.load(data.logo)
//                .fit()
//                .centerCrop()
//                .into(mImageView);
//    }
//
//}
