package com.jaydip.tictactoe.util;

import android.app.Activity;
import android.content.Intent;

import com.jaydip.tictactoe.R;

public class AdManager {


    public static void startActivity(Activity activity, Intent intent, int i) {
        if (intent != null) {
            activity.startActivityForResult(intent, i);
        }
    }

}
