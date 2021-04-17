package com.gdx.tetris;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class Animations {
	//private long prevFrameTime;
	Texture texture;
	
	Animations(Texture texture){
		this.texture = texture;
	}
	
	public void FadeIn(TetrisGDX game, float sec) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		float currAlpha=1;
		while(currAlpha != 0) {
			game.batch.begin();
			game.batch.setColor(0, 0, 0, currAlpha);
			game.batch.draw(texture, 0, 0, texture.getWidth(), texture.getHeight());
			currAlpha -= 1.f/(sec*10.f);
			if(currAlpha < 0) currAlpha = 0;
			game.batch.end();
		}
		
	}
}
