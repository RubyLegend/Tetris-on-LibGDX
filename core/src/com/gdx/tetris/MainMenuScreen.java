package com.gdx.tetris;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {
	
	final TetrisGDX game;
	Texture background;
	OrthographicCamera camera;
	Sprite mn_sprite;

	public MainMenuScreen(final TetrisGDX game) {
		this.game = game;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1920, 1080);
		background = new Texture("tetris.jpeg");
	}
	
	@Override
	public void render(float delta) {

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		game.batch.draw(background, 320, 180, 1280, 720);
		game.font.draw(game.batch, "Welcome to my Tetris game!!! ", (int)(camera.viewportWidth*0.46), (int)(camera.viewportHeight*0.95));
		game.font.draw(game.batch, "Tap anywhere to begin!", (int)(camera.viewportWidth*0.46), (int)(camera.viewportHeight*0.05));
		game.batch.end();

		if (Gdx.input.isTouched()) {
			game.setScreen(new GameScreen(game));
			dispose();
		}
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
