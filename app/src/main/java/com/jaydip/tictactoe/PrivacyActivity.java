package com.jaydip.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class PrivacyActivity extends Activity {
   
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_privacy);
        getWindow().setFlags(1024, 1024);
        WebView webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/privacy_policy.html");
    }
}
