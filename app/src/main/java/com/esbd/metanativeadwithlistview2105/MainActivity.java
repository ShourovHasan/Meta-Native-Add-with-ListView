package com.esbd.metanativeadwithlistview2105;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeBannerAd;
import com.facebook.ads.NativeBannerAdView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    private NativeBannerAd mNativeBannerAd;
    public static String TAG = "NativeAdCheck";
    HashMap<String, String> hashMap;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.listView);
        loadNativeAd();

        hashMap = new HashMap<>();
        hashMap.put("type","news");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","native_ad");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","news");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","news");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","native_ad");
        arrayList.add(hashMap);

        listView.setAdapter(new MyAdapter());




    }

    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View myView = null;
            
            hashMap = arrayList.get(position);
            String type = hashMap.get("type");
            
            if (type.contains("news")){
                myView = inflater.inflate(R.layout.item, null, false);
            } else if (type.contains("native_ad")) {
                myView = inflater.inflate(R.layout.item_ad, null, false);


                // Render the Native Banner Ad Template
                View adView = NativeBannerAdView.render(MainActivity.this, mNativeBannerAd, NativeBannerAdView.Type.HEIGHT_100);
                LinearLayout nativeBannerAdContainer = (LinearLayout) myView.findViewById(R.id.adContainerLayout);
                // Add the Native Banner Ad View to your ad container
                nativeBannerAdContainer.addView(adView);
            }

            return myView;
        }
    }

    private void loadNativeAd() {
//       nativeAd = new NativeAd(this, "YOUR_PLACEMENT_ID");
        mNativeBannerAd = new NativeBannerAd(this, "YOUR_PLACEMENT_ID");
        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {
                // Native ad finished downloading all assets
                Log.e(TAG, "Native ad finished downloading all assets.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Native ad failed to load
                Log.e(TAG, "Native ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Native ad is loaded and ready to be displayed
                Log.d(TAG, "Native ad is loaded and ready to be displayed!");
                // Race condition, load() called again before last ad was displayed



//                // Render the Native Ad Template
//                View adView = NativeAdView.render(MainActivity.this, nativeAd);
//                LinearLayout nativeAdContainer = (LinearLayout) findViewById(R.id.native_ad_container);
//                nativeAdContainer.addView(adView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 800));


//                == for xml native add===
//                if (nativeAd == null || nativeAd != ad) {
//                    return;
//                }
//                // Inflate Native Ad into Container
//                inflateAd(nativeAd);

            }

            @Override
            public void onAdClicked(Ad ad) {
                // Native ad clicked
                Log.d(TAG, "Native ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Native ad impression
                Log.d(TAG, "Native ad impression logged!");
            }
        };

        // Initiate a request to load an ad.
        // for native add
//        nativeAd.loadAd(
//                nativeAd.buildLoadAdConfig()
//                        .withAdListener(nativeAdListener)
//                        .withMediaCacheFlag(NativeAdBase.MediaCacheFlag.ALL)
//                        .build());

        // for native banner add
        mNativeBannerAd.loadAd(
                mNativeBannerAd.buildLoadAdConfig()
                        .withAdListener(nativeAdListener)
                        .build());
    }


}