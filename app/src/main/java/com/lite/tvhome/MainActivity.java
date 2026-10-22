package com.lite.tvhome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
    WebView w;
    static final String[][] M = {
        {"netflix.com", "com.netflix.ninja"},
        {"youtube.com", "com.google.android.youtube.tv"},
        {"primevideo.com", "com.amazon.amazonvideo.livingroom"},
        {"disneyplus.com", "com.disney.disneyplus"},
        {"shahid.mbc.net", "net.mbc.shahid"},
        {"spotify.com", "com.spotify.tv.android"}
    };

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        w = new WebView(this);
        setContentView(w);
        WebSettings s = w.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        w.setBackgroundColor(0xFF080D18);
        w.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView v, String url) {
                if (url.startsWith("file:")) return false;
                for (String[] m : M) {
                    if (url.contains(m[0])) {
                        Intent i = getPackageManager().getLeanbackLaunchIntentForPackage(m[1]);
                        if (i == null) i = getPackageManager().getLaunchIntentForPackage(m[1]);
                        if (i != null) { startActivity(i); return true; }
                    }
                }
                return false;
            }
        });
        w.loadUrl("file:///android_asset/index.html");
        w.requestFocus();
    }

    @Override
    public void onBackPressed() { }
}
