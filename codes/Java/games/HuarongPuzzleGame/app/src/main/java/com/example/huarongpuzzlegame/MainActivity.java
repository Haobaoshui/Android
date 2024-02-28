package com.example.huarongpuzzlegame;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.huarongpuzzlegame.game.uniform.GameView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final GameView gameView = (GameView) findViewById(R.id.gameView);
        gameView.post(new Runnable() {
            @Override
            public void run() {
                int width = gameView.getWidth();
                int height = gameView.getHeight();
                gameView.splitImage(width, height);
            }
        });
    }
}
