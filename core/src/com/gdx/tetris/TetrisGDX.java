package com.gdx.tetris;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TetrisGDX extends Game {
	
	public SpriteBatch batch;
	public BitmapFont font;
	@Override
	public void create() {
		batch = new SpriteBatch();
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
