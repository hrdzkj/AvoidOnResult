package io.github.anotherjack.avoidonresultdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import startforresult.StartForResultUtil;

/**
 * Created by grid on 18/6/18.
 */

public class FisrtAcitivity extends Activity {
    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        initView();
    }



    private void initView() {
        findViewById(R.id.callback).setOnClickListener(v -> {
            Intent intent = new Intent(FisrtAcitivity.this,FetchDataActivity.class);
            StartForResultUtil.getInstance(FisrtAcitivity.this).startForResultWithCallback(intent, (resultCode, data) -> {
                Log.v(TAG,"---------->Callbakck resultCode="+resultCode);
                 finish();
            });
        });


     findViewById(R.id.back).setOnClickListener(v -> {
       finish();
     });

     findViewById(R.id.callback).performClick();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_CANCELED) {

            Log.v(TAG,"-------->resultCode == Activity.RESULT_CANCELED");
        }else
        {
            Log.v(TAG,"-------->resultCode == "+resultCode);
        }
    }
}
