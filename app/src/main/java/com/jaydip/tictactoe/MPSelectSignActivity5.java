package com.jaydip.tictactoe;


public final  class MPSelectSignActivity5 implements Runnable {
    public final  MPSelectSignActivity f$0;

    public MPSelectSignActivity5(MPSelectSignActivity mPSelectSignActivity) {
        this.f$0 = mPSelectSignActivity;
    }

    public final void run() {
        this.f$0.onBackPressed();
    }
}
