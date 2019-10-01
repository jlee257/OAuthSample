package com.sanlok.calmgarden;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login Activity");


        findViewById(R.id.login_button).setOnClickListener(v -> {
            final CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder()
                    .setToolbarColor(this.getResources().getColor(R.color.colorPrimary))
                    .setShowTitle(true)
                    .build();

            final Intent intent = customTabsIntent.intent;
            intent.setData(getAuthServerUri());
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
    }



    Uri getAuthServerUri() {
        Uri.Builder builder = Uri.parse("https://accounts.google.com/o/oauth2/v2/auth").buildUpon()
                .appendQueryParameter("redirect_uri", "com.sanlok.calmgarden:/googleauthcallback")
                .appendQueryParameter("client_id", "791219182348-vhtk6smd9l2tph9c4dksaob5icehqmbj.apps.googleusercontent.com")
                .appendQueryParameter("response_type", "code")
                .appendQueryParameter("scope", "profile");

        return builder.build();
    }



    @Override
    protected void onNewIntent(Intent intent) {
        if (intent != null) {
            String code = intent.getStringExtra("code");

            Log.i("CalmGarden", "code=" + code);
            Toast.makeText(this, "code=" + code, Toast.LENGTH_LONG).show();
        }
    }
}
