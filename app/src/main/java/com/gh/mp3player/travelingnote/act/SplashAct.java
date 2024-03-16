package com.gh.mp3player.travelingnote.act;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.gh.mp3player.travelingnote.R;

public class SplashAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        handler.postDelayed(() -> nextAct(), 2000);
    }

    private void nextAct() {

            Intent intent=new Intent(this, MainActivity.class);
            startActivity(intent);

    }
}