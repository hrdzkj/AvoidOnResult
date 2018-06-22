package startforresult;

import android.app.Activity;
import android.app.FragmentManager;

/**
 * 用于返回一个实现IStartForResultWithCallback的实现者
 */

public class StartForResultUtil {
    private static final String TAG = StartForResultUtil.class.getSimpleName();


    public static IStartForResult.IStartForResultWithCallback getInstance(Activity activity) {
        StartForResultImplement fragment = (StartForResultImplement) activity.getFragmentManager().findFragmentByTag(TAG);
        if (fragment == null) {
            fragment = new StartForResultImplement();
            FragmentManager fragmentManager = activity.getFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .add(fragment, TAG)
                    .commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
        return fragment;
    }

}
