package com.jaydip.tictactoe;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.jaydip.tictactoe.util.Animatee;
import com.jaydip.tictactoe.util.KSUtil;
import com.jaydip.tictactoe.util.SharedPrefs;

public class SettingsActivity extends AppCompatActivity {
    private ImageView backBtn;
    private ImageView bgIV;
    private LinearLayout privacy;
    private LinearLayout rateUs;
    private LinearLayout share;
    SwitchCompat soundSwitch;
    SwitchCompat vibrationSwitch;

   
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_settings);
        this.bgIV = (ImageView) findViewById(R.id.bgIV);
        Glide.with((FragmentActivity) this).load(Integer.valueOf((int) R.drawable.m_bg)).into(this.bgIV);
        this.vibrationSwitch =  findViewById(R.id.vibration_switch);
        this.soundSwitch =  findViewById(R.id.sound_switch);
        this.backBtn =  findViewById(R.id.settings_back_btn);
        this.rateUs =  findViewById(R.id.rate_us_layout);
        this.share =  findViewById(R.id.share_layout);
        this.privacy =  findViewById(R.id.privacy_layout);
        if (SharedPrefs.getIsVibrate(this)) {
            this.vibrationSwitch.setChecked(true);
        } else {
            this.vibrationSwitch.setChecked(false);
        }
        this.vibrationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean z) {
                if (z) {
                    SharedPrefs.setIsVibrate(SettingsActivity.this, true);
                    Toast.makeText(SettingsActivity.this, "Vibration On.", 0).show();
                    return;
                }
                SharedPrefs.setIsVibrate(SettingsActivity.this, false);
                Toast.makeText(SettingsActivity.this, "Vibration Off.", Toast.LENGTH_SHORT).show();
            }
        });
        if (SharedPrefs.getIsMusic(this)) {
            this.soundSwitch.setChecked(true);
        } else {
            this.soundSwitch.setChecked(false);
        }
        this.soundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean z) {
                if (z) {
                    SharedPrefs.setIsMusic(SettingsActivity.this, true);
                    Toast.makeText(SettingsActivity.this, "Sound On.", Toast.LENGTH_SHORT).show();
                    return;
                }
                SharedPrefs.setIsMusic(SettingsActivity.this, false);
                Toast.makeText(SettingsActivity.this, "Sound Off.", Toast.LENGTH_SHORT).show();
            }
        });
        this.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KSUtil.Bounce(SettingsActivity.this, backBtn);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onBackPressed();
                    }
                }, 300);
            }
        });
        this.rateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateUs();

            }
        });
        this.privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsActivity.this, PrivacyActivity.class));

            }
        });
        this.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareApp();

            }
        });

    }

    public void shareApp() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", "Download this awesome app\n https://play.google.com/store/apps/details?id=" + getPackageName() + " \n");
        startActivity(intent);
    }

    public void rateUs() {
        try {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + getPackageName())));
        } catch (ActivityNotFoundException unused) {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatee.animateSlideDown(this);
    }
}
