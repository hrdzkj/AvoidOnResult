package io.github.anotherjack.avoidonresultdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;


/**
 * Created by LiuYi on 2018/6/20.
 */

public class FetchDataActivity extends Activity{
    private EditText text;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_data);
        text = findViewById(R.id.text);

        findViewById(R.id.goBack).setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("text",text.getText().toString());
            setResult(222,intent);

            finish();

        });

     //   findViewById(R.id.goBack).performClick();

    }



}

