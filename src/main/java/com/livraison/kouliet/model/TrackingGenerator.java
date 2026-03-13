package com.livraison.kouliet.model;

public class TrackingGenerator {

    public static String generate() {
        return "KLT-" + System.currentTimeMillis();
    }
}
