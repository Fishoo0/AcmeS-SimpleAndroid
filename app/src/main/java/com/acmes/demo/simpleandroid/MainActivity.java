package com.acmes.demo.simpleandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.acmes.demo.R;
import com.acmes.simpleandroid.SimpleActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends SimpleActivity implements View.OnClickListener {

    @BindView(R.id.textview)
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


    }
}
