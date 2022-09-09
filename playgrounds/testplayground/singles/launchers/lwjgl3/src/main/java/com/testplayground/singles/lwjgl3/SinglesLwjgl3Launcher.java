package com.testplayground.singles.lwjgl3;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.*;
import com.testplayground.singles.Lwjgl3ConfigHelper;
import com.testplayground.singles.SinglesLauncher;

public class SinglesLwjgl3Launcher implements SinglesLauncher.RunSingleRunnable {

//    private static final ObjectMap<String, Lwjgl3Window> WINDOWS = new ObjectMap<>();

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("TestPlaygroundSingles");
        config.setWindowedMode(640, 480);
        new Lwjgl3Application(new SinglesLauncher(new SinglesLwjgl3Launcher()), config);
    }

    @Override
    public int run(Class single) {
//        if (WINDOWS.containsKey(single.getSimpleName())) return;

        try {
            ApplicationListener listener = (ApplicationListener) single.getDeclaredConstructor().newInstance();

            Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
            config.setWindowedMode(400, 400);
            config.setTitle(single.getSimpleName());
            config.useVsync(false);

            if (listener instanceof Lwjgl3ConfigHelper.Lwjgl3Configurable) {
                Lwjgl3ConfigHelper helper = ((Lwjgl3ConfigHelper.Lwjgl3Configurable) listener).getLwjgl3ConfigHelper();

                if (helper != null) {
                    config.setWindowedMode(helper.windowWidth, helper.windowHeight);
                }
            }

            Lwjgl3Graphics graphics = (Lwjgl3Graphics) Gdx.graphics;
            config.setWindowPosition(graphics.getWindow().getPositionX() + 50, graphics.getWindow().getPositionY() + 50);

            Lwjgl3Window window = ((Lwjgl3Application) Gdx.app).newWindow(listener, config);
            window.setWindowListener(new Lwjgl3WindowListener() {

                @Override
                public void created(Lwjgl3Window window) {
//                   WINDOWS.put(single.getSimpleName(), window);
                }

                @Override
                public void iconified(boolean isIconified) {
                    //
                }

                @Override
                public void maximized(boolean isMaximized) {
                    //
                }

                @Override
                public void focusLost() {
                    //
                }

                @Override
                public void focusGained() {
                    //
                }

                @Override
                public boolean closeRequested() {
//                    WINDOWS.remove(single.getSimpleName());
                    return true;
                }

                @Override
                public void filesDropped(String[] files) {
                    //
                }

                @Override
                public void refreshRequested() {
                    //
                }
            });

        } catch (Exception e) {
            Gdx.app.error("Error", e.getMessage());
            return -1;
        }
        return 0;
    }
}
