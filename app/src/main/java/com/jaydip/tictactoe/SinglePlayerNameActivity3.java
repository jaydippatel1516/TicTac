package com.jaydip.tictactoe;


public final  class SinglePlayerNameActivity3 implements Runnable {
    public final  SinglePlayerNameActivity f$0;

    public SinglePlayerNameActivity3(SinglePlayerNameActivity singlePlayerNameActivity) {
        this.f$0 = singlePlayerNameActivity;
    }

    public final void run() {
        this.f$0.onBackPressed();
    }
}
