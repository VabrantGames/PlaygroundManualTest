package com.testplayground.singles;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class SinglesLauncher extends ApplicationAdapter {

    private Stage stage;
    private Skin skin;
    private RunSingleRunnable runSingleRunnable;
    private ObjectMap<String, Class<?>> singles;

    public SinglesLauncher(RunSingleRunnable runnable) {
        runSingleRunnable = runnable;
    }

    @Override
    public void create() {
        super.create();

        singles = new ObjectMap<>();
        stage = new Stage(new ScreenViewport());

        try (BufferedReader is = Gdx.files.internal("runnableSingles.txt").reader(1024)) {
            final String path = is.readLine();

            if (path == null) {
                throw new FileNotFoundException("");
            }

            String str;
            while ((str = is.readLine()) != null) {
                if (str.isEmpty()) continue;

                try {
                    singles.put(str, ClassReflection.forName(path + str));
                } catch (Exception e) {
                    System.err.println("No such class: " + str);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Gdx.app.exit();
        }

        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("orangepeelui/uiskin.json"));

        Table root = new Table();
        stage.addActor(root);
        root.setFillParent(true);

        Table table = new Table();
        table.pad(0).defaults().expandX().pad(4);

        for (Class<?> c : singles.values()) {
            TextButton button = new TextButton(c.getSimpleName(), skin);
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    int code = runSingleRunnable.run(c);

                    if (code == -1) {
                        button.setDisabled(true);
                        button.getStyle().disabledFontColor = Color.RED;
                    }
                }
            });
            table.add(button).fillX();
            table.row();
        }

        root.add(table).expand().fillX().top();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public interface RunSingleRunnable {
        int run(Class single);
    }
}
