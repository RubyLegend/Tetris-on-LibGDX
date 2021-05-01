package com.gdx.tetris;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class TetrisGDX extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	public ShapeRenderer shapeRenderer;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		font = new BitmapFont(); // Default - Arial
		this.setScreen(new MainMenuScreen(this));
		
	}
	
	@Override
	public void render() {
		super.render(); //Links to a parent class and calls render
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
	}
}
