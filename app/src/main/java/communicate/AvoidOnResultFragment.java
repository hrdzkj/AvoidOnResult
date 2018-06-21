package communicate;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.squareup.leakcanary.RefWatcher;
import com.squareup.otto.Subscribe;

import java.util.HashMap;
import java.util.Map;

import communicate.eventbus.BusEventData;
import communicate.eventbus.BusProvider;
import io.github.anotherjack.avoidonresultdemo.ExampleApplication;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by jack on 2017/12/27.
 * modify by https://guofeng007.github.io remove request code ,instead use callback.hashcode as requestcode 2017/1/10
 */

public class AvoidOnResultFragment extends Fragment {
    private Map<Integer, PublishSubject<ActivityResultInfo>> mSubjects = new HashMap<>();
    private Map<Integer, AvoidOnResult.Callback> mCallbacks = new HashMap<>();
    private String TAG = AvoidOnResultFragment.class.getSimpleName();

    public AvoidOnResultFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        BusProvider.getInstance().register(this);
        Log.v("TAG","------->Event bus is register");
    }

    @Override
    public void onDestroy() {
        BusProvider.getInstance().unregister(this);
        mSubjects.clear();
        mCallbacks.clear();
        Log.v("TAG","------->Event bus is cancle");
        super.onDestroy();
        RefWatcher refWatcher = ExampleApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }


    public Observable<ActivityResultInfo> startForResult(final Intent intent) {
        final PublishSubject<ActivityResultInfo> subject = PublishSubject.create();
        return subject.doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                mSubjects.put(subject.hashCode(), subject);
                startActivityForResult(intent, subject.hashCode());
            }
        });
    }

    public void startActivityWithCallback(Intent intent, AvoidOnResult.Callback callback) {
        mCallbacks.put(callback.getEventCode(), callback);
        startActivity(intent);
    }

    /*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //rxjava方式的处理
        PublishSubject<ActivityResultInfo> subject = mSubjects.remove(requestCode);
        if (subject != null) {
            subject.onNext(new ActivityResultInfo(resultCode, data));
            subject.onComplete();
        }

        //callback方式的处理
        AvoidOnResult.Callback callback = mCallbacks.remove(requestCode); // remove了
        if (callback != null) {
            callback.onActivityResult( resultCode, data);
        }
    }
*/
    @Subscribe
    public void transfer(BusEventData data) {
        //callback方式的处理
        //AvoidOnResult.Callback callback = mCallbacks.remove(data.getEventCode()); // remove了
        AvoidOnResult.Callback callback = mCallbacks.get(data.getEventCode()); // remove了
        if (callback != null) {
            callback.onCallBack(data.getContent());
        }
    }



}
