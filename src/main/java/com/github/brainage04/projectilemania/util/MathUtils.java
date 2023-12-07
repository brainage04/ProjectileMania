package com.github.brainage04.projectilemania.util;

public class MathUtils {
    public static double randomRange(double min, double max) {
        return min + (Math.random() * (max - min));
    }
}
