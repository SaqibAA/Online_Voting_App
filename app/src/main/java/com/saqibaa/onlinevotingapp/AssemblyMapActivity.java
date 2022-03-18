package com.saqibaa.onlinevotingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AssemblyMapActivity extends AppCompatActivity {

    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assembly_map);

        web = findViewById(R.id.map_web);

//        WebSettings webSettings = web.getSettings();
//        webSettings.setJavaScriptEnabled(true);
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setUseWideViewPort(true);

        web.getSettings().setSupportZoom(true);
        web.getSettings().setBuiltInZoomControls(true);
        web.getSettings().setDisplayZoomControls(false);

        web.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        web.setScrollbarFadingEnabled(false);
//        web.getSettings().setUserAgentString("Android");

        // loading http://www.google.com url in the the WebView.
//        w.loadUrl("https://eci.gov.in/elections/election/");
        web.loadUrl("https://ceo.maharashtra.gov.in/SearchInfo/maps.aspx");

        // this will enable the javascript.
//        web.getSettings().setJavaScriptEnabled(true);

        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        web.setWebViewClient(new WebViewClient());
    }
}