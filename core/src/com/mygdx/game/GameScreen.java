package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    final GameZep game;
    Texture zepImage;
    Texture background;
    OrthographicCamera camera;
    Zeppelin zeppelin;
    float windX;
    float windY;

    public GameScreen(final GameZep game) {
        this.game = game;

        // Load images
        zepImage = new Texture(Gdx.files.internal("zeppelin.png"));
        background = new Texture(Gdx.files.internal("zeplandbackground.png"));


        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);


        zeppelin = new Zeppelin(400, 300, 128, 64, 60, 30, 30);

        windX = MathUtils.random(-4,4);
        windY = 0;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        boolean moveLeft = Gdx.input.isKeyPressed(Keys.LEFT);
        boolean moveRight = Gdx.input.isKeyPressed(Keys.RIGHT);
        boolean moveUp = Gdx.input.isKeyPressed(Keys.UP);
        boolean moveDown = Gdx.input.isKeyPressed(Keys.DOWN);

        zeppelin.setWind(windX, windY);
        zeppelin.update(delta, moveLeft, moveRight, moveUp, moveDown);

        game.batch.begin();
        game.batch.draw(background,0,0, camera.viewportWidth, camera.viewportHeight);
        game.batch.draw(zepImage, zeppelin.getBounds().x, zeppelin.getBounds().y, zeppelin.getBounds().width, zeppelin.getBounds().height);
        game.font.draw(game.batch, "Wind speed: " + windX, 0, 480);
        game.batch.end();

        if(zeppelin.getBounds().y < 10 && zeppelin.getBounds().x > 300){
            zeppelin.getBounds().y = 400;
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        zepImage.dispose();
    }
}

