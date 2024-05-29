package com.jaydip.tictactoe;

import static android.os.Build.VERSION.SDK_INT;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.jaydip.tictactoe.util.AdManager;
import com.jaydip.tictactoe.util.Animatee;
import com.jaydip.tictactoe.util.KSUtil;
import com.jaydip.tictactoe.util.SharedPrefs;

public class MPDashboardActivity extends AppCompatActivity implements View.OnClickListener {
    int ActivePlayer;
    private ImageView Box_1;
    private ImageView Box_2;
    private ImageView Box_3;
    private ImageView Box_4;
    private ImageView Box_5;
    private ImageView Box_6;
    private ImageView Box_7;
    private ImageView Box_8;
    private ImageView Box_9;
    int PICK_SIDE;
    int Player_0 = 1;
    int Player_X = 0;
    private ImageView backBtn;
    private ImageView bgIV;
    Dialog dialog;
    Dialog drawMatchDialog;
    Dialog exitDialog;
    int[] filledPos = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
    boolean isGameActive = true;
    LinearLayout player1Lay;
    LinearLayout player2Lay;
    private String playerOne;
    private ImageView playerOneImg;
    private TextView playerOneName;
    int playerOneWinCount = 0;
    private TextView playerOneWins;
    private String playerTwo;
    private ImageView playerTwoImg;
    private TextView playerTwoName;
    int playerTwoWinCount = 0;
    private TextView playerTwoWins;
    private ImageView settingsBtn;
    int storeActivePlayer;
    Vibrator vibrator;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_mp_dashboard);
        this.bgIV = (ImageView) findViewById(R.id.bgIV);
        Glide.with((FragmentActivity) this).load(Integer.valueOf((int) R.drawable.m_bg)).into(this.bgIV);
        this.dialog = new Dialog(this);
        this.drawMatchDialog = new Dialog(this);
        this.exitDialog = new Dialog(this);
        this.vibrator = (Vibrator) getSystemService("vibrator");
        this.Box_1 = (ImageView) findViewById(R.id.img_1);
        this.Box_2 = (ImageView) findViewById(R.id.img_2);
        this.Box_3 = (ImageView) findViewById(R.id.img_3);
        this.Box_4 = (ImageView) findViewById(R.id.img_4);
        this.Box_5 = (ImageView) findViewById(R.id.img_5);
        this.Box_6 = (ImageView) findViewById(R.id.img_6);
        this.Box_7 = (ImageView) findViewById(R.id.img_7);
        this.Box_8 = (ImageView) findViewById(R.id.img_8);
        this.Box_9 = (ImageView) findViewById(R.id.img_9);
        this.backBtn = (ImageView) findViewById(R.id.offline_game_back_btn);
        this.settingsBtn = (ImageView) findViewById(R.id.offline_game_seting_gifview);
        this.playerOneImg = (ImageView) findViewById(R.id.player_one_img);
        this.playerTwoImg = (ImageView) findViewById(R.id.player_two_img);
        this.playerOneName = (TextView) findViewById(R.id.player_one_name_txt);
        this.playerTwoName = (TextView) findViewById(R.id.player_two_name_txt);
        this.playerOneWins = (TextView) findViewById(R.id.player_one_win_count_txt);
        this.playerTwoWins = (TextView) findViewById(R.id.player_two_won_txt);
        this.Box_1.setOnClickListener(this);
        this.Box_2.setOnClickListener(this);
        this.Box_3.setOnClickListener(this);
        this.Box_4.setOnClickListener(this);
        this.Box_5.setOnClickListener(this);
        this.Box_6.setOnClickListener(this);
        this.Box_7.setOnClickListener(this);
        this.Box_8.setOnClickListener(this);
        this.Box_9.setOnClickListener(this);
        this.playerOneWins.setText(String.valueOf(this.playerOneWinCount));
        this.playerTwoWins.setText(String.valueOf(this.playerTwoWinCount));
        this.playerOne = getIntent().getStringExtra("p1");
        this.playerTwo = getIntent().getStringExtra("p2");
        this.PICK_SIDE = getIntent().getIntExtra("ps", 0);
        this.playerOneName.setText(this.playerOne);
        this.playerTwoName.setText(this.playerTwo);
        int i = this.PICK_SIDE;
        this.ActivePlayer = i;
        this.storeActivePlayer = i;
        this.player1Lay = (LinearLayout) findViewById(R.id.player1Lay);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.player2Lay);
        this.player2Lay = linearLayout;
        int i2 = this.PICK_SIDE;
        if (i2 == 0) {
            linearLayout.setBackgroundResource(R.drawable.bgb);
            this.playerTwoName.setTextColor(getResources().getColor(R.color.player_one_txt));
            this.playerTwoWins.setBackgroundResource(R.drawable.scoreb);
            this.player1Lay.setBackgroundResource(R.drawable.bgy);
            this.playerOneName.setTextColor(getResources().getColor(R.color.player_two_txt));
            this.playerOneWins.setBackgroundResource(R.drawable.scorey);
            this.player2Lay.setAlpha(0.6f);
            this.playerTwoImg.setAlpha(0.6f);
            this.storeActivePlayer = 0;
            this.ActivePlayer = 0;
        } else if (i2 == 1) {
            linearLayout.setBackgroundResource(R.drawable.bgy);
            this.playerTwoName.setTextColor(getResources().getColor(R.color.player_two_txt));
            this.playerTwoWins.setBackgroundResource(R.drawable.scorey);
            this.player1Lay.setBackgroundResource(R.drawable.bgb);
            this.playerOneName.setTextColor(getResources().getColor(R.color.player_one_txt));
            this.playerOneWins.setBackgroundResource(R.drawable.scoreb);
            this.player2Lay.setAlpha(0.6f);
            this.playerTwoImg.setAlpha(0.6f);
            this.storeActivePlayer = 1;
            this.ActivePlayer = 1;
        }
        this.settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KSUtil.Bounce(MPDashboardActivity.this, settingsBtn);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(MPDashboardActivity.this, SettingsActivity.class));
                        Animatee.animateSlideUp(MPDashboardActivity.this);
                    }
                }, 300);
            }
        });
        this.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KSUtil.Bounce(MPDashboardActivity.this, backBtn);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onBackPressed();
                    }
                }, 300);
            }
        });
    }



    public void navigate(Intent intent, int i) {
        AdManager.startActivity(this, intent, i);

    }

    @Override // androidx.activity.ComponentActivity
    public void onBackPressed() {
        super.onBackPressed();
        exitDialog();
    }

    public void onClick(View var1) {
        if (this.isGameActive) {
            label59:
            {
                ImageView var5 = (ImageView) this.findViewById(var1.getId());
                int var3 = Integer.parseInt(var1.getTag().toString());
                int var4 = this.ActivePlayer;
                int var2;
                int[] var6;
                if (var4 == this.Player_X) {
                    var6 = this.filledPos;
                    var2 = var3 - 1;
                    if (var6[var2] == -1) {
                        if (SharedPrefs.getIsMusic(this)) {
                            MediaPlayer.create(this, R.raw.x).start();
                        }

                        if (SharedPrefs.getIsVibrate(this)) {
                            if (SDK_INT >= 26) {
                                this.vibrator.vibrate(VibrationEffect.createOneShot(200L, -1));
                            } else {
                                this.vibrator.vibrate(200L);
                            }
                        }

                        var3 = this.PICK_SIDE;
                        if (var3 == 0) {
                            this.player1Lay.setAlpha(0.6F);
                            this.player2Lay.setAlpha(1.0F);
                            this.playerOneImg.setAlpha(0.6F);
                            this.playerTwoImg.setAlpha(1.0F);
                        } else if (var3 == 1) {
                            this.player2Lay.setAlpha(0.6F);
                            this.player1Lay.setAlpha(1.0F);
                            this.playerTwoImg.setAlpha(0.6F);
                            this.playerOneImg.setAlpha(1.0F);
                        }

                        var5.setImageResource(R.drawable.cross);
                        this.storeActivePlayer = this.ActivePlayer;
                        this.ActivePlayer = this.Player_0;
                        this.filledPos[var2] = this.Player_X;
                        break label59;
                    }
                }

                if (var4 == this.Player_0) {
                    var6 = this.filledPos;
                    --var3;
                    if (var6[var3] == -1) {
                        if (SharedPrefs.getIsMusic(this)) {
                            MediaPlayer.create(this, R.raw.o).start();
                        }

                        if (SharedPrefs.getIsVibrate(this)) {
                            if (SDK_INT >= 26) {
                                this.vibrator.vibrate(VibrationEffect.createOneShot(200L, -1));
                            } else {
                                this.vibrator.vibrate(200L);
                            }
                        }

                        var2 = this.PICK_SIDE;
                        if (var2 == 0) {
                            this.player2Lay.setAlpha(0.6F);
                            this.player1Lay.setAlpha(1.0F);
                            this.playerTwoImg.setAlpha(0.6F);
                            this.playerOneImg.setAlpha(1.0F);
                        } else if (var2 == 1) {
                            this.player1Lay.setAlpha(0.6F);
                            this.player2Lay.setAlpha(1.0F);
                            this.playerOneImg.setAlpha(0.6F);
                            this.playerTwoImg.setAlpha(1.0F);
                        }

                        var5.setImageResource(R.drawable.circle);
                        this.storeActivePlayer = this.ActivePlayer;
                        this.ActivePlayer = this.Player_X;
                        this.filledPos[var3] = this.Player_0;
                    }
                }
            }

            this.checkForWin();
            if (this.isGameActive) {
                this.checkdraw();
            }

        }
    }

    private void checkForWin() {
        int[] var9 = new int[]{1, 2, 3};
        int[] var8 = new int[]{4, 5, 6};
        int[] var11 = new int[]{1, 4, 7};
        int[] var10 = new int[]{2, 5, 8};
        int[] var7 = new int[]{3, 6, 9};
        int[] var12 = new int[]{1, 5, 9};
        int[] var13 = new int[]{3, 5, 7};

        for (int var1 = 0; var1 < 8; ++var1) {
            int[] var14 = (new int[][]{var9, var8, {7, 8, 9}, var11, var10, var7, var12, var13})[var1];
            int var2 = var14[0];
            int var3 = var14[1];
            int var4 = var14[2];
            var14 = this.filledPos;
            int var5 = var14[var2 - 1];
            int var6 = var14[var3 - 1];
            if (var5 == var6 && var6 == var14[var4 - 1] && var5 != -1) {
                var5 = this.storeActivePlayer;
                Handler var15;
                if (var5 == this.Player_X) {
                    if (this.PICK_SIDE == 0) {
                        var5 = this.playerOneWinCount + 1;
                        this.playerOneWinCount = var5;
                        this.playerOneWins.setText(String.valueOf(var5));
                    }

                    if (this.PICK_SIDE == 1) {
                        var5 = this.playerTwoWinCount + 1;
                        this.playerTwoWinCount = var5;
                        this.playerTwoWins.setText(String.valueOf(var5));
                    }

                    if (var2 == 1 && var3 == 2 && var4 == 3) {
                        this.Box_1.setBackgroundResource(R.drawable.orange_bg);
                        this.Box_2.setBackgroundResource(R.drawable.orange_bg);
                        this.Box_3.setBackgroundResource(R.drawable.orange_bg);
                    } else if (var2 == 4 && var3 == 5 && var4 == 6) {
                        this.Box_4.setBackgroundResource(R.drawable.orange_bg);
                        this.Box_5.setBackgroundResource(R.drawable.orange_bg);
                        this.Box_6.setBackgroundResource(R.drawable.orange_bg);
                    } else if (var2 == 7 && var3 == 8 && var4 == 9) {
                        this.Box_7.setBackgroundResource(R.drawable.orange_bg);
                        this.Box_8.setBackgroundResource(R.drawable.orange_bg);
                        this.Box_9.setBackgroundResource(R.drawable.orange_bg);
                    } else if (var2 == 1 && var3 == 4 && var4 == 7) {
                        this.Box_1.setBackgroundResource(R.drawable.orange_bg);
                        this.Box_4.setBackgroundResource(R.drawable.orange_bg);
                        this.Box_7.setBackgroundResource(R.drawable.orange_bg);
                    } else if (var2 == 2 && var3 == 5 && var4 == 8) {
                        this.Box_2.setBackgroundResource(R.drawable.orange_bg);
                        this.Box_5.setBackgroundResource(R.drawable.orange_bg);
                        this.Box_8.setBackgroundResource(R.drawable.orange_bg);
                    } else if (var2 == 3 && var3 == 6 && var4 == 9) {
                        this.Box_3.setBackgroundResource(R.drawable.orange_bg);
                        this.Box_6.setBackgroundResource(R.drawable.orange_bg);
                        this.Box_9.setBackgroundResource(R.drawable.orange_bg);
                    } else if (var2 == 1 && var3 == 5 && var4 == 9) {
                        this.Box_1.setBackgroundResource(R.drawable.orange_bg);
                        this.Box_5.setBackgroundResource(R.drawable.orange_bg);
                        this.Box_9.setBackgroundResource(R.drawable.orange_bg);
                    } else if (var2 == 3 && var3 == 5 && var4 == 7) {
                        this.Box_3.setBackgroundResource(R.drawable.orange_bg);
                        this.Box_5.setBackgroundResource(R.drawable.orange_bg);
                        this.Box_7.setBackgroundResource(R.drawable.orange_bg);
                    }

                    var15 = new Handler();
                    if (SharedPrefs.getIsMusic(this)) {
                        MediaPlayer.create(this, R.raw.click).start();
                    }

                    var15.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dialogWinMatch(0);

                        }
                    }, 750L);
                } else if (var5 == this.Player_0) {
                    if (this.PICK_SIDE == 0) {
                        var5 = this.playerTwoWinCount + 1;
                        this.playerTwoWinCount = var5;
                        this.playerTwoWins.setText(String.valueOf(var5));
                    }

                    if (this.PICK_SIDE == 1) {
                        var5 = this.playerOneWinCount + 1;
                        this.playerOneWinCount = var5;
                        this.playerOneWins.setText(String.valueOf(var5));
                    }

                    if (var2 == 1 && var3 == 2 && var4 == 3) {
                        this.Box_1.setBackgroundResource(R.drawable.blue_bg);
                        this.Box_2.setBackgroundResource(R.drawable.blue_bg);
                        this.Box_3.setBackgroundResource(R.drawable.blue_bg);
                    } else if (var2 == 4 && var3 == 5 && var4 == 6) {
                        this.Box_4.setBackgroundResource(R.drawable.blue_bg);
                        this.Box_5.setBackgroundResource(R.drawable.blue_bg);
                        this.Box_6.setBackgroundResource(R.drawable.blue_bg);
                    } else if (var2 == 7 && var3 == 8 && var4 == 9) {
                        this.Box_7.setBackgroundResource(R.drawable.blue_bg);
                        this.Box_8.setBackgroundResource(R.drawable.blue_bg);
                        this.Box_9.setBackgroundResource(R.drawable.blue_bg);
                    } else if (var2 == 1 && var3 == 4 && var4 == 7) {
                        this.Box_1.setBackgroundResource(R.drawable.blue_bg);
                        this.Box_4.setBackgroundResource(R.drawable.blue_bg);
                        this.Box_7.setBackgroundResource(R.drawable.blue_bg);
                    } else if (var2 == 2 && var3 == 5 && var4 == 8) {
                        this.Box_2.setBackgroundResource(R.drawable.blue_bg);
                        this.Box_5.setBackgroundResource(R.drawable.blue_bg);
                        this.Box_8.setBackgroundResource(R.drawable.blue_bg);
                    } else if (var2 == 3 && var3 == 6 && var4 == 9) {
                        this.Box_3.setBackgroundResource(R.drawable.blue_bg);
                        this.Box_6.setBackgroundResource(R.drawable.blue_bg);
                        this.Box_9.setBackgroundResource(R.drawable.blue_bg);
                    } else if (var2 == 1 && var3 == 5 && var4 == 9) {
                        this.Box_1.setBackgroundResource(R.drawable.blue_bg);
                        this.Box_5.setBackgroundResource(R.drawable.blue_bg);
                        this.Box_9.setBackgroundResource(R.drawable.blue_bg);
                    } else if (var2 == 3 && var3 == 5 && var4 == 7) {
                        this.Box_3.setBackgroundResource(R.drawable.blue_bg);
                        this.Box_5.setBackgroundResource(R.drawable.blue_bg);
                        this.Box_7.setBackgroundResource(R.drawable.blue_bg);
                    }

                    var15 = new Handler();
                    if (SharedPrefs.getIsMusic(this)) {
                        MediaPlayer.create(this, R.raw.click).start();
                    }

                    var15.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dialogWinMatch(1);

                        }
                    }, 750L);
                }

                this.isGameActive = false;
            }
        }

    }


    public void checkdraw() {
        boolean z = true;
        for (int i = 0; i <= 8; i++) {
            if (this.filledPos[i] == -1) {
                z = false;
            }
        }
        if (z) {
            this.isGameActive = false;
            if (SharedPrefs.getIsMusic(this)) {
                MediaPlayer.create(this, (int) R.raw.click).start();
            }
            dialogDrawMatch();
        }
    }

    private void dialogWinMatch(int i) {
        this.dialog.setContentView(R.layout.celebrate_dialog);
        this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.dialog.setCanceledOnTouchOutside(false);
        ImageView imageView = (ImageView) this.dialog.findViewById(R.id.offline_game_quit_btn);
        ImageView imageView2 = (ImageView) this.dialog.findViewById(R.id.offline_game_continue_btn);
        new Handler().postDelayed(new MPDashboardActivity13((LottieAnimationView) this.dialog.findViewById(R.id.celebrate_animationView), (RelativeLayout) this.dialog.findViewById(R.id.container_1), i, (ImageView) this.dialog.findViewById(R.id.offline_game_player_img)), 3000);
        imageView.setOnClickListener(new MPDashboardActivity14(this, imageView));
        imageView2.setOnClickListener(new MPDashboardActivity15(this, imageView2));
        this.dialog.show();
    }

    static void lambda$dialogWinMatch$5(LottieAnimationView lottieAnimationView, RelativeLayout relativeLayout, int i, ImageView imageView) {
        lottieAnimationView.setVisibility(8);
        relativeLayout.setVisibility(0);
        if (i == 0) {
            imageView.setImageResource(R.drawable.cross);
        } else if (i == 1) {
            imageView.setImageResource(R.drawable.circle);
        }
    }


    public void m118lambda$dialogWinMatch$7$comkessitictactoeMPDashboardActivity(ImageView imageView, View view) {
        KSUtil.Bounce(this, imageView);
        new Handler().postDelayed(new MPDashboardActivity18(this), 300);
    }


    public void m117lambda$dialogWinMatch$6$comkessitictactoeMPDashboardActivity() {
        this.dialog.dismiss();
        exitDialog();
    }


    public void m120lambda$dialogWinMatch$9$comkessitictactoeMPDashboardActivity(ImageView imageView, View view) {
        KSUtil.Bounce(this, imageView);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                Restart();
                navigate(null, 0);
            }
        }, 300);
    }


    private void dialogDrawMatch() {
        this.drawMatchDialog.setContentView(R.layout.draw_dialog);
        this.drawMatchDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.drawMatchDialog.setCanceledOnTouchOutside(false);
        ImageView imageView = (ImageView) this.drawMatchDialog.findViewById(R.id.offline_game_draw_quit_btn);
        ImageView imageView2 = (ImageView) this.drawMatchDialog.findViewById(R.id.offline_game_draw_continue_btn);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KSUtil.Bounce(MPDashboardActivity.this, imageView);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        drawMatchDialog.dismiss();
                        exitDialog();
                    }
                }, 300);
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KSUtil.Bounce(MPDashboardActivity.this, imageView);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        drawMatchDialog.dismiss();
                        Restart();
                        navigate(null, 0);
                    }
                }, 300);
            }
        });
        this.drawMatchDialog.show();
    }


    private void Restart() {
        for (int i = 0; i <= 8; i++) {
            this.filledPos[i] = -1;
        }
        this.Box_1.setBackgroundResource(R.drawable.gbox_bg);
        this.Box_2.setBackgroundResource(R.drawable.gbox_bg);
        this.Box_3.setBackgroundResource(R.drawable.gbox_bg);
        this.Box_4.setBackgroundResource(R.drawable.gbox_bg);
        this.Box_5.setBackgroundResource(R.drawable.gbox_bg);
        this.Box_6.setBackgroundResource(R.drawable.gbox_bg);
        this.Box_7.setBackgroundResource(R.drawable.gbox_bg);
        this.Box_8.setBackgroundResource(R.drawable.gbox_bg);
        this.Box_9.setBackgroundResource(R.drawable.gbox_bg);
        this.Box_1.setImageResource(0);
        this.Box_2.setImageResource(0);
        this.Box_3.setImageResource(0);
        this.Box_4.setImageResource(0);
        this.Box_5.setImageResource(0);
        this.Box_6.setImageResource(0);
        this.Box_7.setImageResource(0);
        this.Box_8.setImageResource(0);
        this.Box_9.setImageResource(0);
        this.isGameActive = true;
    }

    /* access modifiers changed from: private */
    public void exitDialog() {
        this.exitDialog.setContentView(R.layout.quit_dialog);
        this.exitDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.exitDialog.setCanceledOnTouchOutside(false);
        ImageView imageView = (ImageView) this.exitDialog.findViewById(R.id.quit_btn);
        ImageView imageView2 = (ImageView) this.exitDialog.findViewById(R.id.continue_btn);
        imageView.setOnClickListener(new MPDashboardActivity16(this, imageView));
        imageView2.setOnClickListener(new MPDashboardActivity17(this, imageView2));
        this.exitDialog.show();
    }


    public void m122lambda$exitDialog$15$comkessitictactoeMPDashboardActivity(ImageView imageView, View view) {
        KSUtil.Bounce(this, imageView);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                exitDialog.dismiss();
                Intent intent = new Intent(MPDashboardActivity.this, GameMainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }, 300);
    }


    public void m124lambda$exitDialog$17$comkessitictactoeMPDashboardActivity(ImageView imageView, View view) {
        KSUtil.Bounce(this, imageView);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                exitDialog.dismiss();
                navigate(null, 0);
            }
        }, 300);
    }


}
