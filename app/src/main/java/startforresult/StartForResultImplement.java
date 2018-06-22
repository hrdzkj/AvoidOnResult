package startforresult;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.squareup.leakcanary.RefWatcher;
import java.util.HashMap;
import java.util.Map;

import io.github.anotherjack.avoidonresultdemo.ExampleApplication;


public class StartForResultImplement extends Fragment implements IStartForResult.IStartForResultWithCallback {
    @SuppressLint("UseSparseArrays")
    private Map<Integer, IStartForResult.ICallback> mCallbacks = new HashMap<>();
    private String TAG = StartForResultImplement.class.getSimpleName();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Override
    public void onDestroy() {
        Log.v(TAG,"------>onDestroy");
        mCallbacks.clear();
        super.onDestroy();
        RefWatcher refWatcher = ExampleApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //callback方式的处理
        IStartForResult.ICallback callback; // remove了
        callback = mCallbacks.remove(requestCode);
        if (callback != null) {
            callback.onCallBack(resultCode, data);
        }
    }


    @Override
    public void startForResultWithCallback(Intent intent, IStartForResult.ICallback callback) {
        mCallbacks.clear();
        mCallbacks.put(callback.hashCode(), callback);
        startActivityForResult(intent,callback.hashCode());
    }
}
