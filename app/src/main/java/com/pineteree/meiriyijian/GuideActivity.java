package com.pineteree.meiriyijian;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.pineteree.meiriyijian.main.MainActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class GuideActivity extends AppCompatActivity {
    TextView mTextView;
    private int mTime = 5;
    Disposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        mTextView = (TextView) findViewById(R.id.tv_guide_time);
        mTextView.setText(mTime + "s");

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                finish();
            }
        });

        Observable.timer(1, TimeUnit.SECONDS)
                .repeat()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        mTime--;
                        mTextView.setText(mTime + "s");
                        if (mTime == 0) {
                            startActivity(new Intent(GuideActivity.this, MainActivity.class));
                            finish();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        //        Observable.timer(1, TimeUnit.SECONDS)
        //                .repeat()
        //                .observeOn(AndroidSchedulers.mainThread())
        //                .subscribe(new Consumer<Long>() {
        //                    @Override
        //                    public void accept(Long aLong) throws Exception {
        //                        mTime--;
        //                        mTextView.setText(mTime + "s");
        //                        if (mTime == 0) {
        //                            startActivity(new Intent(GuideActivity.this, MainActivity
        // .class));
        //                            finish();
        //                        }
        //                    }
        //                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDisposable.dispose();
    }
}
