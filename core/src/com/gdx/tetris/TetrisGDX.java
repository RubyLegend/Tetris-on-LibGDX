package com.gdx.tetris;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class TetrisGDX extends Game{

	public ShapeRenderer shapeRenderer; //For animations between screens, do not remove
	@Override
	public void create()
	  {
	    shapeRenderer = new ShapeRenderer();
		setScreen(new LoadingScreen(this));
	  }
	
	@Override
	public void render() {
		super.render(); //Links to a parent class and calls render	*MUST HAVE STRING*
	}
	
	@Override
	public void dispose() {
	}
}
