package com.jaydip.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.jaydip.tictactoe.util.Animatee;
import com.jaydip.tictactoe.util.KSUtil;

public class SinglePlayerNameActivity extends AppCompatActivity {
    private ImageView back_btn;
    ImageView bgIV;
    private ImageView next_btn;
    private String playerName;
    private EditText player_name_edt;

   
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_single_player_name);
        this.bgIV = (ImageView) findViewById(R.id.bgIV);
        Glide.with((FragmentActivity) this).load(Integer.valueOf((int) R.drawable.m_bg)).into(this.bgIV);
        this.back_btn = (ImageView) findViewById(R.id.back_btn);
        this.player_name_edt = (EditText) findViewById(R.id.player_name_edt);
        ImageView imageView = (ImageView) findViewById(R.id.next_btn);
        this.next_btn = imageView;
        imageView.setOnClickListener(new SinglePlayerNameActivity0(this));
        this.back_btn.setOnClickListener(new SinglePlayerNameActivity1(this));
    }


    public  void m169lambda$onCreate$1$comkessitictactoeSinglePlayerNameActivity(View view) {
        if (TextUtils.isEmpty(this.player_name_edt.getText().toString())) {
            Toast.makeText(getBaseContext(), "Enter Player Name", 1).show();
            return;
        }
        KSUtil.Bounce(this, this.next_btn);
        new Handler().postDelayed(new SinglePlayerNameActivity2(this), 300);
    }


    public  void m168lambda$onCreate$0$comkessitictactoeSinglePlayerNameActivity() {
        this.playerName = this.player_name_edt.getText().toString();
        Intent intent = new Intent(this, SelectSignActivity.class);
        intent.putExtra("p1", this.playerName);
        startActivity(intent);
        Animatee.animateSlideUp(this);
    }


    public  void m170lambda$onCreate$2$comkessitictactoeSinglePlayerNameActivity(View view) {
        KSUtil.Bounce(this, this.back_btn);
        new Handler().postDelayed(new SinglePlayerNameActivity3(this), 300);
    }

    @Override // androidx.activity.ComponentActivity
    public void onBackPressed() {
        super.onBackPressed();
        Animatee.animateSlideDown(this);
    }
}
