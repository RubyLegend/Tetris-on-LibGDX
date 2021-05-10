package com.gdx.tetris;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class LoadingScreen implements Screen {
	private Stage main;
	private Texture bg, logo;
	private boolean loaded = false;
	public Assets assets = new Assets();
	final TetrisGDX game;
	
	public LoadingScreen(final TetrisGDX game) {
		this.game = game;
		main = new Stage();
		bg = new Texture(Gdx.files.internal("bootimage.jpeg"));
		logo = new Texture(Gdx.files.internal("tetris.png"));
		assets.load(); //starts loading assets
		main.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1.5f)));
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		main.act(Gdx.graphics.getDeltaTime());
		if(assets.manager.update() && !loaded) {//Waiting till all assets are loaded
			assets.manager.finishLoading(); //Continues when done loading.
			loaded = true;
			//it won't continue until all assets are finished loading.
			//Assets have finished loading, change screen maybe?
			game.setScreen(new Animations(this, new MainMenuScreen(game, assets), game, 0.02f));
			//setScreen(new MainMenuScreen(this, assets));
		}
		
		main.getBatch().begin();
		main.getBatch().draw(bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		main.getBatch().draw(logo, (Gdx.graphics.getWidth()-logo.getWidth())/2, Gdx.graphics.getHeight()/2 - logo.getHeight()/2);
		main.getBatch().end();
		main.draw();
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
