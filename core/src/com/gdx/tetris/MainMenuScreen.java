package com.gdx.tetris;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class MainMenuScreen extends ApplicationAdapter implements Screen{
	final TetrisGDX game;
	Assets assets;
	MainMenuScreen thisScreen;
	Stage main;
	Table table;
	Texture background, logo;
	TextureAtlas atlas, buttonAtlas;
	TextButtonStyle textButtonStyle;
	Button start, settings, score, exit;
	BitmapFont font;
	OrthographicCamera camera;
	Skin skin, button;
	FrameRate rate;
	//----------------------
	boolean _exit = false;
	float alpha = 1f;
	
	public MainMenuScreen(final TetrisGDX game, final Assets assets) {
		this.game = game;
		this.assets = assets;
		thisScreen = this;
		main = new Stage();
		Gdx.input.setInputProcessor(main);
		table = new Table();
		table.setFillParent(true);
		main.addActor(table);
		rate = new FrameRate();
		
		//table.setDebug(true);
		atlas = new TextureAtlas(Gdx.files.internal("textures.pack"));
		
		skin = new Skin();
		skin.add("default", new LabelStyle(new BitmapFont(), Color.WHITE));
		skin.add("default", new TextFieldStyle(new BitmapFont(), Color.WHITE, null, null, null));
		
		Label blankField = new Label("", skin);
	    
	    font = new BitmapFont();
	    button = new Skin();
	    button.addRegions(assets.manager.get(Assets.Buttons));
	    
	    textButtonStyle = new TextButtonStyle();
	    textButtonStyle.font = font;
	    textButtonStyle.up = button.getDrawable("normal");
	    textButtonStyle.down = button.getDrawable("pressed");
	    textButtonStyle.over = button.getDrawable("hover");
	    
	    start = new TextButton("Start the game!", textButtonStyle);
	    settings = new TextButton("Settings", textButtonStyle);
	    exit = new TextButton("Exit", textButtonStyle);
	    score = new TextButton("Score Board", textButtonStyle);
	    
	    table.add(blankField).height(600).row();
	    table.add(start).width(300).height(50).row();
	    table.add(blankField).height(20).row();
	    table.add(score).width(300).height(50).row();
	    table.add(blankField).height(20).row();
	    table.add(settings).width(300).height(50).row();
	    table.add(blankField).height(20).row();
	    table.add(exit).width(300).height(50).row();
	    
	    main.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1.5f)));
	    
	    start.addListener(new ClickListener() {
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	    	    game.setScreen(new Animations(thisScreen, new GameScreen(game, assets, new Settings()), game, 0.02f));
	        	super.clicked(event, x, y);
	        }
	    });
	    score.addListener(new ClickListener() {
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	    	    game.setScreen(new Animations(thisScreen, new ScoreBoardScreen(game, thisScreen, assets), game, 0.02f));
	        	super.clicked(event, x, y);
	        }
	    });
	    settings.addListener(new ClickListener() {
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	    	    game.setScreen(new Animations(thisScreen, new SettingsScreen(game, assets, new Settings()), game, 0.02f));
	        	super.clicked(event, x, y);
	        }
	    });
	    exit.addListener(new ClickListener() {
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	    	    _exit = true;
	    	    main.addAction(Actions.fadeOut(1f));
	        	super.clicked(event, x, y);
	        }
	    });

	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		main.act(Gdx.graphics.getDeltaTime());
		main.getBatch().begin();
		main.getBatch().draw(assets.manager.get(Assets.Background).findRegion("bg1"), 0, 0, 1920, 1080);
		main.getBatch().draw(assets.manager.get(Assets.logo), (Gdx.graphics.getWidth()-assets.manager.get(Assets.logo).getWidth())/2, Gdx.graphics.getHeight()/2);
		main.getBatch().end();
		main.draw();
		rate.update();
		rate.render();
		if(_exit) {
			alpha-=0.01f;
			if(alpha <= 0) {
				Gdx.app.exit();
			}
		}
	}

	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		main.getViewport().update(width, height, true);
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
		main.dispose();
	}

}
