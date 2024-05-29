package com.jaydip.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.jaydip.tictactoe.util.AdManager;
import com.jaydip.tictactoe.util.Animatee;
import com.jaydip.tictactoe.util.KSUtil;

public class SelectSignActivity extends AppCompatActivity {
    int PICK_SIDE;
    private ImageView bgIV;
    private ImageView btn_back;
    private ImageView btn_continue;
    private ImageView btn_pick_circle;
    private ImageView btn_pick_cross;
    private ImageView circle_img;
    private ImageView cross_img;
    private String playerName;

   
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_select_sign);
        this.bgIV = (ImageView) findViewById(R.id.bgIV);
        Glide.with((FragmentActivity) this).load(Integer.valueOf((int) R.drawable.m_bg)).into(this.bgIV);
        this.playerName = getIntent().getStringExtra("p1");
        this.btn_back = (ImageView) findViewById(R.id.btn_back);
        this.cross_img = (ImageView) findViewById(R.id.cross_img);
        this.circle_img = (ImageView) findViewById(R.id.circle_img);
        this.btn_pick_cross = (ImageView) findViewById(R.id.btn_pick_cross);
        this.btn_pick_circle = (ImageView) findViewById(R.id.btn_pick_circle);
        this.btn_continue = (ImageView) findViewById(R.id.btn_continue);
        this.circle_img.setAlpha(0.6f);
        this.btn_pick_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KSUtil.Bounce(SelectSignActivity.this, btn_pick_cross);
                PICK_SIDE = 0;
                btn_pick_cross.setImageResource(R.drawable.radio_button_checked);
                btn_pick_circle.setImageResource(R.drawable.radio_button_unchecked);
                circle_img.setAlpha(0.6f);
                cross_img.setAlpha(1.0f);
            }
        });
        this.btn_pick_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KSUtil.Bounce(SelectSignActivity.this, btn_pick_circle);
                PICK_SIDE = 1;
                btn_pick_circle.setImageResource(R.drawable.radio_button_checked);
                btn_pick_cross.setImageResource(R.drawable.radio_button_unchecked);
                cross_img.setAlpha(0.6f);
                circle_img.setAlpha(1.0f);
            }
        });
        this.btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KSUtil.Bounce(SelectSignActivity.this, btn_back);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onBackPressed();
                    }
                }, 300);
            }
        });
        this.btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KSUtil.Bounce(SelectSignActivity.this, btn_continue);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SelectSignActivity.this, SPDashboardActivity.class);
                        intent.putExtra("p1", playerName);
                        intent.putExtra("ps", PICK_SIDE);
                        navigate(intent, 0);
                    }
                }, 300);
            }
        });

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
