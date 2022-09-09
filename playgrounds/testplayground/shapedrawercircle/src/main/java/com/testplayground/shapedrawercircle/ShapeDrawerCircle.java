package com.testplayground.shapedrawercircle;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import space.earlygrey.shapedrawer.ShapeDrawer;


public class ShapeDrawerCircle extends ApplicationAdapter {

    private Batch batch;
    private ShapeDrawer shapeDrawer;
    private Texture pixTexture;

    @Override
    public void create() {
        batch = new SpriteBatch();

        Pixmap pix = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pix.drawPixel(0, 0, 0xFFFFFFFF);

        pixTexture = new Texture(pix);
        shapeDrawer = new ShapeDrawer(batch, new TextureRegion(pixTexture));
    }

    @Override
    public void dispose() {
       pixTexture.dispose();
       batch.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        float x = Gdx.graphics.getWidth() * 0.5f;
        float y = Gdx.graphics.getHeight() * 0.5f;
        shapeDrawer.filledCircle(x, y, 50, Color.BLACK);
        batch.end();
    }
}
