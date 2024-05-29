package com.jaydip.tictactoe.util;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class LayManager {
    public static LinearLayout.LayoutParams LinParams(Context context, int i, int i2) {
        return new LinearLayout.LayoutParams((context.getResources().getDisplayMetrics().widthPixels * i) / 1080, (context.getResources().getDisplayMetrics().heightPixels * i2) / 1920);
    }

    public static RelativeLayout.LayoutParams RelParams(Context context, int i, int i2) {
        return new RelativeLayout.LayoutParams((context.getResources().getDisplayMetrics().widthPixels * i) / 1080, (context.getResources().getDisplayMetrics().heightPixels * i2) / 1920);
    }
}
