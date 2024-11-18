package com.example.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //create webview
        super.onCreate(savedInstanceState);
        WebView webView = findViewById(R.id.webview1);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setDomStorageEnabled(true);
        final ProgressDialog pd = ProgressDialog.show(WebViewActivity.this, "", "Loading...", true);


        String projectUrl = "<iframe width=\"100%\" height=\"100%\" "
                + "src=\"https://arcade.makecode.com/---run?id="
                + "S75750-04534-22148-97281" + "\" "
                + "seamless=\"seamless\" "
                + "scrolling=\"no\" "
                + "frameborder=\"0\" "
                + "allowtransparency=\"true\"></iframe>";
        webView.loadDataWithBaseURL(null, projectUrl, "text/html", "UTF-8", null);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                pd.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                try {
                    pd.dismiss();
                } catch (Exception e) {

                }
            }
        });
    }
}
