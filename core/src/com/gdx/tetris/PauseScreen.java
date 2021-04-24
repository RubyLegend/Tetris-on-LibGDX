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

public class PauseScreen implements Screen {
	final TetrisGDX game;
	final GameScreen g2;
	Texture background;
	Stage main;
	Table table;
	TextureAtlas atlas;
	TextureAtlas buttonAtlas;
	TextButtonStyle textButtonStyle;
	Button cont;
	BitmapFont font;
	Skin skin, button;
	
	
	public PauseScreen(final TetrisGDX game, final GameScreen g2) {
		this.game = game;
		this.g2 = g2;
		main = new Stage();
		Gdx.input.setInputProcessor(main);
		table = new Table();
		table.setFillParent(true);
		main.addActor(table);
		
		//table.setDebug(true);
		background = new Texture("tetris.jpeg");
		
		skin = new Skin();
		skin.add("default", new LabelStyle(new BitmapFont(), Color.WHITE));
	    	    
	    font = new BitmapFont();
	    buttonAtlas = new TextureAtlas(Gdx.files.internal("button.pack"));
	    button = new Skin();
	    button.addRegions(buttonAtlas);
	    textButtonStyle = new TextButtonStyle();
	    textButtonStyle.font = font;
	    textButtonStyle.up = button.getDrawable("17868");
	    textButtonStyle.down = button.getDrawable("17869");
	    cont = new TextButton("Continue", textButtonStyle);
	    cont.addListener(new ClickListener() {
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	game.setScreen(g2);
				dispose();
	    		super.clicked(event, x, y);
	        }
	    });
	    
	    skin.add("default", new TextFieldStyle(new BitmapFont(), Color.WHITE, null, null, null));
		TextField Pause = new TextField("             Pause", skin);
	    table.add(Pause);
	    
	    table.row();
	    table.add(cont).width(400).height(150);
	    main.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1.5f)));
		background = new Texture("black_background.jpg");	
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		main.act(Gdx.graphics.getDeltaTime());
		// TODO Auto-generated method stub
		main.getBatch().begin();
		main.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
