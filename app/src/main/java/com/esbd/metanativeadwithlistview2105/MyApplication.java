package com.esbd.metanativeadwithlistview2105;

import android.app.Application;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AudienceNetworkAds;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AudienceNetworkAds.initialize(this);
        AdSettings.addTestDevice("fd5f3aab-0519-4441-8784-14464bcbdd8c");
    }
}
