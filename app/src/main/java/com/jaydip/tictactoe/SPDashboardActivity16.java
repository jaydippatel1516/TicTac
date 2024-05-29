package com.jaydip.tictactoe;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.airbnb.lottie.LottieAnimationView;


public final  class SPDashboardActivity16 implements Runnable {
    public final  LottieAnimationView f$0;
    public final  RelativeLayout f$1;
    public final  int f$2;
    public final  ImageView f$3;

    public SPDashboardActivity16(LottieAnimationView lottieAnimationView, RelativeLayout relativeLayout, int i, ImageView imageView) {
        this.f$0 = lottieAnimationView;
        this.f$1 = relativeLayout;
        this.f$2 = i;
        this.f$3 = imageView;
    }

    public final void run() {
        SPDashboardActivity.lambda$dialogWinMatch$4(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
