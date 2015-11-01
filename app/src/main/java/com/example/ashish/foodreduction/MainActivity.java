package com.example.ashish.foodreduction;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebView webview = new WebView(this);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        setContentView(webview);
        final Activity activity = this;
//        webview.setWebViewClient(new WebViewClient() {
////            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
////                Toast.makeText(activity, "Oh no! " + description, Toast.LENGTH_SHORT).show();
////            }
//
//            @Override
//            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
//                super.onReceivedHttpError(view, request, errorResponse);
//                Toast.makeText(activity,String.valueOf(errorResponse),Toast.LENGTH_LONG).show();
//            }
//        });

        webview.setWebViewClient(new WebViewClient(){
            ProgressDialog progressDialog;
            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                if(progressDialog==null){
                    progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setMessage("Loading");
                    progressDialog.show();
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                try{
                    if(progressDialog.isShowing()){
                        progressDialog.dismiss();
                        progressDialog=null;
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        webview.canGoBackOrForward(5);
        webview.loadUrl("http://foodreduction.tk/");



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
