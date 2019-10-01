package com.sanlok.calmgarden;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class DeeplinkReceiverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deeplink_receiver);

        final Uri uri = getIntent().getData();

        Log.i("CalmGarden", "uri=" + uri);
        Log.i("CalmGarden", "host=" + uri.getHost());
        Log.i("CalmGarden", "authority=" + uri.getAuthority());
        Log.i("CalmGarden", "scheme=" + uri.getScheme());
        Log.i("CalmGarden", "path=" + uri.getPath());

        for (String name: uri.getQueryParameterNames()) {
            Log.i("CalmGarden", "query[" + name + "]=" + uri.getQueryParameter(name));
        }

        if (uri.getPath().contains("googleauthcallback")) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // To clear top
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP); // To call onNewIntent
            intent.putExtra("code", uri.getQueryParameter("code"));
            startActivity(intent);
            finish();
        }
    }
}
