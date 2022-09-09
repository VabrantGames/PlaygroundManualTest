package com.testplayground.shapedrawercircle.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.testplayground.shapedrawercircle.ShapeDrawerCircle;

public class ShapeDrawerCircleLwjgl3Launcher {

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("ShapeDrawerCircle");
        config.setWindowedMode(640, 480);
        new Lwjgl3Application(new ShapeDrawerCircle(), config);
    }
}
