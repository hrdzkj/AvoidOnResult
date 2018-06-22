package startforresult;

import android.content.Intent;

/**
 * Created by LiuYi on 2018/6/22.
 */

public interface IStartForResult {

    interface ICallback {
        void onCallBack(int resultCode,Intent data);
    }

    interface IStartForResultWithCallback{
        void startForResultWithCallback(Intent intent, IStartForResult.ICallback callback);
    }

}
