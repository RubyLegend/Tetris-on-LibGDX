package com.gdx.tetris;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class PauseScreen implements Screen {
	final TetrisGDX game;
	final GameScreen g2;
	Texture background;
	OrthographicCamera camera;
	public PauseScreen(final TetrisGDX game, final GameScreen g2) {
		this.game = game;
		this.g2 = g2;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, GameScreen.getWwidth(), GameScreen.getWheight());
		background = new Texture("black_background.jpg");	
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		// TODO Auto-generated method stub
		game.batch.begin();
		game.batch.setColor(0, 0, 0, 0.25f);
		game.batch.draw(background, 0, 0, 1920, 1080);
		game.font.draw(game.batch, "Pause", GameScreen.getWwidth()/2-50, GameScreen.getWheight()/2+200);
		game.font.draw(game.batch, "Press anywhere to continue", GameScreen.getWwidth()/2-150, GameScreen.getWheight()/2);
		game.batch.end();
		
		if (Gdx.input.isTouched()) {
			game.setScreen(g2);
			dispose();
		}
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
