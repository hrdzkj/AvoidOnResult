package io.github.anotherjack.avoidonresultdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import io.github.anotherjack.avoidonresult.ActivityResultInfo;
import io.github.anotherjack.avoidonresult.AvoidOnResult;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by grid on 18/6/18.
 */

public class FisrtAcitivity extends Activity {
    private String Tag = this.getClass().getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        //callback方式
        findViewById(R.id.callback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AvoidOnResult(FisrtAcitivity.this).startForResult(FetchDataActivity.class, new AvoidOnResult.Callback() {
                    @Override
                    public void onActivityResult(int resultCode, Intent data) {
                        if (resultCode == Activity.RESULT_OK) {
                            String text = data.getStringExtra("text");
                            Log.v(Tag,"callback -> " + text);

                        } else {
                            Log.v(Tag,"callback canceled");
                        }
                    }
                });
            }
        });


        findViewById(R.id.rxjava).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AvoidOnResult(FisrtAcitivity.this)
                        .startForResult(FetchDataActivity.class)
                        .subscribe(new Consumer<ActivityResultInfo>() {
                            @Override
                            public void accept(ActivityResultInfo activityResultInfo) throws Exception {
                                Log.v(Tag,"rxjava is accept");
                            }
                        });
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.v(Tag,"normal onActivityResult");
    }
}
