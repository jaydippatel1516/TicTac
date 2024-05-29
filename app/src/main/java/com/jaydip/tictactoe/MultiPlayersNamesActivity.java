package com.jaydip.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.jaydip.tictactoe.util.Animatee;
import com.jaydip.tictactoe.util.KSUtil;

public class MultiPlayersNamesActivity extends AppCompatActivity {
    private ImageView backBtn;
    private ImageView backBtn1;
    private ImageView bgIV;
    private String playerOne;
    private ImageView playerOneButton;
    private RelativeLayout playerOneLayout;
    private EditText playerOneName;
    private String playerTwo;
    private ImageView playerTwoButton;
    private RelativeLayout playerTwoLayout;
    private EditText playerTwoName;

   
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_multi_players_names);
        this.bgIV = (ImageView) findViewById(R.id.bgIV);
        Glide.with((FragmentActivity) this).load(Integer.valueOf((int) R.drawable.m_bg)).into(this.bgIV);
        this.backBtn = (ImageView) findViewById(R.id.player_names_back_btn);
        this.backBtn1 = (ImageView) findViewById(R.id.player_names_back_btn1);
        this.playerOneName = (EditText) findViewById(R.id.player_one_name_edttxt);
        this.playerTwoName = (EditText) findViewById(R.id.player_two_name_edttxt);
        this.playerOneButton = (ImageView) findViewById(R.id.player_one_btn);
        this.playerTwoButton = (ImageView) findViewById(R.id.player_two_btn);
        this.playerOneLayout = (RelativeLayout) findViewById(R.id.player_one_layout);
        this.playerTwoLayout = (RelativeLayout) findViewById(R.id.player_two_layout);
        this.playerOneButton.setOnClickListener(new MultiPlayersNamesActivity2(this));
        this.backBtn.setOnClickListener(new MultiPlayersNamesActivity3(this));
        this.backBtn1.setOnClickListener(new MultiPlayersNamesActivity4(this));
        this.playerTwoButton.setOnClickListener(new MultiPlayersNamesActivity5(this));
    }


    public  void m134lambda$onCreate$1$comkessitictactoeMultiPlayersNamesActivity(View view) {
        if (TextUtils.isEmpty(this.playerOneName.getText().toString())) {
            Toast.makeText(getBaseContext(), "Enter Player Name", 1).show();
            return;
        }
        KSUtil.Bounce(this, this.playerOneButton);
        new Handler().postDelayed(new MultiPlayersNamesActivity0(this), 300);
    }


    public  void m133lambda$onCreate$0$comkessitictactoeMultiPlayersNamesActivity() {
        this.playerOneLayout.setVisibility(8);
        this.playerTwoLayout.setVisibility(0);
        this.playerOne = this.playerOneName.getText().toString();
    }


    public  void m135lambda$onCreate$2$comkessitictactoeMultiPlayersNamesActivity(View view) {
        KSUtil.Bounce(this, this.backBtn);
        new Handler().postDelayed(new MultiPlayersNamesActivity7(this), 300);
    }


    public  void m137lambda$onCreate$4$comkessitictactoeMultiPlayersNamesActivity(View view) {
        KSUtil.Bounce(this, this.playerOneButton);
        new Handler().postDelayed(new MultiPlayersNamesActivity6(this), 300);
    }


    public  void m136lambda$onCreate$3$comkessitictactoeMultiPlayersNamesActivity() {
        this.playerTwoLayout.setVisibility(8);
        this.playerOneLayout.setVisibility(0);
    }


    public  void m139lambda$onCreate$6$comkessitictactoeMultiPlayersNamesActivity(View view) {
        if (TextUtils.isEmpty(this.playerTwoName.getText().toString())) {
            Toast.makeText(getBaseContext(), "Enter Player Name", 1).show();
            return;
        }
        KSUtil.Bounce(this, this.playerTwoButton);
        new Handler().postDelayed(new MultiPlayersNamesActivity1(this), 300);
    }


    public  void m138lambda$onCreate$5$comkessitictactoeMultiPlayersNamesActivity() {
        this.playerTwo = this.playerTwoName.getText().toString();
        Intent intent = new Intent(this, MPSelectSignActivity.class);
        intent.putExtra("p1", this.playerOne);
        intent.putExtra("p2", this.playerTwo);
        startActivity(intent);
        Animatee.animateSlideUp(this);
    }

    @Override // androidx.activity.ComponentActivity
    public void onBackPressed() {
        super.onBackPressed();
        Animatee.animateSlideDown(this);
    }
}
