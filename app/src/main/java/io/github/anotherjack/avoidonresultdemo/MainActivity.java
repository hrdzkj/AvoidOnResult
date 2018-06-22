package io.github.anotherjack.avoidonresultdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

/**
 * Created by LiuYi on 2018/6/20.
 */

public class MainActivity extends Activity {
    private String TAG = this.getClass().getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.textToFirst).setOnClickListener(v ->
                startActivity( new Intent(MainActivity.this,FisrtAcitivity.class)));
        Log.v(TAG,"-------->onCreate");
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.v(TAG,"-------->onNewIntent");
    }
}
