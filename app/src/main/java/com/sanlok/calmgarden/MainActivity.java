package com.sanlok.calmgarden;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Main Activity");

        findViewById(R.id.next_activity_button).setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
        });
    }
}
