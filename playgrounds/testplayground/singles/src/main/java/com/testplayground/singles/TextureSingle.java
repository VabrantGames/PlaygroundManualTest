package com.testplayground.singles;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextureSingle extends ApplicationAdapter implements Lwjgl3ConfigHelper.Lwjgl3Configurable {

    private Batch batch;
    private Texture texture;

    @Override
    public void create() {
       batch = new SpriteBatch();
       texture = new Texture(Gdx.files.internal("character.png"));
    }

    @Override
    public void render() {
       batch.begin();
       batch.draw(texture, 0, 0);
       batch.end();
    }

    @Override
    public Lwjgl3ConfigHelper getLwjgl3ConfigHelper() {
        return new Lwjgl3ConfigHelper()
                .setWindowedMode(800, 200);
    }
}
