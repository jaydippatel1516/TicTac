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

public class MPSelectSignActivity extends AppCompatActivity {
    int PICK_SIDE;
    private ImageView backBtn;
    private ImageView bgIV;
    private ImageView circleImg;
    private ImageView circleRadioImg;
    private ImageView continueBtn;
    private ImageView crossImg;
    private ImageView crossRadioImg;
    private String playerOne;
    private String playerTwo;

   
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_mp_select_sign);
        this.bgIV = (ImageView) findViewById(R.id.bgIV);
        Glide.with((FragmentActivity) this).load(Integer.valueOf((int) R.drawable.m_bg)).into(this.bgIV);
        this.playerOne = getIntent().getStringExtra("p1");
        this.playerTwo = getIntent().getStringExtra("p2");
        this.backBtn = (ImageView) findViewById(R.id.pick_side_back_btn);
        this.crossImg = (ImageView) findViewById(R.id.pick_side_cross_img);
        this.circleImg = (ImageView) findViewById(R.id.pick_side_circle_img);
        this.crossRadioImg = (ImageView) findViewById(R.id.pick_side_cross_radio);
        this.circleRadioImg = (ImageView) findViewById(R.id.pick_side_circle_radio);
        this.continueBtn = (ImageView) findViewById(R.id.pick_side_continue_btn);
        this.circleImg.setAlpha(0.6f);
        this.crossRadioImg.setOnClickListener(new MPSelectSignActivity0(this));
        this.circleRadioImg.setOnClickListener(new MPSelectSignActivity1(this));
        this.backBtn.setOnClickListener(new MPSelectSignActivity2(this));
        this.continueBtn.setOnClickListener(new MPSelectSignActivity3(this));
    }


    public  void m128lambda$onCreate$0$comkessitictactoeMPSelectSignActivity(View view) {
        KSUtil.Bounce(this, this.crossRadioImg);
        this.PICK_SIDE = 0;
        this.crossRadioImg.setImageResource(R.drawable.radio_button_checked);
        this.circleRadioImg.setImageResource(R.drawable.radio_button_unchecked);
        this.circleImg.setAlpha(0.6f);
        this.crossImg.setAlpha(1.0f);
    }


    public  void m129lambda$onCreate$1$comkessitictactoeMPSelectSignActivity(View view) {
        KSUtil.Bounce(this, this.circleRadioImg);
        this.PICK_SIDE = 1;
        this.circleRadioImg.setImageResource(R.drawable.radio_button_checked);
        this.crossRadioImg.setImageResource(R.drawable.radio_button_unchecked);
        this.crossImg.setAlpha(0.6f);
        this.circleImg.setAlpha(1.0f);
    }


    public  void m130lambda$onCreate$2$comkessitictactoeMPSelectSignActivity(View view) {
        KSUtil.Bounce(this, this.backBtn);
        new Handler().postDelayed(new MPSelectSignActivity5(this), 300);
    }


    public  void m132lambda$onCreate$4$comkessitictactoeMPSelectSignActivity(View view) {
        KSUtil.Bounce(this, this.continueBtn);
        new Handler().postDelayed(new MPSelectSignActivity4(this), 300);
    }


    public  void m131lambda$onCreate$3$comkessitictactoeMPSelectSignActivity() {
        Intent intent = new Intent(this, MPDashboardActivity.class);
        intent.putExtra("p1", this.playerOne);
        intent.putExtra("p2", this.playerTwo);
        intent.putExtra("ps", this.PICK_SIDE);
        navigate(intent, 0);
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
