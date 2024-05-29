package com.jaydip.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.jaydip.tictactoe.util.Animatee;
import com.jaydip.tictactoe.util.KSUtil;
import com.jaydip.tictactoe.util.LayManager;
import com.jaydip.tictactoe.util.Render;

public class StartActivity extends AppCompatActivity {
    ImageView bgIV;
    ImageView btnPlay;
    ImageView logoIV;

   
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_start);
        this.bgIV =  findViewById(R.id.bgIV);
        Glide.with((FragmentActivity) this).load(Integer.valueOf((int) R.drawable.m_bg)).into(this.bgIV);
        this.logoIV =  findViewById(R.id.logoIV);
        Glide.with((FragmentActivity) this).load(Integer.valueOf((int) R.drawable.g_name)).into(this.logoIV);
        this.logoIV.setLayoutParams(LayManager.LinParams(this, 819, 631));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                logoIV.setVisibility(0);
                Render render = new Render(StartActivity.this);
                render.setAnimation(KSUtil.ZoomIn(logoIV));
                render.start();
            }
        }, 1000);
        this.btnPlay =  findViewById(R.id.btnPlay);
        Glide.with((FragmentActivity) this).load(Integer.valueOf((int) R.drawable.play)).into(this.btnPlay);
        this.btnPlay.setLayoutParams(LayManager.LinParams(this, 472, 262));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btnPlay.setVisibility(0);
                Render render = new Render(StartActivity.this);
                render.setAnimation(KSUtil.In(btnPlay));
                render.start();
            }
        }, 2000);
        btnPlay=  findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KSUtil.Bounce(StartActivity.this, btnPlay);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(StartActivity.this, GameMainActivity.class));
                        Animatee.animateSlideUp(StartActivity.this);
                    }
                }, 200);
            }
        });
    }



}
