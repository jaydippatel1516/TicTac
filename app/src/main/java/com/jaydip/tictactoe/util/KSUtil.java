package com.jaydip.tictactoe.util;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.jaydip.tictactoe.R;

public class KSUtil {
    public static AnimatorSet In(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f));
        return animatorSet;
    }

    public static AnimatorSet InDown(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f, 1.0f, 1.0f), ObjectAnimator.ofFloat(view, "translationY", (float) ((long) (-view.getHeight())), 30.0f, -10.0f, 0.0f));
        return animatorSet;
    }

    public static AnimatorSet ZoomIn(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(view, "scaleX", 0.45f, 1.0f), ObjectAnimator.ofFloat(view, "scaleY", 0.45f, 1.0f), ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f));
        return animatorSet;
    }

    public static void Bounce(Context context, View view) {
        Animation loadAnimation = AnimationUtils.loadAnimation(context, R.anim.bounce_anim);
        loadAnimation.setInterpolator(new BounceyInterpolator(0.2d, 20.0d));
        view.startAnimation(loadAnimation);
    }

    public static void maxNativeSize(Context context, RelativeLayout relativeLayout) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(dpToPixel(context, 300), dpToPixel(context, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION));
        layoutParams.gravity = 17;
        relativeLayout.setLayoutParams(layoutParams);
    }

    public static int dpToPixel(Context context, int i) {
        return (int) ((((float) i) * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
