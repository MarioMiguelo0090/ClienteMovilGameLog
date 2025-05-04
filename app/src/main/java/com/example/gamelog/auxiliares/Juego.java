package com.example.gamelog.auxiliares;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Juego {

    private String name;

    private String released;

    private String description;

    private double rating;
    private List<PlatformWrapper> platforms;



    @SerializedName("background_image")
    private String backgroundImage;

    public String getName() {
        return name;
    }

    public String getReleased() {
        return released;
    }

    public String getDescription() {
        return description;
    }

    public double getRating() {
        return rating;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }
    public List<PlatformWrapper> getPlatforms() { return platforms; }

    public static class PlatformWrapper {
        private Platform platform;
        public Platform getPlatform() { return platform; }
    }

    public static class Platform {
        private String name;
        public String getName() { return name; }
    }
}
