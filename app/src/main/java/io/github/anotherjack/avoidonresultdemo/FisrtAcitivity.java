package io.github.anotherjack.avoidonresultdemo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.tbruyelle.rxpermissions2.RxPermissions;

import communicate.AvoidOnResult;

import static io.github.anotherjack.avoidonresultdemo.EventCodeConstants.EVENT_CODE_PWS_FIRST_IDENTIFIER;

/**
 * Created by grid on 18/6/18.
 */

public class FisrtAcitivity extends Activity {
    private String Tag = this.getClass().getSimpleName();
    private RxPermissions rxPermissions ;//= new RxPermissions(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        initView();
        //requestPermission();


    }

    private void requestPermission() {
        rxPermissions
                .request(Manifest.permission.CAMERA).subscribe(granted -> {
            if (granted) { // Always true pre-M
                // I can control the camera now
            } else {
                // Oups permission denied
            }
        });

    }

    private void initView() {
        //callback方式
        findViewById(R.id.callback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FisrtAcitivity.this,FetchDataActivity.class);
                new AvoidOnResult(FisrtAcitivity.this).startForResult(intent, new AvoidOnResult.Callback() {
                    // 4.2.2---回调先执行了  https://www.cnblogs.com/gccBlog/p/5754517.html
                    @Override
                    public void onCallBack (String data) {
                        //Log.v(FisrtAcitivity.this.Tag,intent.getStringExtra("text"));
                        Log.v(FisrtAcitivity.this.Tag,data);
                    }

                    @Override
                    public int getEventCode() {
                        return EVENT_CODE_PWS_FIRST_IDENTIFIER;
                    }
                });
            }
        });

/*
        findViewById(R.id.rxjava).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FisrtAcitivity.this,FetchDataActivity.class);
                new AvoidOnResult(FisrtAcitivity.this)
                        .startForResult(intent)
                        .subscribe(new Consumer<ActivityResultInfo>() {
                            @Override
                            public void accept(ActivityResultInfo activityResultInfo) throws Exception {
                                Log.v(Tag, "rxjava is accept");
                            }
                        });
            }
        });
        */

     findViewById(R.id.back).setOnClickListener(v -> {
       finish();
     });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.v(Tag, "normal onActivityResult");
    }
}
