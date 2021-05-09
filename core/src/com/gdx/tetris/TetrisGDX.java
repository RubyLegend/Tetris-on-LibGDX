package com.gdx.tetris;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class TetrisGDX extends Game {
	public Assets assets = new Assets();
	public ShapeRenderer shapeRenderer; //For animations between screens, do not remove
	@Override
	public void create()
	  {
	    shapeRenderer = new ShapeRenderer();
		assets.load(); //starts loading assets
		while(!assets.manager.update()); //Waiting till all assets are loaded
		//Assets have finished loading, change screen maybe?
	    setScreen(new MainMenuScreen(this, assets)); //your screen should take a Assets object in it's constructor.
	    assets.manager.finishLoading(); //Continues when done loading.
	    //it won't continue until all assets are finished loading.
	  }
	
	@Override
	public void render() {
		super.render(); //Links to a parent class and calls render	
	}
	
	@Override
	public void dispose() {
	}
}
