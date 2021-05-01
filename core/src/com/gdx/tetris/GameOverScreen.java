package com.gdx.tetris;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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

public class GameOverScreen implements Screen {
	final TetrisGDX game;
	//------------------
	Screen thisScreen;
	Stage main;
	Table table;
	TextureAtlas atlas, buttonAtlas;
	TextButtonStyle textButtonStyle;
	Button restart, menu;
	BitmapFont font;
	Skin skin, button;
	FrameRate rate;
	//--------------------
	TextField GameOver;
	
	
	public GameOverScreen(final TetrisGDX game, int score) {
		this.game = game;
		SaveScreen(this);
		main = new Stage();
		Gdx.input.setInputProcessor(main);
		table = new Table();
		table.setFillParent(true);
		main.addActor(table);
		rate = new FrameRate();
		//table.setDebug(true);
		
		skin = new Skin();
		skin.add("default", new LabelStyle(new BitmapFont(), Color.WHITE));
		skin.add("default", new TextFieldStyle(new BitmapFont(), Color.WHITE, null, null, null));
		
		Label blankField = new Label("", skin);
	    
	    font = new BitmapFont();
	    buttonAtlas = new TextureAtlas(Gdx.files.internal("button.pack"));
	    button = new Skin();
	    button.addRegions(buttonAtlas);
	    textButtonStyle = new TextButtonStyle();
	    textButtonStyle.font = font;
	    textButtonStyle.up = button.getDrawable("normal");
	    textButtonStyle.down = button.getDrawable("pressed");
	    textButtonStyle.over = button.getDrawable("hover");
	    restart = new TextButton("Restart game", textButtonStyle);
	    restart.addListener(new ClickListener() {
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	game.setScreen(new Animations(thisScreen, new GameScreen(game), game, 0.02f));
				dispose();
	    		super.clicked(event, x, y);
	        }
	    });
	    
	    skin.add("default", new TextFieldStyle(new BitmapFont(), Color.WHITE, null, null, null));
		TextField Pause = new TextField("           Game Over", skin);
		TextField Score = new TextField("     Your score was: " + score, skin);
	    table.add(Pause).height(50).row();
	    table.add(Score).height(50).row();
	    table.add(restart).width(300).height(100).row();
	    
	    menu = new TextButton("Main menu", textButtonStyle);
	    menu.addListener(new ClickListener() {
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	MainMenuScreen ms = new MainMenuScreen(game);
	    		ms.SaveScreen(ms);
	    		game.setScreen(new Animations(thisScreen, ms, game, 0.05f));
	    		super.clicked(event, x, y);
	        }
	    });
	    table.add(blankField).height(20).row();
	    table.add(menu).width(300).height(100);
	    main.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1.5f)));	
	    atlas = new TextureAtlas(Gdx.files.internal("textures.pack"));
	}
	
	public void SaveScreen(Screen screen) {
		thisScreen = screen;
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		main.act(Gdx.graphics.getDeltaTime());
		main.getBatch().begin();
		main.getBatch().draw(atlas.findRegion("background"), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		main.getBatch().end();
		main.draw();
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
