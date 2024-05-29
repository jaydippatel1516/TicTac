package com.jaydip.tictactoe.util;

import android.animation.AnimatorSet;
import android.content.Context;
import android.view.animation.AccelerateDecelerateInterpolator;

public class Render {
    private AnimatorSet animatorSet;
    private Context cx;
    private long duration = 1000;

    public Render(Context context) {
        this.cx = context;
    }

    public void setAnimation(AnimatorSet animatorSet2) {
        this.animatorSet = animatorSet2;
    }

    public void start() {
        this.animatorSet.setDuration(this.duration);
        this.animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        this.animatorSet.start();
    }

    public void setDuration(long j) {
        this.duration = j;
    }
}
