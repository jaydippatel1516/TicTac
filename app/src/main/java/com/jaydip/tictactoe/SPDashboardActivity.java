package com.jaydip.tictactoe;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.jaydip.tictactoe.util.AdManager;
import com.jaydip.tictactoe.util.Animatee;
import com.jaydip.tictactoe.util.KSUtil;
import com.jaydip.tictactoe.util.SharedPrefs;

public class SPDashboardActivity extends AppCompatActivity implements View.OnClickListener {
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
    private ImageView[] Boxes = {this.Box_1, this.Box_2, this.Box_3, this.Box_4, this.Box_5, this.Box_6, this.Box_7, this.Box_8, this.Box_9};
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
    LinearLayout playerLay;
    private String playerOne;
    private ImageView playerOneImg;
    private TextView playerOneName;
    int playerOneWinCount = 0;
    private TextView playerOneWins;
    private TextView playerTwoName;
    int playerTwoWinCount = 0;
    private TextView playerTwoWins;
    LinearLayout robotLay;
    private ImageView settingsBtn;
    int storeActivePlayer;
    Vibrator vibrator;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_sp_dashboard);
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
        ImageView imageView = (ImageView) findViewById(R.id.img_9);
        this.Box_9 = imageView;
        ImageView[] imageViewArr = this.Boxes;
        imageViewArr[0] = this.Box_1;
        imageViewArr[1] = this.Box_2;
        imageViewArr[2] = this.Box_3;
        imageViewArr[3] = this.Box_4;
        imageViewArr[4] = this.Box_5;
        imageViewArr[5] = this.Box_6;
        imageViewArr[6] = this.Box_7;
        imageViewArr[7] = this.Box_8;
        imageViewArr[8] = imageView;
        this.backBtn = (ImageView) findViewById(R.id.offline_game_back_btn);
        this.settingsBtn = (ImageView) findViewById(R.id.ai_game_seting_gifview);
        this.playerOneImg = (ImageView) findViewById(R.id.player_one_img);
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
        this.PICK_SIDE = getIntent().getIntExtra("ps", 0);
        this.playerOneName.setText(this.playerOne);
        int i = this.PICK_SIDE;
        this.ActivePlayer = i;
        this.storeActivePlayer = i;
        this.robotLay = (LinearLayout) findViewById(R.id.robotLay);
        this.playerLay = (LinearLayout) findViewById(R.id.playerLay);
        int i2 = this.PICK_SIDE;
        if (i2 == 0) {
            this.robotLay.setBackgroundResource(R.drawable.bgb);
            this.playerTwoName.setTextColor(getResources().getColor(R.color.player_one_txt));
            this.playerTwoWins.setBackgroundResource(R.drawable.scoreb);
            this.playerLay.setBackgroundResource(R.drawable.bgy);
            this.playerOneName.setTextColor(getResources().getColor(R.color.player_two_txt));
            this.playerOneWins.setBackgroundResource(R.drawable.scorey);
            this.storeActivePlayer = 0;
            this.ActivePlayer = 0;
        } else if (i2 == 1) {
            this.robotLay.setBackgroundResource(R.drawable.bgy);
            this.playerTwoName.setTextColor(getResources().getColor(R.color.player_two_txt));
            this.playerTwoWins.setBackgroundResource(R.drawable.scorey);
            this.playerLay.setBackgroundResource(R.drawable.bgb);
            this.playerOneName.setTextColor(getResources().getColor(R.color.player_one_txt));
            this.playerOneWins.setBackgroundResource(R.drawable.scoreb);
            this.storeActivePlayer = 1;
            this.ActivePlayer = 1;
        }
        this.settingsBtn.setOnClickListener(new SPDashboardActivity13(this));
        this.backBtn.setOnClickListener(new SPDashboardActivity14(this));

    }


    public void m154lambda$onCreate$1$comkessitictactoeSPDashboardActivity(View view) {
        KSUtil.Bounce(this, this.settingsBtn);
        new Handler().postDelayed(new SPDashboardActivity11(this), 300);
    }


    public void m153lambda$onCreate$0$comkessitictactoeSPDashboardActivity() {
        startActivity(new Intent(this, SettingsActivity.class));
        Animatee.animateSlideUp(this);
    }


    public void m155lambda$onCreate$2$comkessitictactoeSPDashboardActivity(View view) {
        KSUtil.Bounce(this, this.backBtn);
        new Handler().postDelayed(new SPDashboardActivity8(this), 300);
    }


    public void navigate(Intent intent, int i) {
        AdManager.startActivity(this, intent, i);

    }

    @Override
    public void onClick(View view) {
        if (this.isGameActive) {
            ImageView imageView = (ImageView) findViewById(view.getId());
            int parseInt = Integer.parseInt(view.getTag().toString());
            int i = this.ActivePlayer;
            int i2 = this.Player_X;
            if (i == i2) {
                int i3 = parseInt - 1;
                if (this.filledPos[i3] == -1 && this.PICK_SIDE == i2) {
                    if (SharedPrefs.getIsMusic(this)) {
                        MediaPlayer.create(this, (int) R.raw.x).start();
                    }
                    if (SharedPrefs.getIsVibrate(this)) {
                        if (Build.VERSION.SDK_INT >= 26) {
                            this.vibrator.vibrate(VibrationEffect.createOneShot(200, -1));
                        } else {
                            this.vibrator.vibrate(200);
                        }
                    }
                    imageView.setImageResource(R.drawable.cross);
                    this.storeActivePlayer = this.ActivePlayer;
                    this.ActivePlayer = this.Player_0;
                    this.filledPos[i3] = this.Player_X;
                    checkForWin();
                    if (this.isGameActive) {
                        checkdraw();
                    }
                    if (this.isGameActive) {
                        AI();
                        return;
                    }
                    return;
                }
            }
            int i4 = this.Player_0;
            if (i == i4) {
                int i5 = parseInt - 1;
                if (this.filledPos[i5] == -1 && this.PICK_SIDE == i4) {
                    if (SharedPrefs.getIsMusic(this)) {
                        MediaPlayer.create(this, (int) R.raw.o).start();
                    }
                    if (SharedPrefs.getIsVibrate(this)) {
                        if (Build.VERSION.SDK_INT >= 26) {
                            this.vibrator.vibrate(VibrationEffect.createOneShot(200, -1));
                        } else {
                            this.vibrator.vibrate(200);
                        }
                    }
                    imageView.setImageResource(R.drawable.circle);
                    this.storeActivePlayer = this.ActivePlayer;
                    this.ActivePlayer = this.Player_X;
                    this.filledPos[i5] = this.Player_0;
                    checkForWin();
                    if (this.isGameActive) {
                        checkdraw();
                    }
                    if (this.isGameActive) {
                        Toast.makeText(this, "AI start work !!", 1);
                        AI();
                    }
                }
            }
        }
    }

    private void AI() {
        int i = 0;
        char[][] cArr = {new char[]{' ', ' ', ' '}, new char[]{' ', ' ', ' '}, new char[]{' ', ' ', ' '}};
        for (int i2 = 0; i2 <= 8; i2++) {
            if (i2 < 3) {
                int i3 = this.filledPos[i2];
                if (i3 == -1) {
                    cArr[0][i2] = '_';
                } else if (i3 == 0) {
                    cArr[0][i2] = 'x';
                } else if (i3 == 1) {
                    cArr[0][i2] = 'o';
                }
            } else if (i2 >= 3 && i2 < 6) {
                int i4 = i2 - 3;
                int i5 = this.filledPos[i2];
                if (i5 == -1) {
                    cArr[1][i4] = '_';
                } else if (i5 == 0) {
                    cArr[1][i4] = 'x';
                } else if (i5 == 1) {
                    cArr[1][i4] = 'o';
                }
            } else if (i2 >= 6 && i2 < 9) {
                int i6 = i2 - 6;
                int i7 = this.filledPos[i2];
                if (i7 == -1) {
                    cArr[2][i6] = '_';
                } else if (i7 == 0) {
                    cArr[2][i6] = 'x';
                } else if (i7 == 1) {
                    cArr[2][i6] = 'o';
                }
            }
        }
        MoveFinder.Move findBestMove = MoveFinder.findBestMove(cArr);
        System.out.printf("The Optimal Move is :\n", new Object[0]);
        System.out.printf("ROW: %d COL: %d\n\n", Integer.valueOf(findBestMove.row), Integer.valueOf(findBestMove.col));
        Log.w("myApp", "Row and col :  " + findBestMove.row + findBestMove.col);
        if (findBestMove.row == 0) {
            i = findBestMove.col;
        } else if (findBestMove.row == 1) {
            i = findBestMove.col + 3;
        } else if (findBestMove.row == 2) {
            i = findBestMove.col + 6;
        }
        int i8 = this.PICK_SIDE;
        if (i8 == 0) {
            this.Boxes[i].setImageResource(R.drawable.circle);
            this.storeActivePlayer = this.ActivePlayer;
            this.ActivePlayer = this.Player_X;
            this.filledPos[i] = this.Player_0;
        } else if (i8 == 1) {
            this.Boxes[i].setImageResource(R.drawable.cross);
            this.storeActivePlayer = this.ActivePlayer;
            this.ActivePlayer = this.Player_0;
            this.filledPos[i] = this.Player_X;
        }
        checkForWin();
        if (this.isGameActive) {
            checkdraw();
        }
    }

    private void checkForWin() {
        int[] var9 = new int[]{4, 5, 6};
        int[] var10 = new int[]{7, 8, 9};
        int[] var13 = new int[]{1, 4, 7};
        int[] var12 = new int[]{2, 5, 8};
        int[] var7 = new int[]{3, 6, 9};
        int[] var8 = new int[]{1, 5, 9};
        int[] var11 = new int[]{3, 5, 7};

        for (int var1 = 0; var1 < 8; ++var1) {
            int[] var14 = (new int[][]{{1, 2, 3}, var9, var10, var13, var12, var7, var8, var11})[var1];
            int var2 = var14[0];
            int var4 = var14[1];
            int var3 = var14[2];
            var14 = this.filledPos;
            int var6 = var14[var2 - 1];
            int var5 = var14[var4 - 1];
            if (var6 == var5 && var5 == var14[var3 - 1] && var6 != -1) {
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

                    if (var2 == 1 && var4 == 2 && var3 == 3) {
                        this.Box_1.setBackgroundResource(R.drawable.orange_bg);
                        this.Box_2.setBackgroundResource(R.drawable.orange_bg);
                        this.Box_3.setBackgroundResource(R.drawable.orange_bg);
                    } else if (var2 == 4 && var4 == 5 && var3 == 6) {
                        this.Box_4.setBackgroundResource(R.drawable.orange_bg);
                        this.Box_5.setBackgroundResource(R.drawable.orange_bg);
                        this.Box_6.setBackgroundResource(R.drawable.orange_bg);
                    } else if (var2 == 7 && var4 == 8 && var3 == 9) {
                        this.Box_7.setBackgroundResource(R.drawable.orange_bg);
                        this.Box_8.setBackgroundResource(R.drawable.orange_bg);
                        this.Box_9.setBackgroundResource(R.drawable.orange_bg);
                    } else if (var2 == 1 && var4 == 4 && var3 == 7) {
                        this.Box_1.setBackgroundResource(R.drawable.orange_bg);
                        this.Box_4.setBackgroundResource(R.drawable.orange_bg);
                        this.Box_7.setBackgroundResource(R.drawable.orange_bg);
                    } else if (var2 == 2 && var4 == 5 && var3 == 8) {
                        this.Box_2.setBackgroundResource(R.drawable.orange_bg);
                        this.Box_5.setBackgroundResource(R.drawable.orange_bg);
                        this.Box_8.setBackgroundResource(R.drawable.orange_bg);
                    } else if (var2 == 3 && var4 == 6 && var3 == 9) {
                        this.Box_3.setBackgroundResource(R.drawable.orange_bg);
                        this.Box_6.setBackgroundResource(R.drawable.orange_bg);
                        this.Box_9.setBackgroundResource(R.drawable.orange_bg);
                    } else if (var2 == 1 && var4 == 5 && var3 == 9) {
                        this.Box_1.setBackgroundResource(R.drawable.orange_bg);
                        this.Box_5.setBackgroundResource(R.drawable.orange_bg);
                        this.Box_9.setBackgroundResource(R.drawable.orange_bg);
                    } else if (var2 == 3 && var4 == 5 && var3 == 7) {
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
                            int var2 = PICK_SIDE;
                            int var1 = Player_X;
                            if (var2 == var1) {
                                dialogWinMatch(0, false);
                            } else if (var2 != var1) {
                                dialogWinMatch(0, true);
                            }


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

                    if (var2 == 1 && var4 == 2 && var3 == 3) {
                        this.Box_1.setBackgroundResource(R.drawable.blue_bg);
                        this.Box_2.setBackgroundResource(R.drawable.blue_bg);
                        this.Box_3.setBackgroundResource(R.drawable.blue_bg);
                    } else if (var2 == 4 && var4 == 5 && var3 == 6) {
                        this.Box_4.setBackgroundResource(R.drawable.blue_bg);
                        this.Box_5.setBackgroundResource(R.drawable.blue_bg);
                        this.Box_6.setBackgroundResource(R.drawable.blue_bg);
                    } else if (var2 == 7 && var4 == 8 && var3 == 9) {
                        this.Box_7.setBackgroundResource(R.drawable.blue_bg);
                        this.Box_8.setBackgroundResource(R.drawable.blue_bg);
                        this.Box_9.setBackgroundResource(R.drawable.blue_bg);
                    } else if (var2 == 1 && var4 == 4 && var3 == 7) {
                        this.Box_1.setBackgroundResource(R.drawable.blue_bg);
                        this.Box_4.setBackgroundResource(R.drawable.blue_bg);
                        this.Box_7.setBackgroundResource(R.drawable.blue_bg);
                    } else if (var2 == 2 && var4 == 5 && var3 == 8) {
                        this.Box_2.setBackgroundResource(R.drawable.blue_bg);
                        this.Box_5.setBackgroundResource(R.drawable.blue_bg);
                        this.Box_8.setBackgroundResource(R.drawable.blue_bg);
                    } else if (var2 == 3 && var4 == 6 && var3 == 9) {
                        this.Box_3.setBackgroundResource(R.drawable.blue_bg);
                        this.Box_6.setBackgroundResource(R.drawable.blue_bg);
                        this.Box_9.setBackgroundResource(R.drawable.blue_bg);
                    } else if (var2 == 1 && var4 == 5 && var3 == 9) {
                        this.Box_1.setBackgroundResource(R.drawable.blue_bg);
                        this.Box_5.setBackgroundResource(R.drawable.blue_bg);
                        this.Box_9.setBackgroundResource(R.drawable.blue_bg);
                    } else if (var2 == 3 && var4 == 5 && var3 == 7) {
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
                            if (PICK_SIDE == Player_0) {
                                dialogWinMatch(1, false);
                            } else if (PICK_SIDE != Player_0) {
                                dialogWinMatch(1, true);
                            }

                        }
                    }, 750L);
                }

                this.isGameActive = false;
            }
        }

    }
    public void m140lambda$checkForWin$3$comkessitictactoeSPDashboardActivity() {
        int i = this.PICK_SIDE;
        int i2 = this.Player_X;
        if (i == i2) {
            dialogWinMatch(0, false);
        } else if (i != i2) {
            dialogWinMatch(0, true);
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

    private void dialogWinMatch(int i, boolean z) {
        this.dialog.setContentView(R.layout.celebrate_dialog);
        this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.dialog.setCanceledOnTouchOutside(false);
        LottieAnimationView lottieAnimationView = (LottieAnimationView) this.dialog.findViewById(R.id.celebrate_animationView);
        RelativeLayout relativeLayout = (RelativeLayout) this.dialog.findViewById(R.id.container_1);
        ImageView imageView = (ImageView) this.dialog.findViewById(R.id.offline_game_quit_btn);
        ImageView imageView2 = (ImageView) this.dialog.findViewById(R.id.offline_game_continue_btn);
        ImageView imageView3 = (ImageView) this.dialog.findViewById(R.id.offline_game_player_img);
        TextView textView = (TextView) this.dialog.findViewById(R.id.titleTxt);
        if (z) {
            textView.setText("Robot Win!");
        } else {
            textView.setText("You Are Win!");
        }
        new Handler().postDelayed(new SPDashboardActivity16(lottieAnimationView, relativeLayout, i, imageView3), 3000);
        imageView.setOnClickListener(new SPDashboardActivity17(this, imageView));
        imageView2.setOnClickListener(new SPDashboardActivity1(this, imageView2));
        this.dialog.show();
    }

    static void lambda$dialogWinMatch$4(LottieAnimationView lottieAnimationView, RelativeLayout relativeLayout, int i, ImageView imageView) {
        lottieAnimationView.setVisibility(8);
        relativeLayout.setVisibility(0);
        if (i == 0) {
            imageView.setImageResource(R.drawable.cross);
        } else if (i == 1) {
            imageView.setImageResource(R.drawable.circle);
        }
    }


    public void m146lambda$dialogWinMatch$6$comkessitictactoeSPDashboardActivity(ImageView imageView, View view) {
        KSUtil.Bounce(this, imageView);
        new Handler().postDelayed(new SPDashboardActivity15(this), 300);
    }


    public void m145lambda$dialogWinMatch$5$comkessitictactoeSPDashboardActivity() {
        this.dialog.dismiss();
        exitDialog();
    }


    public void m148lambda$dialogWinMatch$8$comkessitictactoeSPDashboardActivity(ImageView imageView, View view) {
        KSUtil.Bounce(this, imageView);
        new Handler().postDelayed(new SPDashboardActivity3(this), 300);
    }


    public void m147lambda$dialogWinMatch$7$comkessitictactoeSPDashboardActivity() {
        this.dialog.dismiss();
        Restart();
        navigate(null, 0);
    }

    private void dialogDrawMatch() {
        this.drawMatchDialog.setContentView(R.layout.draw_dialog);
        this.drawMatchDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.drawMatchDialog.setCanceledOnTouchOutside(false);
        ImageView imageView = (ImageView) this.drawMatchDialog.findViewById(R.id.offline_game_draw_quit_btn);
        ImageView imageView2 = (ImageView) this.drawMatchDialog.findViewById(R.id.offline_game_draw_continue_btn);
        imageView.setOnClickListener(new SPDashboardActivity5(this, imageView));
        imageView2.setOnClickListener(new SPDashboardActivity6(this, imageView2));
        this.drawMatchDialog.show();
    }


    public void m141x553879f4(ImageView imageView, View view) {
        KSUtil.Bounce(this, imageView);
        new Handler().postDelayed(new SPDashboardActivity2(this), 300);
    }


    public void m144lambda$dialogDrawMatch$9$comkessitictactoeSPDashboardActivity() {
        this.drawMatchDialog.dismiss();
        exitDialog();
    }


    public void m143x6e78cff6(ImageView imageView, View view) {
        KSUtil.Bounce(this, imageView);
        new Handler().postDelayed(new SPDashboardActivity12(this), 300);
    }


    public void m142xe1d8a4f5() {
        this.drawMatchDialog.dismiss();
        Restart();
        navigate(null, 0);
    }

    @Override // androidx.activity.ComponentActivity
    public void onBackPressed() {
        super.onBackPressed();
        exitDialog();
    }

    /* access modifiers changed from: private */
    public void exitDialog() {
        this.exitDialog.setContentView(R.layout.quit_dialog);
        this.exitDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.exitDialog.setCanceledOnTouchOutside(false);
        ImageView imageView = (ImageView) this.exitDialog.findViewById(R.id.quit_btn);
        ImageView imageView2 = (ImageView) this.exitDialog.findViewById(R.id.continue_btn);
        imageView.setOnClickListener(new SPDashboardActivity9(this, imageView));
        imageView2.setOnClickListener(new SPDashboardActivity10(this, imageView2));
        this.exitDialog.show();
    }


    public void m150lambda$exitDialog$14$comkessitictactoeSPDashboardActivity(ImageView imageView, View view) {
        KSUtil.Bounce(this, imageView);
        new Handler().postDelayed(new SPDashboardActivity4(this), 300);
    }


    public void m149lambda$exitDialog$13$comkessitictactoeSPDashboardActivity() {
        this.exitDialog.dismiss();
        Intent intent = new Intent(this, GameMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    public void m152lambda$exitDialog$16$comkessitictactoeSPDashboardActivity(ImageView imageView, View view) {
        KSUtil.Bounce(this, imageView);
        new Handler().postDelayed(new SPDashboardActivity0(this), 300);
    }


    public void m151lambda$exitDialog$15$comkessitictactoeSPDashboardActivity() {
        this.exitDialog.dismiss();
        navigate(null, 0);
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
        if (this.ActivePlayer != this.PICK_SIDE) {
            AI();
        }
    }
}
