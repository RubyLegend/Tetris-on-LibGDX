package com.gdx.tetris;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

//Fading function
//Big thanks to the author of this code: Leowbattle
//https://gist.github.com/Leowbattle/0cff3a06aabe4ee6bd9f723f8565506d

public class Animations implements Screen {

	private Screen currentScreen;
	private Screen nextScreen;

	private TetrisGDX game;

	// Once this reaches 1.0f the next scene is shown
	private float alpha = 0;
	private float shift = 0;
	// true if fade in, false if fade out
	private boolean fadeDirection = true;

	public Animations(Screen current, Screen next, TetrisGDX game, float shift) {
		this.currentScreen = current;
		this.nextScreen = next;
		this.shift = shift;
		// I temporarily change the screen to next because if I call render() on it without calling the create() function
		// there will be crash caused by using null variables
		game.setScreen(next);
		game.setScreen(current);
		//For my particular usage, I pre-init screens, so these strings are useless
		
		this.game = game;
	}

	@Override
	public void show() {

	}

	@SuppressWarnings("static-access")
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);

		if (fadeDirection == true) {
			currentScreen.render(Gdx.graphics.getDeltaTime());
		} else {
			nextScreen.render(Gdx.graphics.getDeltaTime());
		}

		Gdx.gl.glEnable(Gdx.gl.GL_BLEND);
		Gdx.gl.glBlendFunc(Gdx.gl.GL_SRC_ALPHA, Gdx.gl.GL_ONE_MINUS_SRC_ALPHA);
		game.shapeRenderer.setColor(0, 0, 0, alpha);
		game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		game.shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		game.shapeRenderer.end();
		Gdx.gl.glDisable(Gdx.gl.GL_BLEND);

		alpha += fadeDirection == true ? shift : -shift;
		if (alpha >= 1) {
			fadeDirection = false;
		}
		else if (alpha <= 0 && fadeDirection == false) {
			game.setScreen(nextScreen);
		}
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}
	
}
