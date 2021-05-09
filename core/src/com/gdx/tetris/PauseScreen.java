package com.gdx.tetris;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
	Screen thisScreen;
	Stage main;
	Table table;
	TextButtonStyle textButtonStyle;
	Button cont, menu;
	BitmapFont font;
	Skin skin, button;
	FrameRate rate;
	Assets assets;
	Settings settings;
	
	public PauseScreen(final TetrisGDX game, final GameScreen g2, final Assets assets, final Settings settings) {
		this.game = game;
		this.g2 = g2;
		this.assets = assets;
		this.settings = settings;
		thisScreen = this;
		main = new Stage();
		Gdx.input.setInputProcessor(main);
		table = new Table();
		table.setFillParent(true);
		main.addActor(table);
		rate = new FrameRate();
		//table.setDebug(true);
		
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
	    
	    cont = new TextButton("Continue", textButtonStyle);
	    cont.addListener(new ClickListener() {
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	game.setScreen(new Animations(thisScreen, g2, game, 0.1f));
	    		super.clicked(event, x, y);
	        }
	    });
	    
	    skin.add("default", new TextFieldStyle(new BitmapFont(), Color.WHITE, null, null, null));
		TextField Pause = new TextField("             Pause", skin);
	    table.add(Pause).height(50).row();
	    table.add(cont).width(300).height(100).row();
	    
	    menu = new TextButton("Main menu", textButtonStyle);
	    menu.addListener(new ClickListener() {
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	    		game.setScreen(new Animations(thisScreen, new MainMenuScreen(game, assets), game, 0.05f));
	    		super.clicked(event, x, y);
	        }
	    });
	    table.add(blankField).height(20).row();
	    table.add(menu).width(300).height(100);
	    
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
		
		if(Gdx.input.isKeyJustPressed(settings.Pause)) {
			g2.unPaused = true;
			game.setScreen(new Animations(thisScreen, g2, game, 0.1f));
		}
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
