package communicate.eventbus;

import android.content.Intent;

/**
 * Created by LiuYi on 2018/6/20.
 */

public class BusEventData {
    public String mData;
    private int mEventCode;

    public BusEventData(int eventCode,String data) {
        mEventCode = eventCode;
        mData =data;
    }

    public String getContent() {
        return mData;
    }

    public int getEventCode()
    {
       return mEventCode;
    }

}
