package com.jaydip.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.jaydip.tictactoe.util.AdManager;
import com.jaydip.tictactoe.util.Animatee;
import com.jaydip.tictactoe.util.KSUtil;
import com.jaydip.tictactoe.util.LayManager;
import com.jaydip.tictactoe.util.Render;

public class GameMainActivity extends AppCompatActivity {
    ImageView bgIV;
    ImageView logoIV;
    LinearLayout mainLay;
    private ImageView pmulti_btn;
    private ImageView psingle_btn;
    private ImageView settings_btn;


    @Override

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_game_main);
        this.bgIV =  findViewById(R.id.bgIV);
        Glide.with((FragmentActivity) this).load(Integer.valueOf((int) R.drawable.m_bg)).into(this.bgIV);
        this.logoIV =  findViewById(R.id.logoIV);
        Glide.with((FragmentActivity) this).load(Integer.valueOf((int) R.drawable.g_name)).into(this.logoIV);
        this.logoIV.setLayoutParams(LayManager.LinParams(this, 611, 475));
        this.settings_btn = (ImageView) findViewById(R.id.settings_btn);
        Glide.with((FragmentActivity) this).load(Integer.valueOf((int) R.drawable.setting)).into(this.settings_btn);
        this.settings_btn.setLayoutParams(LayManager.LinParams(this, 131, 131));
        this.pmulti_btn = (ImageView) findViewById(R.id.pmulti_btn);
        this.psingle_btn = (ImageView) findViewById(R.id.psingle_btn);
        LinearLayout.LayoutParams LinParams = LayManager.LinParams(this, 586, 249);
        Glide.with((FragmentActivity) this).load(Integer.valueOf((int) R.drawable.multiple)).into(this.pmulti_btn);
        this.pmulti_btn.setLayoutParams(LinParams);
        Glide.with((FragmentActivity) this).load(Integer.valueOf((int) R.drawable.single)).into(this.psingle_btn);
        this.psingle_btn.setLayoutParams(LinParams);
        this.pmulti_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KSUtil.Bounce(GameMainActivity.this, pmulti_btn);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        navigate(new Intent(GameMainActivity.this, MultiPlayersNamesActivity.class), 0);
                    }
                }, 200);
            }
        });
        this.psingle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KSUtil.Bounce(GameMainActivity.this, psingle_btn);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        navigate(new Intent(GameMainActivity.this, SinglePlayerNameActivity.class), 0);

                    }
                }, 200);
            }
        });
        this.settings_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KSUtil.Bounce(GameMainActivity.this, settings_btn);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(GameMainActivity.this, SettingsActivity.class));
                        Animatee.animateSlideUp(GameMainActivity.this);
                    }
                }, 200);
            }
        });
        this.mainLay = (LinearLayout) findViewById(R.id.mainLay);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mainLay.setVisibility(0);
                Render render = new Render(GameMainActivity.this);
                render.setAnimation(KSUtil.In(mainLay));
                render.start();
            }
        }, 400);
    }


    public void navigate(Intent intent, int i) {
        AdManager.startActivity(this, intent, i);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatee.animateSlideDown(this);
    }
}
