package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    //private String gameId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Map <String,String> games = new HashMap<String,String>();
        games.put("Here Come Trouble", "S79244-38759-47429-24622");
        games.put("Dungons", "S37302-95546-86584-34574");
        games.put("master explorer", "S24092-36543-81742-58373");

        setContentView(R.layout.activity_main);
        //create webview
        WebView webView = findViewById(R.id.webview1);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);

        //create spinner
        Spinner spinner = (Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item, games.keySet().toArray(new String[games.size()]));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected (AdapterView < ? > parent, View view,int pos, long id){
                String gameId = games.get(parent.getItemAtPosition(pos).toString());
                
                String projectUrl = "<iframe width=\"100%\" height=\"100%\" "
                        + "src=\"https://arcade.makecode.com/---run?id="
                        + gameId + "\" "
                        + "seamless=\"seamless\" "
                        + "scrolling=\"no\" "
                        + "frameborder=\"0\" "
                        + "allowtransparency=\"true\"></iframe>";
                //webView.loadDataWithBaseURL(null, projectUrl, "text/html", "UTF-8", null);
            }

            @Override
            public void onNothingSelected (AdapterView parent){
                // Do nothing.
            }
        });

        //create button
        Button button = findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gameId = games.get(spinner.getSelectedItem().toString());
                String projectUrl = "<iframe width=\"100%\" height=\"100%\" "
                        + "src=\"https://arcade.makecode.com/---run?id="
                        + gameId + "\" "
                        + "seamless=\"seamless\" "
                        + "scrolling=\"no\" "
                        + "frameborder=\"0\" "
                        + "allowtransparency=\"true\"></iframe>";
                webView.loadDataWithBaseURL(null, projectUrl, "text/html", "UTF-8", null);
                final ProgressDialog pd = ProgressDialog.show(MainActivity.this, "", "Loading...", true);
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
        });

    }
}