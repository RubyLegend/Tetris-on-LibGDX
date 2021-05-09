package com.gdx.tetris;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SettingsScreen implements Screen {
	final Assets assets;
	final TetrisGDX game;
	Stage main;
	Table table;
	//Texture background;
	//TextureAtlas buttonAtlas;
	TextButtonStyle textButtonStyle;
	Button start;
	BitmapFont font;
	OrthographicCamera camera;
	Skin skin, button;
	float alpha = 0;
	FrameRate rate;
	
	public SettingsScreen(final TetrisGDX game, final Assets assets) {
		this.game = game;
		this.assets = assets;
		main = new Stage();
		rate = new FrameRate();
		Gdx.input.setInputProcessor(main);
		table = new Table();
		table.setFillParent(true);
		main.addActor(table);
		rate = new FrameRate();
		table.setDebug(true);
		
		skin = new Skin();
		skin.add("default", new LabelStyle(new BitmapFont(), Color.WHITE));
	    	    
	    font = new BitmapFont();
	    //buttonAtlas = new TextureAtlas(Gdx.files.internal("button.pack"));
	    button = new Skin();
	    button.addRegions(assets.manager.get(Assets.Buttons));
	    textButtonStyle = new TextButtonStyle();
	    textButtonStyle.font = font;
	    textButtonStyle.up = button.getDrawable("normal");
	    textButtonStyle.down = button.getDrawable("pressed");
	    textButtonStyle.over = button.getDrawable("hover");
	    Label blankField = new Label("", skin);
	    
	    
	    //background = new Texture(Gdx.files.internal("bg1.jpg"));
		
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		main.getBatch().begin();
		main.getBatch().draw(assets.manager.get(Assets.Background).findRegion("bg1"), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		main.getBatch().end();
		main.draw();
		main.act(Gdx.graphics.getDeltaTime());
		rate.update();
		rate.render();
		
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
