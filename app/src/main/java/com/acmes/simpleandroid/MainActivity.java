package com.acmes.simpleandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import dagger.Component;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    @Override
    public void onClick(final View view) {
        Consumer<Object> consumer = new Consumer<Object>() {

            @Override
            public void accept(Object o) throws Exception {
                Log.v(TAG, "accept -> " + o);
            }
        };


        Observer<Object> observer = new Observer<Object>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.v(TAG, "onSubscribe");
            }

            @Override
            public void onNext(@NonNull Object s) {
                Log.v(TAG, "onNext -> " + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.v(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.v(TAG, "onComplete");
            }
        };

        Observable<String> observable = Observable.just("1", "2");
        observable.subscribe(consumer);
//        observable.subscribe(observer);


        Flowable<String> flowable = Flowable.just("a","b");
        flowable.subscribe(consumer);


        Single<String> single = Single.just("Single");
        single.subscribe(consumer);

        Maybe<String> maybe = Maybe.just("Maybe");
        maybe.subscribe(consumer);

        Completable completable = Completable.complete();
        completable.doOnSubscribe(consumer);

        Observable<String> testObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {

            }
        });

        testObservable = Observable.create( e -> {});

    }
}
