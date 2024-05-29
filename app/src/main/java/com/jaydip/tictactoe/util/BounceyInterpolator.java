package com.jaydip.tictactoe.util;

import android.view.animation.Interpolator;

public class BounceyInterpolator implements Interpolator {
    private double mAmplitude;
    private double mFrequency;

    public BounceyInterpolator(double d, double d2) {
        this.mAmplitude = d;
        this.mFrequency = d2;
    }

    public float getInterpolation(float f) {
        return (float) ((Math.pow(2.718281828459045d, ((double) (-f)) / this.mAmplitude) * -1.0d * Math.cos(this.mFrequency * ((double) f))) + 1.0d);
    }
}
