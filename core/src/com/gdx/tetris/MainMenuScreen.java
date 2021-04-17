package com.gdx.tetris;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MainMenuScreen extends ApplicationAdapter implements Screen{
	final TetrisGDX game;
	Texture background;
	OrthographicCamera camera;
	float alpha = 0;
	
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
		//FadeIn animation
		if(alpha < 1) {
			alpha += 0.01f;
		}
		game.batch.setColor(255, 255, 255, alpha);
		game.font.setColor(255, 255, 255, alpha);
		//--------------------------
		game.batch.draw(background, 320, 180, 1280, 720);
		game.font.draw(game.batch, "Welcome to my Tetris game!!! ", (int)(camera.viewportWidth*0.46), (int)(camera.viewportHeight*0.95));
		game.font.draw(game.batch, "Tap anywhere to begin!", (int)(camera.viewportWidth*0.46), (int)(camera.viewportHeight*0.05));
		game.batch.end();

		if (Gdx.input.isTouched()) {
			GameScreen g2 = new GameScreen(game);
			g2.SaveGameScreen(g2);
			game.setScreen(g2);
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
