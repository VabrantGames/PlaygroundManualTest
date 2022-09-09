package com.testplayground.singles;

public class Lwjgl3ConfigHelper {

    public int windowWidth;
    public int windowHeight;

    public Lwjgl3ConfigHelper setWindowedMode(int width, int height) {
        windowWidth = width;
        windowHeight = height;
        return this;
    }

    public interface Lwjgl3Configurable {
        Lwjgl3ConfigHelper getLwjgl3ConfigHelper();
    }
}
